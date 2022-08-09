package org.manage.log.config.repository;

import org.manage.log.common.model.LogConfig;
import org.manage.log.common.util.factory.LoadBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cartoon
 * @date 2022/6/9 11:51
 */
@LoadBean(primaryConfigKey = "cache.mode", loadConfigKey = "cache.mode", mode = "redis")
@Repository
public class CacheLogConfigRepository implements LogConfigRepository{

    @Autowired
    @Qualifier("actualConfigRepository")
    private LogConfigRepository actualConfigRepository;

    @Override
    public boolean add(LogConfig logConfig) {
        log.info("data from redis");
        return actualConfigRepository.add(logConfig);
    }

    @Override
    public LogConfig getByConfigName(String configName) {
        log.info("data from redis");
        return actualConfigRepository.getByConfigName(configName);
    }

    @Override
    public List<LogConfig> getAll() {
        log.info("data from redis");
        return actualConfigRepository.getAll();
    }
}
