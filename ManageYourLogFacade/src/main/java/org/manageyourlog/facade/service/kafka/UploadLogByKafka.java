package org.manageyourlog.facade.service.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.manageyourlog.common.util.GsonUtil;
import org.manageyourlog.facade.UploadLog;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author cartoon
 * @date 2022/1/9 15:23
 */
@Service
@Conditional(UploadLogByKafkaLoadCondition.class)
public class UploadLogByKafka implements UploadLog {

    @Autowired
    private UploadLogByKafkaConfig uploadLogByKafkaConfig;

    @Override
    public UploadLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        uploadLogRecordReq.setUploadTime(LocalDateTime.now());
        String dataStr = GsonUtil.getInstance().writeJson(uploadLogRecordReq);
        uploadLogByKafkaConfig.sendMessage(dataStr, log);
        return new UploadLogResp<>(true);
    }

    @Override
    public UploadLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        uploadLogRecordReqs.forEach(this::upload);
        return new UploadLogResp<>(true);
    }
}
