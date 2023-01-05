package org.manage.log.receive.provider.repository.mysql;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.manage.log.common.model.config.LogConfig;
import org.manage.log.common.util.config.ApplicationConfigUtil;
import org.manage.log.common.util.factory.LoadBean;
import org.manage.log.receive.provider.config.ApplicationConfigKey;
import org.manage.log.receive.provider.repository.LogConfigRepository;
import org.manage.log.receive.provider.repository.mysql.builder.LogConfigMysqlBuilder;
import org.manage.log.receive.provider.repository.mysql.mapper.LogConfigMapper;
import org.manage.log.receive.provider.repository.mysql.mapper.LogIndexConfigMapper;
import org.manage.log.receive.provider.repository.mysql.model.LogConfigMysqlPO;
import org.manage.log.receive.provider.repository.mysql.model.LogIndexConfigMysqlPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
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
    private LogIndexConfigMapper logIndexConfigMapper;

    @Autowired
    private ApplicationConfigUtil applicationConfigUtil;

    private LoadingCache<String, LogConfig> CACHE;

    private static final LogConfigMysqlBuilder builder = LogConfigMysqlBuilder.getInstance();

    @Resource
    private TransactionTemplate transactionTemplate;

    @Override
    public boolean add(LogConfig logConfig) {
        return Boolean.TRUE.equals(transactionTemplate.execute(status -> {
            ImmutablePair<LogConfigMysqlPO, List<LogIndexConfigMysqlPO>> mysqlPo = builder.convert(logConfig);
            List<LogIndexConfigMysqlPO> indexList = mysqlPo.getRight();
            if (!indexList.isEmpty() && logIndexConfigMapper.addList(indexList) != indexList.size()) {
                return false;
            }
            return logConfigMapper.add(mysqlPo.getLeft()) == 1;
        }));
    }

    @Override
    public Optional<LogConfig> getByConfigName(String configName) {
        try {
            return Optional.of(CACHE.get(configName));
        } catch (ExecutionException e) {
            log.error("get by config name, get from cache error", e);
        }
        LogConfigMysqlPO logConfig = logConfigMapper.getByConfigName(configName);
        if(Objects.isNull(logConfig)){
            return Optional.empty();
        }
        List<LogIndexConfigMysqlPO> logIndexConfigList = logIndexConfigMapper.getByLogConfigId(logConfig.getRuleId());
        return Optional.of(builder.convert(logConfig, logIndexConfigList));
    }

    @Override
    public List<LogConfig> getByConfigNameList(List<String> configNameList) {
        //get config from cache
        List<LogConfig> configFromCache = new ArrayList<>();
        try {
            configFromCache = CACHE.getAll(configNameList).values().asList();
        } catch (Exception e){
            log.warn("get by config name, get from cache error", e);
        }
        //calculate diff with cache and source config name list
        List<String> configNameFromCache = configFromCache.stream().map(LogConfig::getRuleName).toList();
        List<String> needLoadFromDatabaseConfigNameList = configNameList.stream()
                                                                .filter(sourceConfigName -> !configNameFromCache.contains(sourceConfigName))
                                                                .toList();
        if(needLoadFromDatabaseConfigNameList.isEmpty()){
            return configFromCache;
        }
        //get data from database
        List<LogConfigMysqlPO> configPoFromDatabase = logConfigMapper.getByConfigNameList(needLoadFromDatabaseConfigNameList);
        List<String> logConfigIdFromDatabase = configPoFromDatabase.stream().map(LogConfigMysqlPO::getRuleId).toList();
        List<LogIndexConfigMysqlPO> indexConfigFromDatabase = logIndexConfigMapper.getByConfigNameList(logConfigIdFromDatabase);
        Map<String, List<LogIndexConfigMysqlPO>> configIdToIndexMap = indexConfigFromDatabase.stream().collect(Collectors.groupingBy(LogIndexConfigMysqlPO::getLogConfigId));
        List<LogConfig> configFromDatabase = configPoFromDatabase.stream().
                                                    map(config -> builder.convert(config, configIdToIndexMap.getOrDefault(config.getRuleId(), new ArrayList<>())))
                                                    .toList();
        //merge two result list and return
        configFromCache.addAll(configFromDatabase);
        return configFromCache;
    }

    @Override
    public List<LogConfig> getAll() {
        List<LogConfigMysqlPO> configMysqlPoList = logConfigMapper.getAll();
        List<LogIndexConfigMysqlPO> configIndexMysqlPoList = logIndexConfigMapper.getAll();

        return configMysqlPoList.stream().map(configMysqlPo -> {
            List<LogIndexConfigMysqlPO> indexConfigList = configIndexMysqlPoList.stream().filter(index -> configMysqlPo.getRuleId().equals(index.getLogConfigId())).toList();
            return builder.convert(configMysqlPo, indexConfigList);
        }).toList();
    }

    @PostConstruct
    private void init(){
        CACHE = CacheBuilder.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(applicationConfigUtil.get(ApplicationConfigKey.receiveLogConfigCacheExpireSecond.getKey()).map(Long::parseLong).orElse(1L), TimeUnit.SECONDS)
                .build(new CacheLoader<>() {
                    @Override
                    public LogConfig load(String configName) {
                        LogConfigMysqlPO logConfig = logConfigMapper.getByConfigName(configName);
                        if(Objects.isNull(logConfig)){
                            return null;
                        }
                        List<LogIndexConfigMysqlPO> logIndexConfigList = logIndexConfigMapper.getByLogConfigId(logConfig.getRuleId());
                        return builder.convert(logConfig, logIndexConfigList);
                    }
                });
    }
}
