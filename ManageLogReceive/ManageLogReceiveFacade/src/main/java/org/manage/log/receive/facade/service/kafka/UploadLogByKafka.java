package org.manage.log.receive.facade.service.kafka;

import org.manage.log.common.util.factory.LoadBean;
import org.manage.log.receive.facade.UploadLog;
import org.manage.log.receive.facade.dto.OperateLogResp;
import org.manage.log.receive.facade.dto.UploadLogRecordReq;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author cartoon
 * @date 2022/1/9 15:23
 */
@Service
@LoadBean(primaryConfigKey = "upload.log.mode", loadConfigKey = "upload.log.mode", mode = "kafka")
public class UploadLogByKafka implements UploadLog {

    private final UploadLogByKafkaConfig uploadLogByKafkaConfig;

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

    public UploadLogByKafka(UploadLogByKafkaConfig uploadLogByKafkaConfig) {
        this.uploadLogByKafkaConfig = uploadLogByKafkaConfig;
    }
}
