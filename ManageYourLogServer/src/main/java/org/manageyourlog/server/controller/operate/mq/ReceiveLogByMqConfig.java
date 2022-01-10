package org.manageyourlog.server.controller.operate.mq;

import org.manageyourlog.common.config.ApplicationConfig;
import org.manageyourlog.server.config.ApplicationConfigKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author cartoon
 * @date 2022/1/10 00:07
 */
@Component
public class ReceiveLogByMqConfig {

    @Autowired
    private ApplicationConfig applicationConfig;

    private String consumeUrl;

    private String consumeTopic;

    public String getConsumeUrl() {
        return consumeUrl;
    }

    public String getConsumeTopic() {
        return consumeTopic;
    }

    @PostConstruct
    private void init(){
        consumeUrl = applicationConfig.get(ApplicationConfigKey.uploadLogKafkaUrl.getKey()).orElse(null);
        consumeTopic = applicationConfig.get(ApplicationConfigKey.uploadLogKafkaTopic.getKey()).orElse(null);
    }
}
