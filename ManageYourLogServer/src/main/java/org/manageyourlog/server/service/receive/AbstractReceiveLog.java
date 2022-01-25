package org.manageyourlog.server.service.receive;

import org.manageyourlog.common.constants.Error;
import org.manageyourlog.common.util.CollectionUtil;
import org.manageyourlog.common.util.GsonUtil;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.manageyourlog.server.model.LogRecord;
import org.manageyourlog.server.model.builder.LogRecordBuilder;
import org.manageyourlog.server.repository.LogRecordRepository;
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
    public UploadLogResp<Boolean> receive(UploadLogRecordReq uploadLogRecordReq) {
        //1. judge income request data illegal
        if(!judgeParamIllegal(uploadLogRecordReq)){
            return new UploadLogResp<>(Error.paramMiss);
        }
        //2. transfer request data to domain entity
        LogRecord logRecord = LogRecordBuilder.getInstance().build(uploadLogRecordReq, getUploadTime(uploadLogRecordReq));
        //3. call repository to store
        boolean saveRes = logRecordRepository.save(logRecord);
        return new UploadLogResp<>(saveRes);
    }

    @Override
    public UploadLogResp<Boolean> receive(List<UploadLogRecordReq> uploadLogRecordReqs) {
        //1. judge income request data illegal
        if(!judgeParamIllegal(uploadLogRecordReqs)){
            return new UploadLogResp<>(Error.paramMiss);
        }
        //2. transfer request data to domain entity
        List<LogRecord> logRecords = uploadLogRecordReqs.stream().map(req -> LogRecordBuilder.getInstance().build(req, getUploadTime(req))).collect(Collectors.toList());
        //3. call repository to store
        boolean saveRes = logRecordRepository.save(logRecords);
        return new UploadLogResp<>(saveRes);
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
        if(CollectionUtil.getInstance().judgeIsEmpty(uploadLogRecordReqs)){
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
