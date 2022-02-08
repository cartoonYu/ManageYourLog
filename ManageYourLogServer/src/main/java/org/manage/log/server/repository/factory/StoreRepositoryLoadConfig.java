package org.manage.log.server.repository.factory;

import org.manage.log.common.util.loadCondition.BaseLoadCondition;
import org.manage.log.common.util.loadCondition.LoadConditionPojo;
import org.manage.log.server.config.ApplicationConfigKey;

/**
 * @author cartoon
 * @date 2022/1/24 20:16
 */
public class StoreRepositoryLoadConfig extends BaseLoadCondition {

    @Override
    protected LoadConditionPojo conditionPojo() {
        return new LoadConditionPojo(ApplicationConfigKey.storeLoadMode.getKey(), StoreRepositoryLoadCondition.class);
    }
}
