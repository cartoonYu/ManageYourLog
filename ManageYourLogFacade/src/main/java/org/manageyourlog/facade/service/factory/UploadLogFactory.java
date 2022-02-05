package org.manageyourlog.facade.service.factory;

import org.manageyourlog.common.util.config.ApplicationConfigUtil;
import org.manageyourlog.common.util.factory.BaseFactory;
import org.manageyourlog.facade.UploadLog;
import org.manageyourlog.facade.config.ApplicationConfigKey;
import org.manageyourlog.facade.service.UploadLogByDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author cartoon
 * @date 2021/11/5 20:55
 */
@Component
public class UploadLogFactory extends BaseFactory {

    @Autowired
    private ApplicationConfigUtil applicationConfigUtil;

    @Bean
    @Primary
    public UploadLog initPrimarySendLogService(){
        Optional<String> uploadMode = applicationConfigUtil.get(ApplicationConfigKey.uploadLogMode.getKey());
        Class<?> sendLogClass = UploadLogByDefault.class;
        if(uploadMode.isEmpty()){
            log.warn("init send log service, have not determine store mode, back to default class type: {}", sendLogClass.getSimpleName());
            return (UploadLog) applicationContext.getBean(sendLogClass);
        }
        Optional<UploadLogMode> uploadLogMode = UploadLogMode.parse(uploadMode.get());
        if(uploadLogMode.isPresent()){
            sendLogClass = uploadLogMode.get().getHandleClass();
        }
        log.info("init send log service, class type: {}", sendLogClass.getSimpleName());
        return (UploadLog) applicationContext.getBean(sendLogClass);
    }
}
