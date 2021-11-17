package org.manageyourlog.server.service;

import org.manageyourlog.common.constants.Error;
import org.manageyourlog.common.util.CollectionUtil;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.manageyourlog.facade.service.ActualUploadLog;
import org.manageyourlog.server.biz.LogRecordBiz;
import org.manageyourlog.server.converter.service.LogRecordConverter;
import org.manageyourlog.server.model.LogRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cartoon.yu
 * @date 2021/11/17 23:14
 */
@Service
public abstract class ReceiveLog implements ActualUploadLog {

    @Autowired
    private LogRecordBiz logRecordBiz;

    @Override
    public UploadLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        if(!judgeParamIllegal(uploadLogRecordReq)){
            return new UploadLogResp<>(Error.paramMiss);
        }
        LogRecord logRecord = packLogRecord(uploadLogRecordReq);
        boolean saveRes = logRecordBiz.saveRecord(logRecord);
        return new UploadLogResp<>(saveRes);
    }

    @Override
    public UploadLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        if(!judgeParamIllegal(uploadLogRecordReqs)){
            return new UploadLogResp<>(Error.paramMiss);
        }
        List<LogRecord> logRecords = packLogRecord(uploadLogRecordReqs);
        boolean saveRes = logRecordBiz.saveRecord(logRecords);
        return new UploadLogResp<>(saveRes);
    }

    @Override
    public boolean enable() {
        return true;
    }

    protected abstract boolean judgeParamIllegal(UploadLogRecordReq uploadLogRecordReq);

    protected abstract LogRecord packLogRecord(UploadLogRecordReq logRecord);

    private List<LogRecord> packLogRecord(List<UploadLogRecordReq> logRecords){
        return logRecords.stream().map(this::packLogRecord).collect(Collectors.toList());
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
}
