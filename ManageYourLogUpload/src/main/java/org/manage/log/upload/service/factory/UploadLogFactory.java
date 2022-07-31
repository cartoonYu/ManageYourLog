package org.manage.log.upload.service.factory;

import org.manage.log.common.util.factory.BaseFactory;
import org.manage.log.upload.UploadLog;
import org.manage.log.upload.config.ApplicationConfigKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * determine primary send log service
 * @author cartoon
 * @date 2021/11/5 20:55
 */
@Component
public class UploadLogFactory extends BaseFactory {

    /*@Bean
    @Primary
    public UploadLog initPrimarySendLogService(){
        return initPrimaryBean(ApplicationConfigKey.uploadLogMode.getKey(), UploadLogMode.defaultMode, UploadLogMode.values(), UploadLog.class);
    }*/
}
