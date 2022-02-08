package org.manage.log.server.receive.access.layer.kafka;

import com.google.common.collect.ImmutableList;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.manage.log.server.receive.access.layer.ReceiveLogMode;
import org.manage.log.common.util.config.ApplicationConfigUtil;
import org.manage.log.server.config.ApplicationConfigKey;
import org.manage.log.server.receive.access.layer.ReceiveLogLoadCondition;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.Properties;

/**
 * @author cartoon
 * @date 2022/1/10 00:07
 */
@Component
@ReceiveLogLoadCondition(mode = ReceiveLogMode.mq)
public class ReceiveLogByKafkaConfig implements DisposableBean {

    @Autowired
    private ApplicationConfigUtil applicationConfigUtil;

    private KafkaConsumer<String, String> kafkaConsumer;

    @Bean(name = "receiveByKafkaConsumer")
    public KafkaConsumer<String, String> consumer(@Qualifier("receiveByKafkaConfig") Properties config){
        Properties consumeConfig = getConsumeConfig(config);
        kafkaConsumer = new KafkaConsumer<>(consumeConfig);
        applicationConfigUtil.get(ApplicationConfigKey.receiveLogKafkaTopic.getKey(), (topic) -> kafkaConsumer.subscribe(ImmutableList.of(topic)));
        return kafkaConsumer;
    }

    private Properties getConsumeConfig(Properties config){
        Properties properties = new Properties(config);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.putAll(config);
        return properties;
    }

    @Bean(name = "receiveByKafkaConfig")
    @ConfigurationProperties(prefix = "receive.log.kafka")
    public Properties kafkaConfig(){
        return new Properties();
    }

    @Override
    public void destroy() {
        kafkaConsumer.unsubscribe();
        kafkaConsumer.close();
    }
}
