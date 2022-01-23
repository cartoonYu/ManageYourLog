package org.manageyourlog.facade.service.http;

import org.manageyourlog.common.config.ApplicationConfig;
import org.manageyourlog.facade.config.ApplicationConfigKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * @author cartoon
 * @date 2022/1/9 15:49
 */
@Component
@Conditional(UploadLogByHttpLoadCondition.class)
public class UploadLogByHttpConfig {

    @Autowired
    private ApplicationConfig applicationConfig;


    private String baseUrl;

    @PostConstruct
    private void init(){
        applicationConfig.get(ApplicationConfigKey.uploadLogServerUrl.getKey(), (baseUrl) -> this.baseUrl = baseUrl);
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
