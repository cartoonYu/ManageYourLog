package org.manage.log.receive.provider.service.config;

import org.manage.log.common.model.config.LogConfig;
import org.manage.log.receive.provider.repository.LogConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/**
 * @author cartoon
 * @since 2022/11/14 21:53
 */
@Service
public class LogConfigServiceImpl implements LogConfigService {

    @Autowired
    private LogConfigRepository logConfigRepository;

    @Override
    public boolean add(LogConfig logConfig) {
        return logConfigRepository.add(logConfig);
    }

    @Override
    public Optional<LogConfig> getConfigByName(String configName) {
        Assert.notNull(configName, "config name must not null");
        return logConfigRepository.getByConfigName(configName);
    }

    @Override
    public List<LogConfig> getAll() {
        return logConfigRepository.getAll();
    }
}
