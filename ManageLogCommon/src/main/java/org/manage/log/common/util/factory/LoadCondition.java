package org.manage.log.common.util.factory;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.lang.NonNull;

import java.util.*;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/08/07 16:40
 */
public class LoadCondition implements Condition {

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
        MergedAnnotation<LoadBean> loadBeanAnnotation = metadata.getAnnotations().get(LoadBean.class);

        String mode = loadBeanAnnotation.getString("mode");

        String configKey = loadBeanAnnotation.getString("loadConfigKey");

        return matches(context, configKey, ALL_MATCH_CONFIG) || matches(context, configKey, mode);
    }

    /**
     * get value from config value, compare property file and load matched classes<br/>
     * @param context condition context
     * @param configKey configuration key
     * @param configuration configuration value
     * @return match result
     */
    private boolean matches(ConditionContext context, String configKey, String configuration){
        return Optional.ofNullable(configKey)
                .map(key -> {
                    Environment environment = context.getEnvironment();
                   //get load bean from config
                    String config = environment.getProperty(configKey);
                    return Optional.ofNullable(config).map(value -> {
                        List<String> loadList = Arrays.stream(value.split(CONFIG_VALUE_SPLIT_SEPARATOR)).toList();
                        return loadList.parallelStream().anyMatch(configuration::contains);
                    }).orElse(false);
                }).orElse(false);

    }
}
