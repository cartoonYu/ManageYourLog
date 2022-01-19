package org.manageyourlog.facade.service.rpc;

import org.manageyourlog.common.util.loadCondition.AbstractLoadCondition;
import org.manageyourlog.facade.config.ApplicationConfigKey;
import org.manageyourlog.facade.service.factory.UploadLogMode;

/**
 * @author cartoon
 * @date 2022/1/16 17:03
 */
public class UploadLogByRpcCondition extends AbstractLoadCondition {

    @Override
    protected String configKey() {
        return ApplicationConfigKey.uploadLogMode.getKey();
    }

    @Override
    protected String matchSpecifyCondition() {
        return UploadLogMode.rpc.getMode();
    }
}
