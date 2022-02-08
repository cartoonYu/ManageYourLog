package org.manage.log.facade.service.factory;

import org.manage.log.common.util.loadCondition.BaseLoadCondition;
import org.manage.log.common.util.loadCondition.LoadConditionPojo;
import org.manage.log.facade.config.ApplicationConfigKey;

/**
 * @author cartoon
 * @date 2022/1/23 22:44
 */
public class UploadLogLoadConfig extends BaseLoadCondition {

    @Override
    protected LoadConditionPojo conditionPojo() {
        return new LoadConditionPojo(ApplicationConfigKey.uploadLogMode.getKey(), UploadLogLoadCondition.class);
    }
}
