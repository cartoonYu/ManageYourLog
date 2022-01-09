package org.manageyourlog.server.controller;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author cartoon
 * @description
 * @date 2020/3/24 8:24 PM
 */
@Component
public class TestConsumer implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(TestConsumer.class);

    private ThreadPoolExecutor singleThreadPool;

    private KafkaConsumer<String, String> consumer;

    private String topic;

    @Autowired
    @Qualifier("asyncReceiveLog")
    private ReceiveLog asyncReceiveLog;

    @Override
    public void run(ApplicationArguments args){
        singleThreadPool.execute(() -> {
            while (true){
                ConsumerRecords<String, String> sourceData = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : sourceData) {
                    String receiveData = record.value();
                    log.info("receive data: {}", receiveData);
                    UploadLogRecordReq recordReq = GsonUtil.readJsonObject(receiveData, UploadLogRecordReq.class);
                    asyncReceiveLog.receive(recordReq);
                    log.info("decode data: {}", GsonUtil.writeJson(recordReq));
                }
            }
        });
    }

    private Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "cartoon-ali.com:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 2);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 30);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    @PostConstruct
    private void init(){
        singleThreadPool = new ThreadPoolExecutor(1, 1, 60,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000), new ThreadPoolExecutor.CallerRunsPolicy());
        consumer = new KafkaConsumer<>(consumerConfigs());
        topic = "ManageYourLog";
        consumer.subscribe(Collections.singleton(topic));
    }
}
