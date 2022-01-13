package org.manageyourlog.server.controller.receive.http;

import org.manageyourlog.common.util.loadCondition.AbstractLoadCondition;
import org.manageyourlog.server.config.ApplicationConfigKey;
import org.manageyourlog.server.controller.receive.ReceiveLogMode;

/**
 * @author cartoon
 * @date 2022/1/11 21:42
 */
public class ReceiveLogByHttpCondition extends AbstractLoadCondition {

    @Override
    protected String configKey() {
        return ApplicationConfigKey.receiveLogLoadMode.getKey();
    }

    @Override
    protected String matchSpecifyCondition() {
        return ReceiveLogMode.http.getMode();
    }
}
