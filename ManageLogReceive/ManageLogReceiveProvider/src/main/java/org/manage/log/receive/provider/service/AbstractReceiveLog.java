package org.manage.log.receive.provider.service;

import org.manage.log.common.constants.LogRecordIndexSort;
import org.manage.log.common.model.config.LogConfig;
import org.manage.log.common.model.config.LogIndexConfig;
import org.manage.log.common.model.log.LogRecord;
import org.manage.log.common.model.log.builder.LogRecordFactory;
import org.manage.log.receive.facade.dto.OperateLogResp;
import org.manage.log.receive.facade.dto.UploadLogRecordReq;
import org.manage.log.receive.provider.repository.LogConfigRepository;
import org.manage.log.receive.provider.repository.LogRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * @author cartoon
 * @date 2021/11/17 23:14
 */
@Service
public abstract class AbstractReceiveLog implements ReceiveLog {

    @Autowired
    private LogRecordRepository logRecordRepository;

    @Autowired
    private LogConfigRepository logConfigRepository;

    @Resource
    private LogRecordFactory logRecordFactory;

    @Override
    public OperateLogResp<Boolean> receive(UploadLogRecordReq uploadLogRecordReq) {
        return receive(List.of(uploadLogRecordReq));
    }

    @Override
    public OperateLogResp<Boolean> receive(List<UploadLogRecordReq> uploadLogRecordReqs) {
        //judge income request data illegal
        judgeParamIllegal(uploadLogRecordReqs);
        //transfer request data to domain entity
        //get log config from repository
        List<String> configNameList = uploadLogRecordReqs.stream().map(UploadLogRecordReq::getConfigName).toList();
        List<LogConfig> configList = logConfigRepository.getByConfigNameList(configNameList);
        //format log by log formatter and value list, construct log index list
        List<LogRecord> logRecordList = executeLog(configList, uploadLogRecordReqs);
        //call repository to store
        boolean saveRes = logRecordRepository.save(logRecordList);
        return new OperateLogResp<>(saveRes);
    }

    private List<LogRecord> executeLog(List<LogConfig> configList, List<UploadLogRecordReq> uploadLogRecordReqList){
        Map<String, LogConfig> configNameToConfig = configList.stream().collect(Collectors.toMap(LogConfig::getRuleName, Function.identity()));
        //format log content and others, call factory to build domain object
        return uploadLogRecordReqList.stream().map(uploadReq -> {
            LogConfig logConfig = configNameToConfig.get(uploadReq.getConfigName());
            return ofNullable(logConfig).map(config -> {
                //format content by content template
                String content = String.format(config.getContentTemplate(), uploadReq.getValueList());
                //get index value from value list according by index config
                Map<String, LogRecordIndexSort> indexValueToIndexSortMap = new HashMap<>();
                for(LogIndexConfig logIndexConfig : config.getIndexConfigList()){
                    indexValueToIndexSortMap.put(uploadReq.getValueList().get(logIndexConfig.getValueIndex().intValue()), logIndexConfig.getLogRecordIndexSort());
                }
                return logRecordFactory.build(content, logConfig.getOperatorSort(), uploadReq.getOperator(),
                                                                config.getLogRecordSort(), indexValueToIndexSortMap,
                                            getUploadTime(uploadReq.getUploadTime()));

            }).orElse(null);
        }).filter(Objects::nonNull).toList();
    }

    protected abstract LocalDateTime getUploadTime(LocalDateTime incomingUploadTime);

    private void judgeParamIllegal(List<UploadLogRecordReq> uploadLogRecordReqs){
        Assert.notNull(uploadLogRecordReqs, "receive log, upload request must not be null");
        for(UploadLogRecordReq req : uploadLogRecordReqs){
            judgeParamIllegal(req);
        }
    }

    protected void judgeParamIllegal(UploadLogRecordReq uploadLogRecordReq){

        Assert.notNull(uploadLogRecordReq, "receive log, upload req must not be null");
        Assert.notNull(uploadLogRecordReq.getConfigName(), "receive log, config name must not be null");
        Assert.notNull(uploadLogRecordReq.getOperator(), "receive log, operator must not be null");
    }
}
