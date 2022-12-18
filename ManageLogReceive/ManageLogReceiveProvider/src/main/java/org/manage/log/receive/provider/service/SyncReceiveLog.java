package org.manage.log.receive.provider.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @date 2021/10/30 17:38
 */
@Service
public class SyncReceiveLog extends AbstractReceiveLog {

    @Override
    protected LocalDateTime getUploadTime(LocalDateTime incomingUploadTime) {
        return LocalDateTime.now();
    }
}
