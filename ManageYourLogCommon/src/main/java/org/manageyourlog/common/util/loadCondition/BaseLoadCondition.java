package org.manageyourlog.common.util.loadCondition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * base class assign to load class by configuration
 * @author cartoon
 * @date 2022/1/12 10:27
 */
public abstract class BaseLoadCondition implements Condition {

    private static final String CONFIG_VALUE_SPLIT_SEPARATOR = ",";

    private static final String ALL_MATCH_CONFIG = "all";

    /**
     * match and load matched classes
     * <p> there will be two condition to match success
     * <p>1. config value contains "all", this condition will be load all class
     * <p>2. config value contains configuration of the class value
     * @param context condition context
     * @param metadata annotation meta data
     * @return
     */
    @Override
    public boolean matches(@NonNull ConditionContext context, @NonNull AnnotatedTypeMetadata metadata) {
        Optional<String> specifyCondition = matchSpecifyCondition(metadata);
        return matches(context, configKey(), ALL_MATCH_CONFIG)
                || (specifyCondition.isPresent() && matches(context, configKey(), specifyCondition.get()));
    }

    /**
     * get value from config value, compare property file and load matched classes<br/>
     * @param context condition context
     * @param configKey configuration key
     * @param configuration configuration value
     * @return match result
     */
    private boolean matches(ConditionContext context, String configKey, String configuration){
        if(Optional.ofNullable(configKey).isEmpty()){
            return false;
        }
        Environment environment = context.getEnvironment();
        String config = environment.getProperty(configKey);
        return Optional.ofNullable(config).map(value -> {
            List<String> loadList = Arrays.stream(value.split(CONFIG_VALUE_SPLIT_SEPARATOR)).toList();
            return loadList.stream().anyMatch(configuration::contains);
        }).orElse(false);
    }

    /**
     * config key to be read
     * @return config key
     */
    protected abstract String configKey();

    /**
     * config value to be match
     * @param metadata use to get annotation value tool
     * @return config value
     */
    protected abstract Optional<String> matchSpecifyCondition(AnnotatedTypeMetadata metadata);
}
