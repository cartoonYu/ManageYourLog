package org.manage.log.receive.access.layer;

import org.manage.log.common.util.loadCondition.BaseLoadCondition;
import org.manage.log.common.util.loadCondition.LoadConditionPojo;
import org.manage.log.receive.config.ApplicationConfigKey;

/**
 * @author cartoon
 * @date 2022/1/24 20:16
 */
public class ReceiveLogLoadConfig extends BaseLoadCondition {

    @Override
    protected LoadConditionPojo conditionPojo() {
        return new LoadConditionPojo(ApplicationConfigKey.receiveLogLoadMode.getKey(), ReceiveLogLoadCondition.class);
    }

}
