package org.manage.log.receive.provider.service;

import org.manage.log.common.model.log.builder.LogIndexFactory;
import org.manage.log.common.model.log.builder.LogRecordFactory;
import org.manage.log.receive.facade.dto.UploadLogRecordReq;
import org.manage.log.receive.provider.repository.LogRecordRepository;
import org.manage.log.receive.provider.service.config.LogConfigService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @date 2021/10/30 17:38
 */
@Service
public class SyncReceiveLog extends AbstractReceiveLog {

    @Override
    protected LocalDateTime getUploadTime(UploadLogRecordReq request) {
        return LocalDateTime.now();
    }

    public SyncReceiveLog(LogRecordRepository logRecordRepository, LogConfigService logConfigService,
                          LogRecordFactory logRecordFactory, LogIndexFactory logIndexFactory) {
        super(logRecordRepository, logConfigService, logRecordFactory, logIndexFactory);
    }
}
