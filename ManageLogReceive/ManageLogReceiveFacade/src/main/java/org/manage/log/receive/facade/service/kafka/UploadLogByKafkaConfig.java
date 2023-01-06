package org.manage.log.receive.facade.service.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.manage.log.common.util.GsonUtil;
import org.manage.log.common.util.config.ApplicationConfigUtil;
import org.manage.log.common.util.factory.LoadBean;
import org.manage.log.receive.facade.config.ApplicationConfigKey;
import org.slf4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author cartoon
 * @date 2022/1/13 20:54
 */
@Component
@LoadBean(loadConfigKey = "upload.log.mode", mode = "kafka", needPrimary = false)
public class UploadLogByKafkaConfig implements DisposableBean {

    private ApplicationConfigUtil applicationConfigUtil;

    private static final String TOPIC = "ManageLog";

    private KafkaProducer<String, String> kafkaProducer;

    /**
     * send data tool
     * @param data
     * @param logger
     * @return send result
     */
    public <T> boolean sendMessage(T data, Logger logger){
        String dataStr = GsonUtil.getInstance().writeJson(data);
        if(logger.isInfoEnabled()){
            logger.info("send message though kafka: {}", dataStr);
        }
        kafkaProducer.send(new ProducerRecord<>(TOPIC, dataStr));
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
    public Properties kafkaConfig(){
        String bootstrapServers = String.format("%s:%s",
                                                    applicationConfigUtil.get(ApplicationConfigKey.uploadLogUrl.getKey()),
                                                    applicationConfigUtil.get(ApplicationConfigKey.uploadLogPort.getKey()));
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", bootstrapServers);
        return properties;
    }

    @Override
    public void destroy(){
        kafkaProducer.flush();
        kafkaProducer.close();
    }

    public UploadLogByKafkaConfig(ApplicationConfigUtil applicationConfigUtil) {
        this.applicationConfigUtil = applicationConfigUtil;
    }
}
