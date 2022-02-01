package org.manageyourlog.server.controller.receive;

import org.manageyourlog.common.util.loadCondition.BaseLoadCondition;
import org.manageyourlog.facade.service.factory.UploadLogMode;
import org.manageyourlog.server.config.ApplicationConfigKey;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;
import java.util.Optional;

/**
 * @author cartoon
 * @date 2022/1/24 20:16
 */
public class ReceiveLogLoadConfig extends BaseLoadCondition {

    @Override
    protected String configKey() {
        return ApplicationConfigKey.receiveLogLoadMode.getKey();
    }

    @Override
    protected Optional<String> matchSpecifyCondition(AnnotatedTypeMetadata metadata) {
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ReceiveLogLoadCondition.class.getName());
        assert annotationAttributes != null;
        if(annotationAttributes.get("mode") instanceof UploadLogMode uploadLogMode){
            return Optional.ofNullable(uploadLogMode.getMode());
        }
        return Optional.empty();
    }
}
