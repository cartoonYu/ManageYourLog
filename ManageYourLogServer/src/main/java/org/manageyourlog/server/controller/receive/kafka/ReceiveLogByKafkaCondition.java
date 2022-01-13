package org.manageyourlog.server.controller.receive.kafka;

import org.manageyourlog.common.util.loadCondition.AbstractLoadCondition;
import org.manageyourlog.server.config.ApplicationConfigKey;
import org.manageyourlog.server.controller.receive.ReceiveLogMode;

/**
 * @author cartoon
 * @date 2022/1/11 22:42
 */
public class ReceiveLogByKafkaCondition extends AbstractLoadCondition {

    @Override
    protected String configKey() {
        return ApplicationConfigKey.receiveLogLoadMode.getKey();
    }

    @Override
    protected String matchSpecifyCondition() {
        return ReceiveLogMode.mq.getMode();
    }
}
