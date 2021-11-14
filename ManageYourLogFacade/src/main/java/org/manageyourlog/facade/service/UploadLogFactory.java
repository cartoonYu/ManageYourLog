package org.manageyourlog.facade.service;

import org.manageyourlog.facade.config.ApplicationConfig;
import org.manageyourlog.facade.config.ApplicationConfigKey;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author cartoon
 * @date 2021/11/5 20:55
 */
@Component
public class UploadLogFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationConfig applicationConfig;

    public ActualUploadLog getActualUploadService(){
        Optional<String> uploadMode = applicationConfig.get(ApplicationConfigKey.uploadLogMode);
        UploadLogMode uploadLogMode = uploadMode.isPresent() ? UploadLogMode.parse(uploadMode.get()) : UploadLogMode.defaultMode;
        return (ActualUploadLog) applicationContext.getBean(uploadLogMode.getClassType());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
