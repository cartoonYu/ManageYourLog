package org.manageyourlog.server.service.receive;

import org.manageyourlog.common.util.GsonUtil;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.server.converter.service.LogRecordConverter;
import org.manageyourlog.server.model.LogRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author cartoon
 * @date 2021/11/17 23:10
 */
@Service
public class AsyncReceiveLog extends ReceiveLogImpl {

    private static final Logger log = LoggerFactory.getLogger(SyncReceiveLog.class);

    @Override
    protected boolean judgeParamIllegal(UploadLogRecordReq uploadLogRecordReq) {
        if(Objects.isNull(uploadLogRecordReq)){
            log.error("async receive log, upload req is null");
            return false;
        }
        if(Objects.isNull(uploadLogRecordReq.getContent())){
            log.error("async receive log, upload req content is null, data: {}", GsonUtil.writeJson(uploadLogRecordReq));
            return false;
        }
        if(Objects.isNull(uploadLogRecordReq.getOperator())){
            log.error("async receive log, upload req operator is null, data: {}", GsonUtil.writeJson(uploadLogRecordReq));
            return false;
        }
        if(Objects.isNull(uploadLogRecordReq.getLogRecordSort())){
            log.error("async receive log, upload req log record sort is null, data: {}", GsonUtil.writeJson(uploadLogRecordReq));
            return false;
        }
        if(Objects.isNull(uploadLogRecordReq.getUploadTime())){
            log.error("async receive log, upload req log upload time is null, data: {}", GsonUtil.writeJson(uploadLogRecordReq));
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
