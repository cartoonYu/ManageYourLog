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
import org.manage.log.receive.provider.repository.mysql.mapper.LogContentFormatConfigMapper;
import org.manage.log.receive.provider.repository.mysql.mapper.LogIndexConfigMapper;
import org.manage.log.receive.provider.repository.mysql.model.config.LogConfigMysqlPO;
import org.manage.log.receive.provider.repository.mysql.model.config.LogContentFormatConfigMysqlPO;
import org.manage.log.receive.provider.repository.mysql.model.config.LogIndexConfigMysqlPO;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author cartoon
 * @date 2022/6/9 11:51
 */
@LoadBean(primaryConfigKey = "store.mode", loadConfigKey = "store.load.mode", mode = "mysql")
@Repository
public class LogConfigMysqlRepository implements LogConfigRepository {

    private final LogConfigMapper logConfigMapper;

    private final LogIndexConfigMapper logIndexConfigMapper;

    private final LogContentFormatConfigMapper logContentFormatConfigMapper;

    private final LoadingCache<String, LogConfig> CONFIG_NAME_TO_CONFIG_CACHE;

    private final LoadingCache<String, LogConfig> CONFIG_ID_TO_CONFIG_CACHE;

    private final LogConfigMysqlBuilder builder;

    private final MysqlDatasourceOperate mysqlDatasourceOperate;

    @Override
    public boolean add(LogConfig logConfig) {
        LogConfigMysqlPO logConfigMysqlPO = builder.convertToLogConfig(logConfig);
        List<LogIndexConfigMysqlPO> indexConfigMysqlPOList = builder.convertToLogIndexConfig(logConfig);
        List<LogContentFormatConfigMysqlPO> contentformatMysqlPOList = builder.convertToContentFormatConfig(logConfig);
        List<ImmutablePair<Supplier<Integer>, Integer>> executeFunctionToExpectExecuteRowList = new ArrayList<>();
        if(!indexConfigMysqlPOList.isEmpty()){
            executeFunctionToExpectExecuteRowList.add(ImmutablePair.of(() -> logIndexConfigMapper.addList(indexConfigMysqlPOList), indexConfigMysqlPOList.size()));
        }
        if(!contentformatMysqlPOList.isEmpty()){
            executeFunctionToExpectExecuteRowList.add(ImmutablePair.of(() -> logContentFormatConfigMapper.batchInsert(contentformatMysqlPOList), contentformatMysqlPOList.size()));
        }
        executeFunctionToExpectExecuteRowList.add(
                ImmutablePair.of(() -> logConfigMapper.add(logConfigMysqlPO), 1)
        );
        return mysqlDatasourceOperate.executeDML(executeFunctionToExpectExecuteRowList);
    }

    @Override
    public Optional<LogConfig> getByConfigId(String configId) {
        try {
            return Optional.of(CONFIG_ID_TO_CONFIG_CACHE.get(configId));
        } catch (ExecutionException e) {
            log.error("get by config name, get from cache error", e);
        }
        LogConfigMysqlPO logConfig = logConfigMapper.getByConfigId(configId);
        if(Objects.isNull(logConfig)){
            return Optional.empty();
        }
        List<LogIndexConfigMysqlPO> logIndexConfigList = logIndexConfigMapper.getByLogConfigId(logConfig.ruleId());
        List<LogContentFormatConfigMysqlPO> contentFormatConfigList = logContentFormatConfigMapper.getByConfigId(logConfig.ruleId());
        return Optional.of(builder.convert(logConfig, logIndexConfigList, contentFormatConfigList));
    }

    @Override
    public Optional<LogConfig> getByConfigName(String configName) {
        try {
            return Optional.of(CONFIG_NAME_TO_CONFIG_CACHE.get(configName));
        } catch (ExecutionException e) {
            log.error("get by config name, get from cache error", e);
        }
        LogConfigMysqlPO logConfig = logConfigMapper.getByConfigName(configName);
        if(Objects.isNull(logConfig)){
            return Optional.empty();
        }
        List<LogIndexConfigMysqlPO> logIndexConfigList = logIndexConfigMapper.getByLogConfigId(logConfig.ruleId());
        List<LogContentFormatConfigMysqlPO> contentFormatConfigList = logContentFormatConfigMapper.getByConfigId(logConfig.ruleId());
        return Optional.of(builder.convert(logConfig, logIndexConfigList, contentFormatConfigList));
    }

