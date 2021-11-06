package org.manageyourlog.facade.service;

import org.manageyourlog.facade.UploadLog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author cartoon
 * @date 2021/11/5 20:55
 */
@Component
public class UploadLogFactory implements ApplicationContextAware {

    @Autowired
    private Environment environment;

    private ApplicationContext applicationContext;

    private String uploadLogModeConfigKey;

    public UploadLog getActualUploadService(){
        String uploadMode = environment.getProperty(uploadLogModeConfigKey);
        UploadLogMode uploadLogMode = UploadLogMode.parse(uploadMode);
        return (UploadLog) applicationContext.getBean(uploadLogMode.getClassType());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public UploadLogFactory() {
        uploadLogModeConfigKey = "upload.log.mode";
    }
}
