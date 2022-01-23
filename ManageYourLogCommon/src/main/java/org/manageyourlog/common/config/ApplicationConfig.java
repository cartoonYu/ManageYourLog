package org.manageyourlog.common.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author cartoon
 * @date 2021/11/6 20:55
 */
@Component
public class ApplicationConfig implements EnvironmentAware {

    private Environment environment;
    
    public Optional<String> get(String configKey){
        String config = environment.getProperty(configKey);
        return Objects.isNull(config) ? Optional.empty() : Optional.of(config);
    }

    public void get(String configKey, Consumer<String> consumer){
        Optional<String> config = get(configKey);
        config.ifPresent(consumer);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
