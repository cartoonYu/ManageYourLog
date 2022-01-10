package org.manageyourlog.server.controller.operate.mq;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.manageyourlog.common.util.GsonUtil;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.server.service.receive.ReceiveLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.*;

/**
 * @author cartoon
 * @description
 * @date 2020/3/24 8:24 PM
 */
@Component
@EnableScheduling
public class ReceiveLogByMq implements DisposableBean {

    private static final Logger log = LoggerFactory.getLogger(ReceiveLogByMq.class);

    @Autowired
    private ReceiveLogByMqConfig config;

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

    @Override
    public void destroy() throws Exception {
        consumer.close();
    }

    private Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getConsumeUrl());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, config.getConsumeTopic());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 2);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 30);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    @PostConstruct
    private void init(){
        consumer = new KafkaConsumer<>(consumerConfigs());
        consumer.subscribe(Collections.singleton(config.getConsumeTopic()));
    }
}
