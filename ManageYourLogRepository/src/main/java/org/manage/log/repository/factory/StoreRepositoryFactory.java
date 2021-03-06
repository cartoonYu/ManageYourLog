package org.manage.log.repository.factory;

import org.manage.log.common.util.config.ApplicationConfigUtil;
import org.manage.log.common.util.factory.BaseFactory;
import org.manage.log.repository.LogRecordRepository;
import org.manage.log.repository.config.ApplicationConfigKey;
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
    private ApplicationConfigUtil applicationConfigUtil;

    @Bean
    @Primary
    public LogRecordRepository initPrimaryRepository(){
        Optional<String> storeMode = applicationConfigUtil.get(ApplicationConfigKey.storeMode.getKey());
        Class<?> storeClass = StoreRepositoryMode.Mysql.getClassType();
        if(storeMode.isEmpty()){
            log.warn("init store repository, have not determine store mode, back to default class type: {}", storeClass.getSimpleName());
            return (LogRecordRepository) applicationContext.getBean(storeClass);
        }
        Optional<StoreRepositoryMode> selectStoreMode = StoreRepositoryMode.parse(storeMode.get());
        if(selectStoreMode.isPresent()){
            storeClass = selectStoreMode.get().getClassType();
        }
        log.info("init store repository, class type: {}", storeClass.getSimpleName());
        return (LogRecordRepository) applicationContext.getBean(storeClass);
    }
}
