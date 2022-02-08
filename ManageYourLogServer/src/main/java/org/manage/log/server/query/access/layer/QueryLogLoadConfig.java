package org.manage.log.server.query.access.layer;

import org.manage.log.common.util.loadCondition.BaseLoadCondition;
import org.manage.log.common.util.loadCondition.LoadConditionPojo;
import org.manage.log.server.config.ApplicationConfigKey;

/**
 * @author cartoon
 * @date 2022/1/24 20:16
 */
public class QueryLogLoadConfig extends BaseLoadCondition {

    @Override
    protected LoadConditionPojo conditionPojo() {
        return new LoadConditionPojo(ApplicationConfigKey.queryLogLoadMode.getKey(),
                                        QueryLogLoadCondition.class);
    }
}
