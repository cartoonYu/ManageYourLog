package org.manageyourlog.facade.service.http;

import org.manageyourlog.common.util.loadCondition.AbstractLoadCondition;
import org.manageyourlog.facade.config.ApplicationConfigKey;
import org.manageyourlog.facade.service.factory.UploadLogMode;

/**
 * @author cartoon
 * @date 2022/1/9 17:00
 */
public class UploadLogByHttpLoadCondition extends AbstractLoadCondition {

    @Override
    protected String configKey() {
        return ApplicationConfigKey.uploadLogMode.getKey();
    }

    @Override
    protected String matchSpecifyCondition() {
        return UploadLogMode.http.getMode();
    }
}