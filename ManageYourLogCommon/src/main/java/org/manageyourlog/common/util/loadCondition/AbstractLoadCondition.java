package org.manageyourlog.common.util.loadCondition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author cartoon
 * @date 2022/1/12 10:27
 */
public abstract class AbstractLoadCondition implements Condition {

    private static final String splitSeparator = ",";

    private static final String allMatchConfig = "all";

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return matches(context, configKey(), allMatchConfig)
                || matches(context, configKey(), matchSpecifyCondition(metadata));
    }

    private boolean matches(ConditionContext context, String configKey, String configuration){
        if(Optional.ofNullable(configKey).isEmpty()){
            return false;
        }
        Environment environment = context.getEnvironment();
        String config = environment.getProperty(configKey);
        return Optional.ofNullable(config).map(value -> {
            List<String> loadList = Arrays.stream(value.split(splitSeparator)).toList();
            return loadList.stream().anyMatch(configuration::contains);
        }).orElse(false);
    }

    protected abstract String configKey();

    protected abstract String matchSpecifyCondition(AnnotatedTypeMetadata metadata);
}
