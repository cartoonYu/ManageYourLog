package org.manageyourlog.server.service.receive;

import org.manageyourlog.common.util.GsonUtil;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.server.model.builder.service.LogRecordConverter;
import org.manageyourlog.server.model.LogRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author cartoon
 * @date 2021/10/30 17:38
 */
@Service
public class SyncReceiveLog extends AbstractReceiveLog {

    private static final Logger log = LoggerFactory.getLogger(SyncReceiveLog.class);

    @Override
    protected boolean judgeParamIllegal(UploadLogRecordReq uploadLogRecordReq) {
        if(Objects.isNull(uploadLogRecordReq)){
            log.error("sync receive log, upload req is null");
            return false;
        }
        if(Objects.isNull(uploadLogRecordReq.getContent())){
            log.error("sync receive log, upload req content is null, data: {}", GsonUtil.writeJson(uploadLogRecordReq));
            return false;
        }
        if(Objects.isNull(uploadLogRecordReq.getOperator())){
            log.error("sync receive log, upload req operator is null, data: {}", GsonUtil.writeJson(uploadLogRecordReq));
            return false;
        }
        if(Objects.isNull(uploadLogRecordReq.getLogRecordSort())){
            log.error("sync receive log, upload req log record sort is null, data: {}", GsonUtil.writeJson(uploadLogRecordReq));
            return false;
        }
        return true;
    }

    @Override
    protected LogRecord packLogRecord(UploadLogRecordReq req){
        LogRecord logRecord = LogRecordConverter.getInstance().convert(req);
        logRecord.setCreateTime(LocalDateTime.now())
                .setModifyTime(LocalDateTime.now());
        logRecord.setIndexList(logRecord.getIndexList().stream()
                .peek(index -> index.setCreateTime(LocalDateTime.now())
                        .setModifyTime(LocalDateTime.now()))
                .collect(Collectors.toList()));
        return logRecord;
    }
}
