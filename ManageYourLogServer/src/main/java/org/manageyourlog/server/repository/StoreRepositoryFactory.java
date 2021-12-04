package org.manageyourlog.server.repository;

import org.manageyourlog.common.constants.Error;
import org.manageyourlog.common.util.BaseFactory;
import org.manageyourlog.facade.config.ApplicationConfig;
import org.manageyourlog.facade.config.ApplicationConfigKey;
import org.springframework.beans.factory.annotation.Autowired;
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
        Class<?> storeClass = RepositoryMode.defaultMode.getClassType();
        if(storeMode.isPresent()){
            for(RepositoryMode repositoryMode : RepositoryMode.values()){
                if(repositoryMode.getMode().equals(storeMode.get())){
                    storeClass = repositoryMode.getClassType();
                }
            }
        }
        log.info("init store repository, class type: {}", storeClass.getSimpleName());
        return (LogRecordRepository) applicationContext.getBean(storeClass);
    }

    @Override
    protected void checkModeIllegal() throws IllegalArgumentException {
        Optional<String> storeMethod = applicationConfig.get(ApplicationConfigKey.storeMethod);
        if(!storeMethod.isPresent()){
            log.error("check store mode, check fail because related config is miss");
            throw new IllegalArgumentException(Error.propertyMiss.getMsg());
        }
    }
}
