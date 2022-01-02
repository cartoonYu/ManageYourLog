package org.manageyourlog.server.dao;

import org.manageyourlog.server.config.ApplicationConfigKey;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author cartoon
 * @date 2022/1/2 18:42
 */
public abstract class StoreDaoConditionImpl implements StoreLoadDaoCondition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return matches(context, StoreLoadDaoEnum.All) || matches(context, datasourceType());
    }

    private boolean matches(ConditionContext context, StoreLoadDaoEnum storeLoadDaoEnum){
        Environment environment = context.getEnvironment();
        String storeLoadDao = environment.getProperty(ApplicationConfigKey.storeLoadDao.getKey());
        return storeLoadDaoEnum.getInfo().equals(storeLoadDao);
    }

}
