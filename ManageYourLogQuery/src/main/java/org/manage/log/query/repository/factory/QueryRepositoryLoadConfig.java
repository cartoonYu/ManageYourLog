package org.manage.log.query.repository.factory;

import org.manage.log.common.util.loadCondition.BaseLoadCondition;
import org.manage.log.common.util.loadCondition.LoadConditionPojo;
import org.manage.log.query.repository.config.ApplicationConfigKey;

/**
 * @author cartoon
 * @date 2022/1/24 20:16
 */
public class QueryRepositoryLoadConfig extends BaseLoadCondition {

    @Override
    protected LoadConditionPojo conditionPojo() {
        return new LoadConditionPojo(ApplicationConfigKey.storeLoadMode.getKey(), QueryRepositoryLoadCondition.class);
    }
}
