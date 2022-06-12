package org.manage.log.config.repository.factory;

import org.manage.log.common.util.config.ApplicationConfigUtil;
import org.manage.log.common.util.factory.BaseFactory;
import org.manage.log.config.repository.LogConfigRepository;
import org.manage.log.config.repository.config.ApplicationConfigKey;
import org.manage.log.config.repository.factory.cache.CacheStoreRepositoryMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class ConfigRepositoryFactory extends BaseFactory {

    @Autowired
    private ApplicationConfigUtil applicationConfigUtil;

    @Bean
    @Primary
    public LogConfigRepository initPrimaryRepository(@Qualifier("actualConfigRepository") LogConfigRepository actualConfigRepository){
        Optional<String> cacheMode = applicationConfigUtil.get(ApplicationConfigKey.cacheMode.getKey());
        if(cacheMode.isEmpty()){
            return actualConfigRepository;
        }
        Optional<CacheStoreRepositoryMode> selectCacheMode = CacheStoreRepositoryMode.parse(cacheMode.get());
        Class<?> cacheClass = selectCacheMode.isEmpty() ? CacheStoreRepositoryMode.Redis.getClassType() : selectCacheMode.get().getClassType();
        log.info("init cache store repository, class type: {}", cacheClass.getSimpleName());
        return (LogConfigRepository) applicationContext.getBean(cacheClass);
    }

    @Bean("actualConfigRepository")
    public LogConfigRepository initActualRepository(){
        Optional<String> storeMode = applicationConfigUtil.get(ApplicationConfigKey.storeMode.getKey());
        Class<?> storeClass = StoreRepositoryMode.Mysql.getClassType();
        if(storeMode.isEmpty()){
            log.warn("init store repository, have not determine store mode, back to default class type: {}", storeClass.getSimpleName());
            return (LogConfigRepository) applicationContext.getBean(storeClass);
        }
        Optional<StoreRepositoryMode> selectStoreMode = StoreRepositoryMode.parse(storeMode.get());
        if(selectStoreMode.isPresent()){
            storeClass = selectStoreMode.get().getClassType();
        }
        log.info("init store repository, class type: {}", storeClass.getSimpleName());
        return (LogConfigRepository) applicationContext.getBean(storeClass);
    }


}
