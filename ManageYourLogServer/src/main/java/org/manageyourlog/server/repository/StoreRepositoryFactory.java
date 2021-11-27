package org.manageyourlog.server.repository;

import org.manageyourlog.common.constants.Error;
import org.manageyourlog.common.util.BaseFactory;
import org.manageyourlog.facade.config.ApplicationConfig;
import org.manageyourlog.facade.config.ApplicationConfigKey;
import org.manageyourlog.facade.service.UploadLogMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/15 23:56
 */
@Component
public class StoreRepositoryFactory extends BaseFactory {

    @Autowired
    private ApplicationConfig applicationConfig;

    @Bean
    @Primary
    public LogRecordRepository initPrimaryRepository(){
        Optional<String> storeMode = applicationConfig.get(ApplicationConfigKey.storeMethod);
        RepositoryMode repositoryMode = storeMode.isPresent() ? RepositoryMode.parse(storeMode.get()) : RepositoryMode.defaultMode;
        return (LogRecordRepository) applicationContext.getBean(repositoryMode.getClassType());
    }

    @Override
    protected void checkModeIllegal() throws IllegalArgumentException {
        Optional<String> storeMethod = applicationConfig.get(ApplicationConfigKey.storeMethod);
        if(!storeMethod.isPresent()){

        }
    }
}
