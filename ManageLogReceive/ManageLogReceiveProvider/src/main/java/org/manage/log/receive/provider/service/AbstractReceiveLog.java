package org.manage.log.receive.provider.service;

import org.apache.commons.collections4.CollectionUtils;
import org.manage.log.common.constants.HandleError;
import org.manage.log.common.model.log.LogRecord;
import org.manage.log.receive.facade.dto.OperateLogResp;
import org.manage.log.receive.facade.dto.UploadLogRecordReq;
import org.manage.log.receive.provider.access.layer.builder.LogRecordBuilder;
import org.manage.log.receive.provider.repository.LogRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public OperateLogResp<Boolean> receive(UploadLogRecordReq uploadLogRecordReq) {
        //1. judge income request data illegal
        if(!judgeParamIllegal(uploadLogRecordReq)){
            return new OperateLogResp<>(HandleError.PARAM_MISS);
        }
        //2. transfer request data to domain entity
        //todo 获取 config 配置，format 具体日志，转换领域模型并落库
        LogRecord logRecord = LogRecordBuilder.getInstance().build(uploadLogRecordReq, getUploadTime(uploadLogRecordReq));
        //3. call repository to store
        //todo 转换领域模型并传参
        boolean saveRes = logRecordRepository.save(new LogRecord());
        return new OperateLogResp<>(saveRes);
    }

    @Override
    public OperateLogResp<Boolean> receive(List<UploadLogRecordReq> uploadLogRecordReqs) {
        //1. judge income request data illegal
        if(!judgeParamIllegal(uploadLogRecordReqs)){
            return new OperateLogResp<>(HandleError.PARAM_MISS);
        }
        //2. transfer request data to domain entity
        List<LogRecord> logRecords = uploadLogRecordReqs.stream().map(req -> LogRecordBuilder.getInstance().build(req, getUploadTime(req))).collect(Collectors.toList());
        //3. call repository to store
        boolean saveRes = logRecordRepository.save(logRecords);
        return new OperateLogResp<>(saveRes);
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
