package org.manageyourlog.server.repository.factory;

import org.manageyourlog.common.config.ApplicationConfig;
import org.manageyourlog.common.util.factory.BaseFactory;
import org.manageyourlog.server.config.ApplicationConfigKey;
import org.manageyourlog.server.repository.LogRecordRepository;
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
        Optional<String> storeMode = applicationConfig.get(ApplicationConfigKey.storeMode.getKey());
        Class<?> storeClass = RepositoryMode.defaultMode.getClassType();
        if(storeMode.isPresent()){
            for(RepositoryMode repositoryMode : RepositoryMode.values()){
                if(repositoryMode.getMode().equals(storeMode.get())){
                    storeClass = repositoryMode.getClassType();
                }
            }
        } else {
            log.warn("init store repository, have not determine store mode, back to default class type: {}", storeClass.getSimpleName());
        }
        log.info("init store repository, class type: {}", storeClass.getSimpleName());
        return (LogRecordRepository) applicationContext.getBean(storeClass);
    }
}
