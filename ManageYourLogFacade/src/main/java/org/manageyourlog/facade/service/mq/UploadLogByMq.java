package org.manageyourlog.facade.service.mq;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.manageyourlog.common.util.GsonUtil;
import org.manageyourlog.facade.UploadLog;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

/**
 * @author cartoon
 * @date 2022/1/9 15:23
 */
@Service
@Conditional(UploadLogByMqLoadCondition.class)
public class UploadLogByMq implements UploadLog {

    @Autowired
    private UploadLogByMqConfig uploadLogByMQConfig;

    private String sendTopic;

    private KafkaProducer<String, String> sender;

    @Override
    public UploadLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        uploadLogRecordReq.setUploadTime(LocalDateTime.now());
        String dataStr = GsonUtil.writeJson(uploadLogRecordReq);
        log.info("send data: {}", dataStr);
        sender.send(new ProducerRecord<>(sendTopic, dataStr));
        return new UploadLogResp<>(true);
    }

    @Override
    public UploadLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        uploadLogRecordReqs.forEach(this::upload);
        return new UploadLogResp<>(true);
    }

    @PostConstruct
    private void init(){
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, uploadLogByMQConfig.getBaseUrl());
        props.put("retries", 0);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        sender = new KafkaProducer<>(props);
        sendTopic = uploadLogByMQConfig.getTopic();
    }
}
