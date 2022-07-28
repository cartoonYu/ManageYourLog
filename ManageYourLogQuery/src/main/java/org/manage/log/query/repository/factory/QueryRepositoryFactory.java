package org.manage.log.query.repository.factory;

import org.manage.log.common.util.factory.BaseFactory;
import org.manage.log.query.repository.LogRecordRepository;
import org.manage.log.query.repository.config.ApplicationConfigKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/15 23:56
 */
@Component
public class QueryRepositoryFactory extends BaseFactory {

    @Bean
    @Primary
    public LogRecordRepository initPrimaryRepository(){
        return initPrimaryBean(ApplicationConfigKey.storeMode.getKey(), QueryRepositoryMode.Mysql, QueryRepositoryMode.values(), LogRecordRepository.class);
    }
}
