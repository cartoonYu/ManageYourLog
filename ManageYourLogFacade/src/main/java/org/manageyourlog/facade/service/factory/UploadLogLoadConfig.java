package org.manageyourlog.facade.service.factory;

import org.manageyourlog.common.util.loadCondition.BaseLoadCondition;
import org.manageyourlog.facade.config.ApplicationConfigKey;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;
import java.util.Optional;

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
    protected Optional<String> matchSpecifyCondition(AnnotatedTypeMetadata metadata) {
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(UploadLogLoadCondition.class.getName());
        assert annotationAttributes != null;
        if(annotationAttributes.get("mode") instanceof UploadLogMode uploadLogMode){
            return Optional.ofNullable(uploadLogMode.getMode());
        }
        return Optional.empty();
    }
}
