package org.manage.log.config.repository.factory.cache;

import org.manage.log.common.util.loadCondition.BaseLoadCondition;
import org.manage.log.common.util.loadCondition.LoadConditionPojo;
import org.manage.log.config.repository.config.ApplicationConfigKey;

/**
 * @author cartoon
 * @date 2022/1/24 20:16
 */
public class CacheStoreRepositoryLoadConfig extends BaseLoadCondition {

    @Override
    protected LoadConditionPojo conditionPojo() {
        return new LoadConditionPojo(ApplicationConfigKey.cacheMode.getKey(), CacheRepositoryLoadCondition.class);
    }
}
