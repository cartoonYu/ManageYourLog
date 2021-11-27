package org.manageyourlog.facade.service;

import org.manageyourlog.common.constants.Error;
import org.manageyourlog.common.util.BaseFactory;
import org.manageyourlog.facade.config.ApplicationConfig;
import org.manageyourlog.facade.config.ApplicationConfigKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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

    private static final Logger log = LoggerFactory.getLogger(UploadLogFactory.class);

    @Autowired
    private ApplicationConfig applicationConfig;

    @Bean
    @Primary
    public ActualUploadLog getActualUploadService(){
        Optional<String> uploadMode = applicationConfig.get(ApplicationConfigKey.uploadLogMode);
        UploadLogMode uploadLogMode = uploadMode.isPresent() ? UploadLogMode.parse(uploadMode.get()) : UploadLogMode.defaultMode;
        return (ActualUploadLog) applicationContext.getBean(uploadLogMode.getClassType());
    }

    @Override
    protected void checkModeIllegal() throws IllegalArgumentException {
        Optional<String> uploadMode = applicationConfig.get(ApplicationConfigKey.uploadLogMode);
        UploadLogMode uploadLogMode = uploadMode.isPresent() ? UploadLogMode.parse(uploadMode.get()) : UploadLogMode.defaultMode;
        ActualUploadLog actualUploadLog = (ActualUploadLog) applicationContext.getBean(uploadLogMode.getClassType());
        if(!actualUploadLog.enable()){
            log.error("check upload mode, check fail because related config is miss, using mode: {}, related config: {}", uploadLogMode.getMode(), uploadLogMode.getBaseUrl().getKey());
            throw new IllegalArgumentException(Error.propertyMiss.getMsg());
        }
    }
}
