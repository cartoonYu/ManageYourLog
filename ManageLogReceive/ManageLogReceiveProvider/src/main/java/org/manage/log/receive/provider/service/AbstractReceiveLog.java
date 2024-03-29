package org.manage.log.receive.provider.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.manage.log.common.constants.HandleError;
import org.manage.log.common.model.log.LogRecordIndex;
import org.manage.log.common.model.log.builder.LogIndexFactory;
import org.manage.log.common.model.log.constants.LogRecordIndexSort;
import org.manage.log.common.model.config.LogConfig;
import org.manage.log.common.model.config.LogIndexConfig;
import org.manage.log.common.model.log.LogRecord;
import org.manage.log.common.model.log.builder.LogRecordFactory;
import org.manage.log.common.util.GsonUtil;
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

    private final LogIndexFactory logIndexFactory;

    @Override
    public OperateLogResp<Boolean> receive(UploadLogRecordReq uploadLogRecordReq) {
        return receive(List.of(uploadLogRecordReq));
    }

    @Override
    public OperateLogResp<Boolean> receive(List<UploadLogRecordReq> uploadLogRecordList) {
        //judge income request data illegal
        try {
            judgeParamIllegal(uploadLogRecordList);
        } catch (IllegalArgumentException e){
            return new OperateLogResp<>(HandleError.PARAM_MISS.getCode(), e.getMessage());
        }

        //transfer request data to domain entity
        //get log config from repository
        List<String> configNameList = uploadLogRecordList.parallelStream().map(UploadLogRecordReq::getConfigName).toList();
        List<LogConfig> configList = logConfigService.getByConfigNameList(configNameList);
        //format log by log formatter and value list, construct log index list
        List<LogRecord> logRecordList = executeLog(configList, uploadLogRecordList);
        //call repository to store
        boolean saveRes = logRecordRepository.save(logRecordList);
        return new OperateLogResp<>(saveRes);
    }

    private List<LogRecord> executeLog(List<LogConfig> configList, List<UploadLogRecordReq> uploadLogRecordReqList){
        Map<String, LogConfig> configNameToConfig = configList.parallelStream().collect(Collectors.toMap(LogConfig::ruleName, Function.identity()));
        //format log content and others, call factory to build domain object
        return uploadLogRecordReqList.parallelStream().map(uploadReq -> {
            return ofNullable(configNameToConfig.get(uploadReq.getConfigName())).map(config -> {
                //format content by content template
                Map<String, String> valuePropertyToValueMap = getValueProperty(uploadReq.getValueData());
                String content = logConfigService.formatContent(config, valuePropertyToValueMap);
                //get index value from value list according by index config
                List<LogRecordIndex> indexList = getIndexList(config, valuePropertyToValueMap);
                return logRecordFactory.build(content, config.operatorSort(), uploadReq.getOperator(),
                                                                config.logRecordSort(), indexList,
                                            getUploadTime(uploadReq));

            }).orElse(null);
        }).filter(Objects::nonNull).toList();
    }

    private List<LogRecordIndex> getIndexList(LogConfig logConfig, Map<String, String> valuePropertyToValueMap){
        return logConfig.indexConfigList().parallelStream()
                .map(indexConfig -> logIndexFactory.build(indexConfig.logRecordIndexSort(), valuePropertyToValueMap.get(indexConfig.valueIndexKey())))
                .toList();
    }

    private Map<String, String> getValueProperty(String valueData){
        Map<String, String> valuePropertyMap = new HashMap<>(16);
        getValueProperty(GsonUtil.getInstance().getJson(valueData), "", valuePropertyMap);
        return valuePropertyMap;
    }

    private void getValueProperty(JsonElement valueData, String prefixKey, Map<String, String> valuePropertyMap){
        if(valueData.isJsonArray()){
            valueData.getAsJsonArray().asList().forEach(obj -> getValueProperty(obj, prefixKey, valuePropertyMap));
        }
        if (valueData.isJsonObject()) {
            JsonObject jsonObject = valueData.getAsJsonObject();
            for(String valueKey : jsonObject.keySet()){
                JsonElement subObject = jsonObject.get(valueKey);
                if(subObject.isJsonPrimitive()){
                    valuePropertyMap.put(prefixKey + valueKey, subObject.getAsJsonPrimitive().getAsString());
                } else {
                    getValueProperty(subObject, String.format("%s.%s.", prefixKey, valueKey), valuePropertyMap);
                }
            }
        }
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
                                LogRecordFactory logRecordFactory,
                                LogIndexFactory logIndexFactory) {
        this.logRecordRepository = logRecordRepository;
        this.logConfigService = logConfigService;
        this.logRecordFactory = logRecordFactory;
        this.logIndexFactory = logIndexFactory;
    }
}
