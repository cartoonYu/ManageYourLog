package org.manage.log.facade.service.http;

import org.manage.log.common.util.config.ApplicationConfigUtil;
import org.manage.log.facade.config.ApplicationConfigKey;
import org.manage.log.facade.service.factory.UploadLogLoadCondition;
import org.manage.log.facade.service.factory.UploadLogMode;
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
