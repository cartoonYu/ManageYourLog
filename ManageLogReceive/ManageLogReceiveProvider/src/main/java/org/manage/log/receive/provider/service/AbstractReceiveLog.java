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
        //1. judge income request data illegal
        if(!judgeParamIllegal(uploadLogRecordReqs)){
            return new OperateLogResp<>(HandleError.PARAM_MISS);
        }
        //2. transfer request data to domain entity
        //todo 获取 config 配置，format 具体日志，转换领域模型并落库
        List<LogRecord> logRecords = uploadLogRecordReqs.stream().map(req -> LogRecordBuilder.getInstance().build(req, getUploadTime(req))).collect(Collectors.toList());
        //3. call repository to store
        boolean saveRes = logRecordRepository.save(logRecords);
        return new OperateLogResp<>(saveRes);
    }

    private List<LogRecord> execute(List<LogConfig> logConfigs){
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

    protected abstract LocalDateTime getUploadTime(UploadLogRecordReq uploadLogRecordReq);

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
