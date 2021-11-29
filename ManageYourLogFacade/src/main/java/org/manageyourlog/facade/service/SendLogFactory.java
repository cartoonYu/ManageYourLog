package org.manageyourlog.facade.service;

import org.manageyourlog.common.constants.Error;
import org.manageyourlog.common.util.BaseFactory;
import org.manageyourlog.facade.TransferLog;
import org.manageyourlog.facade.config.ApplicationConfig;
import org.manageyourlog.facade.config.ApplicationConfigKey;
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
public class SendLogFactory extends BaseFactory {

    @Autowired
    private ApplicationConfig applicationConfig;

    @Bean
    @Primary
    public TransferLog initPrimarySendLogService(){
        Optional<String> uploadMode = applicationConfig.get(ApplicationConfigKey.uploadLogMode);
        Class<?> sendLogClass = SendLogMode.defaultMode.getClassType();
        if(uploadMode.isPresent()){
            for(SendLogMode sendLogMode : SendLogMode.values()){
                if(uploadMode.get().equals(sendLogMode.getMode())){
                    sendLogClass = sendLogMode.getClassType();
                    break;
                }
            }
        }
        log.info("init send log service, class type: {}", sendLogClass.getSimpleName());
        return (TransferLog) applicationContext.getBean(sendLogClass);
    }

    @Override
    protected void checkModeIllegal() throws IllegalArgumentException {
        Optional<String> uploadMode = applicationConfig.get(ApplicationConfigKey.uploadLogMode);
        if(!uploadMode.isPresent()){
            log.error("check send log mode, check fail because related config is miss");
            throw new IllegalArgumentException(Error.propertyMiss.getMsg());
        }
    }
}
