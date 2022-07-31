package org.manage.log.config.repository.factory;

import org.manage.log.common.util.config.ApplicationConfigUtil;
import org.manage.log.common.util.factory.BaseFactory;
import org.manage.log.config.repository.LogConfigRepository;
import org.manage.log.config.repository.config.ApplicationConfigKey;
import org.manage.log.config.repository.factory.cache.CacheStoreRepositoryMode;
import org.springframework.beans.factory.InitializingBean;
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
public class ConfigRepositoryFactory extends BaseFactory{

    @Autowired
    private ApplicationConfigUtil applicationConfigUtil;

    @Bean
    @Primary
    public LogConfigRepository initPrimaryRepository(@Qualifier("actualConfigRepository") LogConfigRepository actualConfigRepository){
        Optional<String> cacheMode = applicationConfigUtil.get(ApplicationConfigKey.cacheMode.getKey());
        if(cacheMode.isEmpty()){
            return actualConfigRepository;
        }
        return initPrimaryBean(ApplicationConfigKey.cacheMode.getKey(), CacheStoreRepositoryMode.Redis, CacheStoreRepositoryMode.values(), LogConfigRepository.class);
    }


    @Bean("actualConfigRepository")
    public LogConfigRepository initActualRepository(){
        return initPrimaryBean(ApplicationConfigKey.storeMode.getKey(), StoreRepositoryMode.Mysql, StoreRepositoryMode.values(), LogConfigRepository.class);
    }
}
