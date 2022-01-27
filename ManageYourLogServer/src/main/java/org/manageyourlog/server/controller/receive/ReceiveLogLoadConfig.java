package org.manageyourlog.server.controller.receive;

import org.manageyourlog.common.util.loadCondition.BaseLoadCondition;
import org.manageyourlog.server.config.ApplicationConfigKey;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

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
    protected String matchSpecifyCondition(AnnotatedTypeMetadata metadata) {
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ReceiveLogLoadCondition.class.getName());
        assert annotationAttributes != null;
        return ((ReceiveLogMode) annotationAttributes.get("mode")).getMode();
    }
}
