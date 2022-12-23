package org.manage.log.receive.provider.service;

import com.google.common.collect.ImmutableList;
import org.apache.commons.collections4.CollectionUtils;
import org.manage.log.common.constants.HandleError;
import org.manage.log.common.constants.LogRecordIndexSort;
import org.manage.log.common.model.config.LogConfig;
import org.manage.log.common.model.log.LogRecord;
import org.manage.log.common.model.log.builder.LogRecordFactory;
import org.manage.log.receive.facade.dto.OperateLogResp;
import org.manage.log.receive.facade.dto.UploadLogRecordReq;
import org.manage.log.receive.provider.repository.LogConfigRepository;
import org.manage.log.receive.provider.repository.LogRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return receive(ImmutableList.of(uploadLogRecordReq));
    }

    @Override
    public OperateLogResp<Boolean> receive(List<UploadLogRecordReq> uploadLogRecordReqs) {
        //judge income request data illegal
        if(!judgeParamIllegal(uploadLogRecordReqs)){
            return new OperateLogResp<>(HandleError.PARAM_MISS);
        }
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
                //logConfig miss log template define todo
                String content = "";
                //uploadReq miss operator define todo
                String operator = "";
                //uploadReq miss index value define todo
                Map<String, LogRecordIndexSort> indexValueToIndexSortMap = new HashMap<>();
                return logRecordFactory.build(content, logConfig.getOperatorSort(), operator,
                                                                config.getLogRecordSort(), indexValueToIndexSortMap);

            }).orElse(null);
        }).filter(Objects::nonNull).toList();
    }

    protected boolean judgeParamIllegal(UploadLogRecordReq uploadLogRecordReq){
        if(Objects.isNull(uploadLogRecordReq)){
            log.error("async receive log, upload req is null");
            return false;
        }
        //todo 参数校验
        return true;
    }

    protected abstract LocalDateTime getUploadTime(LocalDateTime incomingUploadTime);

    private boolean judgeParamIllegal(List<UploadLogRecordReq> uploadLogRecordReqs){
        if(CollectionUtils.isEmpty(uploadLogRecordReqs)){
            return false;
        }
        for(UploadLogRecordReq req : uploadLogRecordReqs){
            if(!judgeParamIllegal(req)){
                return false;
            }
        }
        return true;
    }
}
