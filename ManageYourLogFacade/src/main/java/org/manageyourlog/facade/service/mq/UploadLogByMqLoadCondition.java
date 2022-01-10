package org.manageyourlog.facade.service.mq;

import org.manageyourlog.facade.config.ApplicationConfigKey;
import org.manageyourlog.facade.service.factory.UploadLogMode;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author cartoon
 * @date 2022/1/9 16:52
 */
public class UploadLogByMqLoadCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String uploadMode = context.getEnvironment().getProperty(ApplicationConfigKey.uploadLogMode.getKey());
        return UploadLogMode.kafka.getMode().equals(uploadMode);
    }
}
