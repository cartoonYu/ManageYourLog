package org.manage.log.receive.provider.access.layer.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.manage.log.common.util.GsonUtil;
import org.manage.log.common.util.factory.LoadBean;
import org.manage.log.receive.facade.dto.UploadLogRecordReq;
import org.manage.log.receive.provider.service.ReceiveLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@LoadBean(loadConfigKey = "receive.log.load.mode", mode = "kafka", needPrimary = false)
public class ReceiveLogByKafka {

    private static final Logger log = LoggerFactory.getLogger(ReceiveLogByKafka.class);

    private final KafkaConsumer<String, String> consumer;

    private final ReceiveLog asyncReceiveLog;

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

    public ReceiveLogByKafka(@Qualifier("receiveByKafkaConsumer") KafkaConsumer<String, String> consumer,
                             ReceiveLog asyncReceiveLog) {
        this.consumer = consumer;
        this.asyncReceiveLog = asyncReceiveLog;
    }
}
