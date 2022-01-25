package org.manageyourlog.server.service.receive;

import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author cartoon
 * @date 2021/11/17 23:10
 */
@Service
public class AsyncReceiveLog extends AbstractReceiveLog {

    @Override
    protected boolean judgeParamIllegal(UploadLogRecordReq uploadLogRecordReq) {
        return super.judgeParamIllegal(uploadLogRecordReq) && !Objects.isNull(uploadLogRecordReq.getUploadTime());
    }

    @Override
    protected LocalDateTime getUploadTime(UploadLogRecordReq uploadLogRecordReq) {
        return uploadLogRecordReq.getUploadTime();
    }
}
