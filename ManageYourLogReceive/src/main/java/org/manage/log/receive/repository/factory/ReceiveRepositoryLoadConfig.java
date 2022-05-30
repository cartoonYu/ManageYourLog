package org.manage.log.receive.repository.factory;

import org.manage.log.common.util.loadCondition.BaseLoadCondition;
import org.manage.log.common.util.loadCondition.LoadConditionPojo;
import org.manage.log.receive.repository.config.ReceiveApplicationConfigKey;

/**
 * @author cartoon
 * @date 2022/1/24 20:16
 */
public class ReceiveRepositoryLoadConfig extends BaseLoadCondition {

    @Override
    protected LoadConditionPojo conditionPojo() {
        return new LoadConditionPojo(ReceiveApplicationConfigKey.storeLoadMode.getKey(), ReceiveRepositoryLoadCondition.class);
    }
}
