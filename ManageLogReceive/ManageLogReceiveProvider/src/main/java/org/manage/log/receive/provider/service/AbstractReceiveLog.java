package org.manage.log.receive.provider.service;

import com.google.common.collect.ImmutableList;
import org.apache.commons.collections4.CollectionUtils;
import org.manage.log.common.constants.HandleError;
import org.manage.log.common.model.config.LogConfig;
import org.manage.log.common.model.log.LogRecord;
import org.manage.log.receive.facade.dto.OperateLogResp;
import org.manage.log.receive.facade.dto.UploadLogRecordReq;
import org.manage.log.receive.provider.access.layer.builder.LogRecordBuilder;
import org.manage.log.receive.provider.repository.LogConfigRepository;
import org.manage.log.receive.provider.repository.LogRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        //format log by log formatter and value list, construct log index list todo
        //transfer to log domain model todo
        List<LogRecord> logRecords = uploadLogRecordReqs.stream().map(req -> LogRecordBuilder.getInstance().build(req)).collect(Collectors.toList());
        //call repository to store
        boolean saveRes = logRecordRepository.save(logRecords);
        return new OperateLogResp<>(saveRes);
    }

    private List<LogRecord> executeLog(List<LogConfig> logConfigs, List<List<String>> values, List<LocalDateTime> uploadTimeList){
        Assert.isTrue(logConfigs.size() == values.size(), "receive log, config list size must equals value list size");
        Assert.isTrue(logConfigs.size() == uploadTimeList.size(), "receive log, config list size must equals upload time list size");
        for(int index = 0; index < logConfigs.size(); index++){
            LogConfig logConfig = logConfigs.get(index);
            List<String> valueList = values.get(index);
            LocalDateTime uploadTime = uploadTimeList.get(index);

        }
        return new ArrayList<>();
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
