package org.manage.log.config.provider.service;

import org.manage.log.common.model.config.LogConfig;
import org.manage.log.config.provider.repository.LogConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public LogConfig getConfigByName(String configName) {
        return logConfigRepository.getByConfigName(configName);
    }

    @Override
    public List<LogConfig> getAll() {
        return logConfigRepository.getAll();
    }
}
