package org.manage.log.server.receive.service;

import org.manage.log.facade.model.req.UploadLogRecordReq;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @date 2021/10/30 17:38
 */
@Service
public class SyncReceiveLog extends AbstractReceiveLog {

    @Override
    protected LocalDateTime getUploadTime(UploadLogRecordReq uploadLogRecordReq) {
        return LocalDateTime.now();
    }
}
