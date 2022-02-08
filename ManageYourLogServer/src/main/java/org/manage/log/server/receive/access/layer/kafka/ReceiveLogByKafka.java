package org.manage.log.server.receive.access.layer.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.manage.log.server.receive.access.layer.ReceiveLogMode;
import org.manage.log.server.receive.service.ReceiveLog;
import org.manage.log.common.util.GsonUtil;
import org.manage.log.facade.model.req.UploadLogRecordReq;
import org.manage.log.server.receive.access.layer.ReceiveLogLoadCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * receive log by kafka
 * @author cartoon
 * @description
 * @date 2020/3/24 8:24 PM
 */
@Component
@EnableScheduling
@ReceiveLogLoadCondition(mode = ReceiveLogMode.mq)
public class ReceiveLogByKafka {

    private static final Logger log = LoggerFactory.getLogger(ReceiveLogByKafka.class);

    @Autowired
    @Qualifier("receiveByKafkaConsumer")
    private KafkaConsumer<String, String> consumer;

    @Autowired
    @Qualifier("asyncReceiveLog")
    private ReceiveLog asyncReceiveLog;

    @Scheduled(fixedDelayString = "${receive.log.kafka.rate}")
    public void execute(){
        ConsumerRecords<String, String> sourceData = consumer.poll(Duration.ofMillis(0));
        for (ConsumerRecord<String, String> record : sourceData) {
            String receiveData = record.value();
            if(log.isInfoEnabled()){
                log.info("receive data: {}, time: {}", receiveData, LocalDateTime.now());
            }
            UploadLogRecordReq recordReq = GsonUtil.getInstance().readJsonObject(receiveData, UploadLogRecordReq.class);
            asyncReceiveLog.receive(recordReq);
        }
    }
}
