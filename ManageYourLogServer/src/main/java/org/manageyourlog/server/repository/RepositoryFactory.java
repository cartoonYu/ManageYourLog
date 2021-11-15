package org.manageyourlog.server.repository;

import org.manageyourlog.common.util.BaseFactory;
import org.manageyourlog.facade.config.ApplicationConfig;
import org.manageyourlog.facade.config.ApplicationConfigKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/15 23:56
 */
@Component
public class RepositoryFactory extends BaseFactory {

    @Autowired
    private ApplicationConfig applicationConfig;

    public LogRecordRepository get(){
        Optional<String> storeMode = applicationConfig.get(ApplicationConfigKey.storeMethod);
        RepositoryMode repositoryMode = storeMode.isPresent() ? RepositoryMode.parse(storeMode.get()) : RepositoryMode.defaultMode;
        return (LogRecordRepository) applicationContext.getBean(repositoryMode.getClassType());
    }
}
