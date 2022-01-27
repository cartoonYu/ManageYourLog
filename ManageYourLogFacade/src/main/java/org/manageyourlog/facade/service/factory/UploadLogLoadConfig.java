package org.manageyourlog.facade.service.factory;

import org.manageyourlog.common.util.loadCondition.BaseLoadCondition;
import org.manageyourlog.facade.config.ApplicationConfigKey;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * @author cartoon
 * @date 2022/1/23 22:44
 */
public class UploadLogLoadConfig extends BaseLoadCondition {

    @Override
    protected String configKey() {
        return ApplicationConfigKey.uploadLogMode.getKey();
    }

    @Override
    protected String matchSpecifyCondition(AnnotatedTypeMetadata metadata) {
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(UploadLogLoadCondition.class.getName());
        assert annotationAttributes != null;
        return ((UploadLogMode) annotationAttributes.get("mode")).getMode();
    }
}
