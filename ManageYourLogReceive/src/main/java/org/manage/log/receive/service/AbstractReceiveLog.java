package org.manage.log.receive.service;

import org.apache.commons.collections4.CollectionUtils;
import org.manage.log.common.constants.HandleError;
import org.manage.log.common.util.GsonUtil;
import org.manage.log.receive.access.layer.builder.LogRecordBuilder;
import org.manage.log.repository.LogRecordRepository;
import org.manage.log.repository.model.LogRecord;
import org.manage.log.upload.model.req.UploadLogRecordReq;
import org.manage.log.upload.model.resp.OperateLogResp;
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
        LogRecord logRecord = LogRecordBuilder.getInstance().build(uploadLogRecordReq, getUploadTime(uploadLogRecordReq));
        //3. call repository to store
        boolean saveRes = logRecordRepository.save(logRecord);
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
        if(Objects.isNull(uploadLogRecordReq.getContent())){
            log.error("async receive log, upload req content is null, data: {}", GsonUtil.getInstance().writeJson(uploadLogRecordReq));
            return false;
        }
        if(Objects.isNull(uploadLogRecordReq.getOperator())){
            log.error("async receive log, upload req operator is null, data: {}", GsonUtil.getInstance().writeJson(uploadLogRecordReq));
            return false;
        }
        if(Objects.isNull(uploadLogRecordReq.getLogRecordSort())){
            log.error("async receive log, upload req log record sort is null, data: {}", GsonUtil.getInstance().writeJson(uploadLogRecordReq));
            return false;
        }
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
