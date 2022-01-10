package org.manageyourlog.facade.service.mq;

import org.manageyourlog.common.config.ApplicationConfig;
import org.manageyourlog.facade.config.ApplicationConfigKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * @author cartoon
 * @date 2022/1/9 15:54
 */
@Component
public class UploadLogByMqConfig {

    @Autowired
    private ApplicationConfig applicationConfig;

    private String baseUrl;

    private String topic;

    @PostConstruct
    private void init(){
        Optional<String> baseUrl = applicationConfig.get(ApplicationConfigKey.uploadLogKafkaUrl.getKey());
        Optional<String> topic = applicationConfig.get(ApplicationConfigKey.uploadLogKafkaTopic.getKey());
        baseUrl.ifPresent(url -> this.baseUrl = url);
        topic.ifPresent(topicValue -> this.topic = topicValue);
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getTopic() {
        return topic;
    }
}
