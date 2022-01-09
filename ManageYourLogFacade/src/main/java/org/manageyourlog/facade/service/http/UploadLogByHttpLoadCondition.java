package org.manageyourlog.facade.service.http;

import org.manageyourlog.facade.config.ApplicationConfigKey;
import org.manageyourlog.facade.service.factory.UploadLogMode;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author cartoon
 * @date 2022/1/9 17:00
 */
public class UploadLogByHttpLoadCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String uploadMode = context.getEnvironment().getProperty(ApplicationConfigKey.uploadLogMode.getKey());
        return UploadLogMode.http.getMode().equals(uploadMode);
    }
}
