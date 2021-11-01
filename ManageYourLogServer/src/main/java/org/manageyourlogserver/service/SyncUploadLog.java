package org.manageyourlogserver.service;

import org.manageyourlogcommon.constants.Error;
import org.manageyourlogcommon.util.CollectionUtil;
import org.manageyourlogfacade.UploadLog;
import org.manageyourlogfacade.model.req.UploadLogRecordReq;
import org.manageyourlogfacade.model.resp.UploadLogResp;
import org.manageyourlogserver.biz.LogRecordBiz;
import org.manageyourlogserver.converter.service.LogRecordConverter;
import org.manageyourlogserver.model.LogRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author cartoon
 * @date 2021/10/30 17:38
 */
@Service("syncUploadLog")
public class SyncUploadLog implements UploadLog {

    @Autowired
    private LogRecordBiz logRecordBiz;

    @Override
    public UploadLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        if(!judgeParamIllegal(uploadLogRecordReq)){
            return new UploadLogResp<>(Error.paramMiss);
        }
        LogRecord logRecord = LogRecordConverter.convert(uploadLogRecordReq);
        logRecord = packLogRecord(logRecord);
        boolean saveRes = logRecordBiz.saveRecord(logRecord);
        return new UploadLogResp<>(saveRes);
    }

    @Override
    public UploadLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        if(!judgeParamIllegal(uploadLogRecordReqs)){
            return new UploadLogResp<>(Error.paramMiss);
        }
        List<LogRecord> logRecords = LogRecordConverter.convert(uploadLogRecordReqs);
        logRecords = packLogRecord(logRecords);
        boolean saveRes = logRecordBiz.saveRecord(logRecords);
        return new UploadLogResp<>(saveRes);
    }

    private List<LogRecord> packLogRecord(List<LogRecord> logRecords){
        return logRecords.stream().map(this::packLogRecord).collect(Collectors.toList());
    }

    private LogRecord packLogRecord(LogRecord logRecord){
        logRecord.setCreateTime(LocalDateTime.now())
                .setModifyTime(LocalDateTime.now());
        return logRecord;
    }

    private boolean judgeParamIllegal(List<UploadLogRecordReq> uploadLogRecordReqs){
        if(CollectionUtil.judgeIsEmpty(uploadLogRecordReqs)){
            return false;
        }
        for(UploadLogRecordReq req : uploadLogRecordReqs){
            if(!judgeParamIllegal(req)){
                return false;
            }
        }
        return true;
    }

    private boolean judgeParamIllegal(UploadLogRecordReq uploadLogRecordReq){
        if(Objects.isNull(uploadLogRecordReq)){
            return false;
        }
        if(Objects.isNull(uploadLogRecordReq.getContent())){
            return false;
        }
        if(Objects.isNull(uploadLogRecordReq.getOperator())){
            return false;
        }
        if(Objects.isNull(uploadLogRecordReq.getLogRecordSort())){
            return false;
        }
        return true;
    }
}
