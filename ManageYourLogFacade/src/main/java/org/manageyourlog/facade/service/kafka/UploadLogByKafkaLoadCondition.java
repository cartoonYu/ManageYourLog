package org.manageyourlog.facade.service.kafka;

import org.manageyourlog.common.util.loadCondition.AbstractLoadCondition;
import org.manageyourlog.facade.config.ApplicationConfigKey;
import org.manageyourlog.facade.service.factory.UploadLogMode;

/**
 * @author cartoon
 * @date 2022/1/12 20:52
 */
public class UploadLogByKafkaLoadCondition extends AbstractLoadCondition {

    @Override
    protected String configKey() {
        return ApplicationConfigKey.uploadLogMode.getKey();
    }

    @Override
    protected String matchSpecifyCondition() {
        return UploadLogMode.kafka.getMode();
    }
}
