package org.manageyourlog.server.controller.query;

import org.manageyourlog.common.util.loadCondition.BaseLoadCondition;
import org.manageyourlog.facade.service.factory.UploadLogMode;
import org.manageyourlog.server.config.ApplicationConfigKey;
import org.manageyourlog.server.controller.receive.ReceiveLogLoadCondition;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;
import java.util.Optional;

/**
 * @author cartoon
 * @date 2022/1/24 20:16
 */
public class QueryLogLoadConfig extends BaseLoadCondition {

    @Override
    protected String configKey() {
        return ApplicationConfigKey.queryLogLoadMode.getKey();
    }

    @Override
    protected Optional<String> matchSpecifyCondition(AnnotatedTypeMetadata metadata) {
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(QueryLogLoadCondition.class.getName());
        assert annotationAttributes != null;
        if(annotationAttributes.get("mode") instanceof QueryLogMode queryLogMode){
            return Optional.ofNullable(queryLogMode.getMode());
        }
        return Optional.empty();
    }
}
