package org.manageyourlog.facade.service.http;

import org.manageyourlog.common.config.ApplicationConfig;
import org.manageyourlog.facade.config.ApplicationConfigKey;
import org.manageyourlog.facade.service.factory.UploadLogLoadCondition;
import org.manageyourlog.facade.service.factory.UploadLogMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author cartoon
 * @date 2022/1/9 15:49
 */
@Component
@UploadLogLoadCondition(mode = UploadLogMode.http)
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
