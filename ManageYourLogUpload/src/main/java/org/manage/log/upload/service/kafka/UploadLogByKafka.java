package org.manage.log.upload.service.kafka;

import org.manage.log.common.util.factory.InitPrimary;
import org.manage.log.upload.UploadLog;
import org.manage.log.upload.model.req.UploadLogRecordReq;
import org.manage.log.upload.model.resp.OperateLogResp;
import org.manage.log.upload.service.UploadLogByDefault;
import org.manage.log.upload.service.factory.UploadLogLoadCondition;
import org.manage.log.upload.service.factory.UploadLogMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author cartoon
 * @date 2022/1/9 15:23
 */
@Service
@InitPrimary(configKey = "upload.log.mode", mode = "kafka", defaultClass = UploadLogByDefault.class, implementClass = UploadLog.class)
@UploadLogLoadCondition(mode = UploadLogMode.kafka)
public class UploadLogByKafka implements UploadLog {

    @Autowired
    private UploadLogByKafkaConfig uploadLogByKafkaConfig;

    @Override
    public OperateLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        uploadLogRecordReq.setUploadTime(LocalDateTime.now());
        uploadLogByKafkaConfig.sendMessage(uploadLogRecordReq, log);
        return new OperateLogResp<>(true);
    }

    @Override
    public OperateLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        uploadLogRecordReqs.forEach(this::upload);
        return new OperateLogResp<>(true);
    }
}