    @Override
    public List<LogConfig> getByConfigNameList(List<String> configNameList) {
        //get config from cache
        List<LogConfig> configFromCache = new ArrayList<>();
        try {
            configFromCache = CONFIG_NAME_TO_CONFIG_CACHE.getAll(configNameList).values().asList();
        } catch (Exception e){
            log.warn("get by config name, get from cache error", e);
        }
        //calculate diff with cache and source config name list
        List<String> configNameFromCache = configFromCache.parallelStream().map(LogConfig::ruleName).toList();
        List<String> needLoadFromDatabaseConfigNameList = configNameList.parallelStream()
                                                                .filter(sourceConfigName -> !configNameFromCache.contains(sourceConfigName))
                                                                .toList();
        if(needLoadFromDatabaseConfigNameList.isEmpty()){
            return configFromCache;
        }
        //get data from database
        List<LogConfigMysqlPO> configPoFromDatabase = logConfigMapper.getByConfigNameList(needLoadFromDatabaseConfigNameList);
        List<String> logConfigIdFromDatabase = configPoFromDatabase.parallelStream().map(LogConfigMysqlPO::ruleId).toList();
        List<LogIndexConfigMysqlPO> indexConfigFromDatabase = logIndexConfigMapper.getByConfigIdList(logConfigIdFromDatabase);
        List<LogContentFormatConfigMysqlPO> contentFormatConfigFromDatabase = logContentFormatConfigMapper.getByConfigIdList(logConfigIdFromDatabase);
        Map<String, List<LogIndexConfigMysqlPO>> configIdToIndexMap = indexConfigFromDatabase.parallelStream().collect(Collectors.groupingBy(LogIndexConfigMysqlPO::logConfigId));
        Map<String, List<LogContentFormatConfigMysqlPO>> configIdToFormatMap = contentFormatConfigFromDatabase.parallelStream().collect(Collectors.groupingBy(LogContentFormatConfigMysqlPO::logConfigId));
        List<LogConfig> configFromDatabase = configPoFromDatabase.parallelStream().
                                                    map(config -> builder.convert(config,
                                                                                    configIdToIndexMap.getOrDefault(config.ruleId(), new ArrayList<>()),
                                                                                    configIdToFormatMap.getOrDefault(config.ruleId(), new ArrayList<>())))
                                                    .toList();
        //merge two result list and return
        configFromCache.addAll(configFromDatabase);
        return configFromCache;
    }

    @Override
    public List<LogConfig> getAll() {
        List<LogConfigMysqlPO> configMysqlPoList = logConfigMapper.getAll();
        List<LogIndexConfigMysqlPO> configIndexMysqlPoList = logIndexConfigMapper.getAll();
        List<LogContentFormatConfigMysqlPO> contentFormatConfigList = logContentFormatConfigMapper.getAll();

        Map<String, List<LogIndexConfigMysqlPO>> configIdToIndexMap = configIndexMysqlPoList.parallelStream().collect(Collectors.groupingBy(LogIndexConfigMysqlPO::logConfigId));
        Map<String, List<LogContentFormatConfigMysqlPO>> configIdToFormatMap = contentFormatConfigList.parallelStream().collect(Collectors.groupingBy(LogContentFormatConfigMysqlPO::logConfigId));
        return configMysqlPoList.parallelStream().map(configMysqlPo -> {
            String configId = configMysqlPo.ruleId();
            return builder.convert(configMysqlPo, configIdToIndexMap.getOrDefault(configId, new ArrayList<>()), configIdToFormatMap.getOrDefault(configId, new ArrayList<>()));
        }).toList();
    }

    public LogConfigMysqlRepository(LogConfigMapper logConfigMapper,
                                        LogIndexConfigMapper logIndexConfigMapper,
                                        LogContentFormatConfigMapper logContentFormatConfigMapper,
                                        ApplicationConfigUtil applicationConfigUtil,
                                    LogConfigMysqlBuilder logConfigMysqlBuilder,
                                    MysqlDatasourceOperate mysqlDatasourceOperate) {
        this.logConfigMapper = logConfigMapper;
        this.logIndexConfigMapper = logIndexConfigMapper;
        this.logContentFormatConfigMapper = logContentFormatConfigMapper;
        this.builder = logConfigMysqlBuilder;
        this.mysqlDatasourceOperate = mysqlDatasourceOperate;

        CONFIG_NAME_TO_CONFIG_CACHE = CacheBuilder.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(applicationConfigUtil.get(ApplicationConfigKey.receiveLogConfigCacheExpireSecond.getKey()).map(Long::parseLong).orElse(1L), TimeUnit.SECONDS)
                .build(new CacheLoader<>() {
                    @Override
                    public LogConfig load(String configName) {
                        LogConfigMysqlPO logConfig = logConfigMapper.getByConfigName(configName);
                        if(Objects.isNull(logConfig)){
                            return null;
                        }
                        List<LogIndexConfigMysqlPO> logIndexConfigList = logIndexConfigMapper.getByLogConfigId(logConfig.ruleId());
                        List<LogContentFormatConfigMysqlPO> contentFormatConfigList = logContentFormatConfigMapper.getByConfigId(logConfig.ruleId());
                        return builder.convert(logConfig, logIndexConfigList, contentFormatConfigList);
                    }
                });

        CONFIG_ID_TO_CONFIG_CACHE = CacheBuilder.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(applicationConfigUtil.get(ApplicationConfigKey.receiveLogConfigCacheExpireSecond.getKey()).map(Long::parseLong).orElse(1L), TimeUnit.SECONDS)
                .build(new CacheLoader<>() {
                    @Override
                    public LogConfig load(String configId) {
                        LogConfigMysqlPO logConfig = logConfigMapper.getByConfigId(configId);
                        if(Objects.isNull(logConfig)){
                            return null;
                        }
                        List<LogIndexConfigMysqlPO> logIndexConfigList = logIndexConfigMapper.getByLogConfigId(logConfig.ruleId());
                        List<LogContentFormatConfigMysqlPO> contentFormatConfigList = logContentFormatConfigMapper.getByConfigId(logConfig.ruleId());
                        return builder.convert(logConfig, logIndexConfigList, contentFormatConfigList);
                    }
                });
    }

}
