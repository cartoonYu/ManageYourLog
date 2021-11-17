package org.manageyourlog.server.service;

import org.manageyourlog.facade.service.ActualUploadLog;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.server.converter.service.LogRecordConverter;
import org.manageyourlog.server.model.LogRecord;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author cartoon
 * @date 2021/10/30 17:38
 */
@Service
public class SyncReceiveLog extends ReceiveLog{

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
        return true;
    }

    @Override
    protected LogRecord packLogRecord(UploadLogRecordReq req){
        LogRecord logRecord = LogRecordConverter.convert(req);
        logRecord.setCreateTime(LocalDateTime.now())
                .setModifyTime(LocalDateTime.now());
        logRecord.setIndexList(logRecord.getIndexList().stream()
                .peek(index -> index.setCreateTime(LocalDateTime.now())
                        .setModifyTime(LocalDateTime.now()))
                .collect(Collectors.toList()));
        return logRecord;
    }
}
