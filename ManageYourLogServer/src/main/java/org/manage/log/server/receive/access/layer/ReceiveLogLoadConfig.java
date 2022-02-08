package org.manage.log.server.receive.access.layer;

import org.manage.log.server.config.ApplicationConfigKey;
import org.manage.log.common.util.loadCondition.BaseLoadCondition;
import org.manage.log.common.util.loadCondition.LoadConditionPojo;

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
