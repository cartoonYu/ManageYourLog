package org.manageyourlog.server.controller.operate.mq;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.manageyourlog.common.util.GsonUtil;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.server.service.receive.ReceiveLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Conditional;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author cartoon
 * @description
 * @date 2020/3/24 8:24 PM
 */
@Component
@EnableScheduling
@Conditional(ReceiveLogByMqCondition.class)
public class ReceiveLogByMq{

    private static final Logger log = LoggerFactory.getLogger(ReceiveLogByMq.class);

    @Autowired
    @Qualifier("receiveByKafkaConsumer")
    private KafkaConsumer<String, String> consumer;

    @Autowired
    @Qualifier("asyncReceiveLog")
    private ReceiveLog asyncReceiveLog;

    @Scheduled(fixedDelay = 1L)
    public void execute(){
        ConsumerRecords<String, String> sourceData = consumer.poll(Duration.ofMillis(100));
        for (ConsumerRecord<String, String> record : sourceData) {
            String receiveData = record.value();
            log.info("receive data: {}", receiveData);
            UploadLogRecordReq recordReq = GsonUtil.readJsonObject(receiveData, UploadLogRecordReq.class);
            asyncReceiveLog.receive(recordReq);
        }
    }
}
