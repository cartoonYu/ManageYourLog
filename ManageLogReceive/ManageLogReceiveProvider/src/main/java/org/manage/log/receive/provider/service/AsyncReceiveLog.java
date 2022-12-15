package org.manage.log.receive.provider.service;

import org.manage.log.receive.facade.dto.UploadLogRecordReq;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @date 2021/11/17 23:10
 */
@Service
public class AsyncReceiveLog extends AbstractReceiveLog {

    @Override
    protected LocalDateTime getUploadTime(UploadLogRecordReq uploadLogRecordReq) {
        return uploadLogRecordReq.getUploadTime();
    }
}
