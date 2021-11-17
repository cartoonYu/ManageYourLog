package org.manageyourlog.server.service;

import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.service.ActualUploadLog;
import org.manageyourlog.server.converter.service.LogRecordConverter;
import org.manageyourlog.server.model.LogRecord;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author cartoon
 * @date 2021/11/17 23:10
 */
@Service
public class AsyncReceiveLog extends ReceiveLog {

    @Override
    protected boolean judgeParamIllegal(UploadLogRecordReq uploadLogRecordReq) {
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
        if(Objects.isNull(uploadLogRecordReq.getUploadTime())){
            return false;
        }
        return true;
    }

    @Override
    protected LogRecord packLogRecord(UploadLogRecordReq req) {
        LogRecord logRecord = LogRecordConverter.convert(req);
        logRecord.setCreateTime(req.getUploadTime())
                .setModifyTime(req.getUploadTime());
        logRecord.setIndexList(logRecord.getIndexList().stream()
                .peek(index -> index.setCreateTime(req.getUploadTime())
                        .setModifyTime(req.getUploadTime()))
                .collect(Collectors.toList()));
        return logRecord;
    }
}
