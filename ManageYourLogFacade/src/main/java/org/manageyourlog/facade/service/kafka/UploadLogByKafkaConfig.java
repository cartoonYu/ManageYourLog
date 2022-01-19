package org.manageyourlog.facade.service.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.manageyourlog.common.config.ApplicationConfig;
import org.manageyourlog.facade.config.ApplicationConfigKey;
import org.slf4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * @author cartoon
 * @date 2022/1/13 20:54
 */
@Component
@Conditional(UploadLogByKafkaLoadCondition.class)
public class UploadLogByKafkaConfig implements DisposableBean {

    @Autowired
    private ApplicationConfig applicationConfig;

    private String topic;

    private KafkaProducer<String, String> kafkaProducer;

    public boolean sendMessage(String dataStr, Logger logger){
        if(logger.isInfoEnabled()){
            logger.info("send message though kafka: {}", dataStr);
        }
        kafkaProducer.send(new ProducerRecord<>(topic, dataStr));
        return true;
    }

    @Bean(name = "uploadByKafkaProducer")
    public KafkaProducer<String, String> producer(@Qualifier("uploadByKafkaConfig") Properties producerConfig){
        kafkaProducer = new KafkaProducer<>(getProducerConfig(producerConfig));
        return kafkaProducer;
    }

    private Properties getProducerConfig(Properties producerConfig){
        Properties props = new Properties();
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.putAll(producerConfig);
        return props;
    }

    @Bean(name = "uploadByKafkaConfig")
    @ConfigurationProperties(prefix = "upload.log.kafka")
    public Properties kafkaConfig(){
        return new Properties();
    }

    @Override
    public void destroy(){
        kafkaProducer.flush();
        kafkaProducer.close();
    }

    @PostConstruct
    private void init(){
        applicationConfig.get(ApplicationConfigKey.uploadLogKafkaTopic.getKey()).ifPresent(topicValue -> this.topic = topicValue);
    }
}
