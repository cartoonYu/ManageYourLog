package org.manage.log.receive.provider.service;

import org.manage.log.common.constants.HandleError;
import org.manage.log.common.model.log.constants.LogRecordIndexSort;
import org.manage.log.common.model.config.LogConfig;
import org.manage.log.common.model.config.LogIndexConfig;
import org.manage.log.common.model.log.LogRecord;
import org.manage.log.common.model.log.builder.LogRecordFactory;
import org.manage.log.receive.facade.dto.OperateLogResp;
import org.manage.log.receive.facade.dto.UploadLogRecordReq;
import org.manage.log.receive.provider.repository.LogRecordRepository;
import org.manage.log.receive.provider.service.config.LogConfigService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
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

    private final LogRecordRepository logRecordRepository;

    private final LogConfigService logConfigService;

    private final LogRecordFactory logRecordFactory;

    @Override
    public OperateLogResp<Boolean> receive(UploadLogRecordReq uploadLogRecordReq) {
        return receive(List.of(uploadLogRecordReq));
    }

    @Override
    public OperateLogResp<Boolean> receive(List<UploadLogRecordReq> uploadLogRecordReqs) {
        //judge income request data illegal
        try {
            judgeParamIllegal(uploadLogRecordReqs);
        } catch (IllegalArgumentException e){
            return new OperateLogResp<>(HandleError.PARAM_MISS.getCode(), e.getMessage());
        }

        //transfer request data to domain entity
        //get log config from repository
        List<String> configNameList = uploadLogRecordReqs.stream().map(UploadLogRecordReq::getConfigName).toList();
        List<LogConfig> configList = logConfigService.getByConfigNameList(configNameList);
        //format log by log formatter and value list, construct log index list
        List<LogRecord> logRecordList = executeLog(configList, uploadLogRecordReqs);
        //call repository to store
        boolean saveRes = logRecordRepository.save(logRecordList);
        return new OperateLogResp<>(saveRes);
    }

    private List<LogRecord> executeLog(List<LogConfig> configList, List<UploadLogRecordReq> uploadLogRecordReqList){
        Map<String, LogConfig> configNameToConfig = configList.stream().collect(Collectors.toMap(LogConfig::ruleName, Function.identity()));
        //format log content and others, call factory to build domain object
        return uploadLogRecordReqList.stream().map(uploadReq -> {
            return ofNullable(configNameToConfig.get(uploadReq.getConfigName())).map(config -> {
                //format content by content template
                Map<String, String> valuePropertyToValueMap = getValueProperty(uploadReq.getValueData());
                String content = logConfigService.formatContent(config, valuePropertyToValueMap);
                //get index value from value list according by index config
                Map<String, LogRecordIndexSort> indexValueToIndexSortMap = new HashMap<>(config.indexConfigList().size());
                for(LogIndexConfig logIndexConfig : config.indexConfigList()){
                    indexValueToIndexSortMap.put(valuePropertyToValueMap.get(logIndexConfig.valueIndexKey()), logIndexConfig.logRecordIndexSort());
                }
                return logRecordFactory.build(content, config.operatorSort(), uploadReq.getOperator(),
                                                                config.logRecordSort(), indexValueToIndexSortMap,
                                            getUploadTime(uploadReq));

            }).orElse(null);
        }).filter(Objects::nonNull).toList();
    }

    private Map<String, String> getValueProperty(String valueData){
        //todo need to write recursion algorithm to get value property from incoming json data
        return new HashMap<>(0);
    }

    protected abstract LocalDateTime getUploadTime(UploadLogRecordReq request);

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

    public AbstractReceiveLog(LogRecordRepository logRecordRepository,
                                LogConfigService logConfigService,
                                LogRecordFactory logRecordFactory) {
        this.logRecordRepository = logRecordRepository;
        this.logConfigService = logConfigService;
        this.logRecordFactory = logRecordFactory;
    }
}
