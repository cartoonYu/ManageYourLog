package org.manage.log.common.util.loadCondition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.lang.NonNull;

import java.util.*;
import java.util.stream.Collectors;

/**
 * base class assign to load class by configuration
 * @author cartoon
 * @date 2022/1/12 10:27
 */
public abstract class BaseLoadCondition implements Condition {

    private static final String CONFIG_VALUE_SPLIT_SEPARATOR = ",";

    private static final String ALL_MATCH_CONFIG = "all";

    protected abstract LoadConditionPojo conditionPojo();

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
        LoadConditionPojo loadConditionPojo = conditionPojo();
        return matchesAll(context, loadConditionPojo) || matchSpecify(context, metadata, loadConditionPojo);
    }

    private boolean matchesAll(ConditionContext context, LoadConditionPojo loadConditionPojo){
        return matches(context, loadConditionPojo.getConfigKey(), ALL_MATCH_CONFIG);
    }

    private boolean matchSpecify(ConditionContext context, AnnotatedTypeMetadata metadata, LoadConditionPojo loadConditionPojo){
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(loadConditionPojo.getLoadConditionClass().getName());
        return Optional.ofNullable(annotationAttributes).map(conditionData -> {
            Object loadCondition = annotationAttributes.get("mode");
            String specifyMode = ((BaseLoadMode) loadCondition).getMode();
            return matches(context, loadConditionPojo.getConfigKey(), specifyMode);
        }).orElse(false);
    }

    /**
     * get value from config value, compare property file and load matched classes<br/>
     * @param context condition context
     * @param configKey configuration key
     * @param configuration configuration value
     * @return match result
     */
    private boolean matches(ConditionContext context, String configKey, String configuration){
        if(Objects.isNull(configKey)){
            return false;
        }
        Environment environment = context.getEnvironment();
        String config = environment.getProperty(configKey);
        return Optional.ofNullable(config).map(value -> {
            List<String> loadList = Arrays.stream(value.split(CONFIG_VALUE_SPLIT_SEPARATOR)).collect(Collectors.toList());
            return loadList.stream().anyMatch(configuration::contains);
        }).orElse(false);
    }

}
