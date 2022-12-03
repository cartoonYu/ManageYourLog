package org.manage.log.common.util.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * dynamically obtain configuration to prevent program errors caused by missing configuration in {@link org.springframework.beans.factory.annotation.Value}
 * @author cartoon
 * @date 2021/11/6 20:55
 */
@Component
public class ApplicationConfigUtil implements EnvironmentAware {

    private static final Logger log = LoggerFactory.getLogger(ApplicationConfigUtil.class);

    private Environment environment;

    /**
     * get configuration by key
     * @param configKey config key
     * @return configuration by key, if didn't get, return Optional.empty()
     */
    public Optional<String> get(String configKey){
        String config = environment.getProperty(configKey);
        return Optional.ofNullable(config);
    }

    /**
     * get config by key and put it to consumer
     * @param configKey config key
     * @param consumer consume configuration by key, if didn't get config, consume operate will not execute
     */
    public void get(String configKey, Consumer<String> consumer){
        Optional<String> config = get(configKey);
        if(config.isEmpty()){
            log.warn("application config, get config by key fail, key: {}", configKey);
        }
        config.ifPresent(consumer);
    }

    @Override
    public void setEnvironment(@NonNull Environment environment) {
        this.environment = environment;
    }
}
