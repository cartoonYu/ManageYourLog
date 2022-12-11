package org.manage.log.receive.provider.repository.mysql;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.manage.log.common.model.config.LogConfig;
import org.manage.log.common.util.config.ApplicationConfigUtil;
import org.manage.log.common.util.factory.LoadBean;
import org.manage.log.receive.provider.config.ApplicationConfigKey;
import org.manage.log.receive.provider.repository.LogConfigRepository;
import org.manage.log.receive.provider.repository.mysql.builder.LogConfigMysqlBuilder;
import org.manage.log.receive.provider.repository.mysql.mapper.LogConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author cartoon
 * @date 2022/6/9 11:51
 */
@LoadBean(primaryConfigKey = "store.mode", loadConfigKey = "store.load.mode", mode = "mysql")
@Repository
public class LogConfigMysqlRepository implements LogConfigRepository {

    @Autowired
    private LogConfigMapper logConfigMapper;

    @Autowired
    private ApplicationConfigUtil applicationConfigUtil;

    private LoadingCache<String, LogConfig> CACHE;

    private static final LogConfigMysqlBuilder builder = LogConfigMysqlBuilder.getInstance();


    @Override
    public boolean add(LogConfig logConfig) {
        return logConfigMapper.add(builder.convert(logConfig)) == 1;
    }

    @Override
    public LogConfig getByConfigName(String configName) {
        try {
            return CACHE.get(configName);
        } catch (ExecutionException e) {
            log.error("get by config name, get from cache error", e);
        }
        return builder.convert(logConfigMapper.getByConfigName(configName));
    }

    @Override
    public List<LogConfig> getAll() {
        return logConfigMapper.getAll().stream().map(builder::convert).collect(Collectors.toList());
    }

    @PostConstruct
    private void init(){
        CACHE = CacheBuilder.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(applicationConfigUtil.get(ApplicationConfigKey.receiveLogConfigCacheExpireSecond.getKey()).map(Long::parseLong).orElse(1L), TimeUnit.SECONDS)
                .build(new CacheLoader<>() {
                    @Override
                    public LogConfig load(String configName) {
                        return builder.convert(logConfigMapper.getByConfigName(configName));
                    }
                });
    }
}
