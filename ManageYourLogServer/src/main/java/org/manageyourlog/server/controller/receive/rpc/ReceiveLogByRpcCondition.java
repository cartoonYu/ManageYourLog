package org.manageyourlog.server.controller.receive.rpc;

import org.manageyourlog.common.util.loadCondition.AbstractLoadCondition;
import org.manageyourlog.server.config.ApplicationConfigKey;
import org.manageyourlog.server.controller.receive.ReceiveLogMode;

/**
 * @author cartoon
 * @date 2022/1/16 17:03
 */
public class ReceiveLogByRpcCondition extends AbstractLoadCondition {

    @Override
    protected String configKey() {
        return ApplicationConfigKey.receiveLogLoadMode.getKey();
    }

    @Override
    protected String matchSpecifyCondition() {
        return ReceiveLogMode.rpc.getMode();
    }
}
