package org.manageyourlog.server.dao;

import org.manageyourlog.common.util.loadCondition.AbstractLoadCondition;
import org.manageyourlog.server.config.ApplicationConfigKey;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * @author cartoon
 * @date 2022/1/24 20:16
 */
public class ReceiveLogDaoLoadConfig extends AbstractLoadCondition {

    @Override
    protected String configKey() {
        return ApplicationConfigKey.receiveLogLoadMode.getKey();
    }

    @Override
    protected String matchSpecifyCondition(AnnotatedTypeMetadata metadata) {
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ReceiveLogDaoLoadCondition.class.getName());
        assert annotationAttributes != null;
        return ((StoreMode) annotationAttributes.get("mode")).getMode();
    }
}
