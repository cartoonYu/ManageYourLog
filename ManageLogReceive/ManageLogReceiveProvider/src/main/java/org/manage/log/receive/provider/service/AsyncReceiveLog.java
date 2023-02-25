package org.manage.log.receive.provider.service;

import org.manage.log.common.model.log.builder.LogIndexFactory;
import org.manage.log.common.model.log.builder.LogRecordFactory;
import org.manage.log.receive.facade.dto.UploadLogRecordReq;
import org.manage.log.receive.provider.repository.LogRecordRepository;
import org.manage.log.receive.provider.service.config.LogConfigService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @date 2021/11/17 23:10
 */
@Service
public class AsyncReceiveLog extends AbstractReceiveLog {

    @Override
    protected LocalDateTime getUploadTime(UploadLogRecordReq request) {
        return request.getUploadTime();
    }

    @Override
    protected void judgeParamIllegal(UploadLogRecordReq uploadLogRecordReq) {
        super.judgeParamIllegal(uploadLogRecordReq);
        Assert.notNull(uploadLogRecordReq.getUploadTime(), "upload time must not be null");
    }

    public AsyncReceiveLog(LogRecordRepository logRecordRepository,
                           LogConfigService logConfigService,
                           LogRecordFactory logRecordFactory,
                           LogIndexFactory logIndexFactory) {
        super(logRecordRepository, logConfigService, logRecordFactory, logIndexFactory);
    }
}
