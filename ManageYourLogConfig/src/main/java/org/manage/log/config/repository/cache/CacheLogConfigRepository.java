package org.manage.log.config.repository.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.manage.log.common.model.LogConfig;
import org.manage.log.common.util.config.ApplicationConfigUtil;
import org.manage.log.common.util.factory.LoadBean;
import org.manage.log.config.repository.LogConfigRepository;
import org.manage.log.config.repository.config.ApplicationConfigKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author cartoon
 * @date 2022/6/9 11:51
 */
@LoadBean(primaryConfigKey = "cache.mode", loadConfigKey = "cache.mode", mode = "cache")
@Repository
public class CacheLogConfigRepository implements LogConfigRepository {

    @Autowired
    @Qualifier("actualConfigRepository")
    private LogConfigRepository actualConfigRepository;

    @Autowired
    private ApplicationConfigUtil applicationConfigUtil;

    private LoadingCache<String, LogConfig> CACHE;

    @Override
    public boolean add(LogConfig logConfig) {
        return actualConfigRepository.add(logConfig);
    }

    @Override
    public LogConfig getByConfigName(String configName) {
        try {
            return CACHE.get(configName);
        } catch (ExecutionException e) {
            log.error("get by config name, get from cache error", e);
        }
        return actualConfigRepository.getByConfigName(configName);
    }

    @Override
    public List<LogConfig> getAll() {
        return actualConfigRepository.getAll();
    }

    @PostConstruct
    private void init(){
        CACHE = CacheBuilder.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(applicationConfigUtil.get(ApplicationConfigKey.CACHE_EXPIRE.getKey()).map(Long::parseLong).orElse(1L), TimeUnit.SECONDS)
                .build(new CacheLoader<>() {
                    @Override
                    public LogConfig load(String configName) {
                        return actualConfigRepository.getByConfigName(configName);
                    }
                });
    }
}
