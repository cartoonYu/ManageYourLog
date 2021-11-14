package org.manageyourlog.facade;

import org.manageyourlog.common.constants.Error;
import org.manageyourlog.facade.config.ApplicationConfig;
import org.manageyourlog.facade.config.ApplicationConfigKey;
import org.manageyourlog.facade.service.ActualUploadLog;
import org.manageyourlog.facade.service.UploadLogMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author cartoon
 * @date 2021/11/15 00:02
 */
@Component
public class CheckUploadModeIllegal implements ApplicationRunner, ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(CheckUploadModeIllegal.class);

    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationConfig applicationConfig;

    @Override
    public void run(ApplicationArguments args) {
        Optional<String> isCheckUploadLogMode = applicationConfig.get(ApplicationConfigKey.checkUploadLogMode);
        if(isCheckUploadLogMode.isPresent() && "false".equals(isCheckUploadLogMode.get())){
            return;
        }
        Optional<String> uploadMode = applicationConfig.get(ApplicationConfigKey.uploadLogMode);
        UploadLogMode uploadLogMode = uploadMode.isPresent() ? UploadLogMode.parse(uploadMode.get()) : UploadLogMode.defaultMode;
        ActualUploadLog actualUploadLog = (ActualUploadLog) applicationContext.getBean(uploadLogMode.getClassType());
        if(!actualUploadLog.enable()){
            log.error("check upload mode, check fail because related config is miss, using mode: {}, related config: {}", uploadLogMode.getMode(), uploadLogMode.getBaseUrl().getKey());
            throw new IllegalArgumentException(Error.propertyMiss.getMsg());
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
