package org.manageyourlog.facade.service.http;

import org.manageyourlog.common.util.config.ApplicationConfigUtil;
import org.manageyourlog.facade.config.ApplicationConfigKey;
import org.manageyourlog.facade.service.factory.UploadLogLoadCondition;
import org.manageyourlog.facade.service.factory.UploadLogMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * get upload log base url from config
 * @author cartoon
 * @date 2022/1/9 15:49
 */
@Component
@UploadLogLoadCondition(mode = UploadLogMode.http)
public class UploadLogByHttpConfig {

    @Autowired
    private ApplicationConfigUtil applicationConfigUtil;

    private String baseUrl;

    @PostConstruct
    private void init(){
        applicationConfigUtil.get(ApplicationConfigKey.uploadLogHttpUrl.getKey(), (baseUrl) -> this.baseUrl = baseUrl);
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
