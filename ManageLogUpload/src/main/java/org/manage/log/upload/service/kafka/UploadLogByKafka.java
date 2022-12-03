package org.manage.log.upload.service.kafka;

import org.manage.log.common.util.factory.LoadBean;
import org.manage.log.upload.UploadLog;
import org.manage.log.upload.model.req.UploadLogRecordReq;
import org.manage.log.upload.model.resp.OperateLogResp;
import org.springframework.beans.factory.annotation.Autowired;
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
