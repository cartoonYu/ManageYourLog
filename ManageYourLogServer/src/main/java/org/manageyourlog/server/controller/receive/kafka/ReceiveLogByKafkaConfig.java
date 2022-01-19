package org.manageyourlog.server.controller.receive.kafka;

import com.google.common.collect.ImmutableList;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.manageyourlog.common.config.ApplicationConfig;
import org.manageyourlog.server.config.ApplicationConfigKey;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import java.util.Properties;

/**
 * @author cartoon
 * @date 2022/1/10 00:07
 */
@Component
@Conditional(ReceiveLogByKafkaCondition.class)
public class ReceiveLogByKafkaConfig implements DisposableBean {

    @Autowired
    private ApplicationConfig applicationConfig;

    private KafkaConsumer<String, String> kafkaConsumer;

    @Bean(name = "receiveByKafkaConsumer")
    public KafkaConsumer<String, String> consumer(@Qualifier("receiveByKafkaConfig") Properties config){
        String topic = applicationConfig.get(ApplicationConfigKey.receiveLogKafkaTopic.getKey()).orElse(null);
        assert topic != null;
        Properties consumeConfig = getConsumeConfig(config);
        kafkaConsumer = new KafkaConsumer<>(consumeConfig);
        kafkaConsumer.subscribe(ImmutableList.of(topic));
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
