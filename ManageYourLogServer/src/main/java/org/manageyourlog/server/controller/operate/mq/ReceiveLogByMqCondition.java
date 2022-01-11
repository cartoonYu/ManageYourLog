package org.manageyourlog.server.controller.operate.mq;

import org.manageyourlog.server.config.ApplicationConfigKey;
import org.manageyourlog.server.controller.operate.ReceiveLogMode;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author cartoon
 * @date 2022/1/11 16:42
 */
public class ReceiveLogByMqCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String receiveLogMode = context.getEnvironment().getProperty(ApplicationConfigKey.receiveLogMode.getKey());
        return ReceiveLogMode.mq.getMode().equals(receiveLogMode);
    }
}
