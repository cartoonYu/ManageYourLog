package org.manageyourlog.facade.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * @author cartoon
 * @date 2021/11/6 20:55
 */
@Component
public class ApplicationConfig implements EnvironmentAware {

    private Environment environment;

    public Optional<String> get(ApplicationConfigKey configKey){
        String config = environment.getProperty(configKey.getKey());
        return Objects.isNull(config) ? Optional.empty() : Optional.of(config);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
