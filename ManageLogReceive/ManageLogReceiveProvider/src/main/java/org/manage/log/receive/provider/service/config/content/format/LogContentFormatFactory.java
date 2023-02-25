package org.manage.log.receive.provider.service.config.content.format;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.manage.log.common.model.config.LogConfig;
import org.manage.log.common.model.config.LogContentFormatConfig;
import org.manage.log.common.model.config.constants.LogContentFormatType;
import org.manage.log.common.util.config.ApplicationConfigUtil;
import org.manage.log.receive.provider.config.ApplicationConfigKey;
import org.manage.log.receive.provider.repository.LogConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author cartoon
 * @version 1.0
 * @since 2023/1/12 20:01
 */
@Component
public class LogContentFormatFactory {

    private static final Logger log = LoggerFactory.getLogger(LogContentFormatFactory.class);

    private final Map<LogContentFormatType, LogContentFormatService> formatTypeToHandlerMap;

    private final LoadingCache<String, Map<String, String>> CONFIG_RULE_ID_TO_VALUE_KEY_CACHE;

    /**
     * extract value key from config
     * @param logConfig config
     * @return source key to result key list
     */
    public Map<String, String> extractValueKey(LogConfig logConfig){
        try {
            return CONFIG_RULE_ID_TO_VALUE_KEY_CACHE.get(logConfig.ruleId());
        } catch (Exception e){
            log.error("get by config name, get from cache error", e);
        }
        return Collections.emptyMap();
    }

    private Map<String, String> extractValueKeyFromConfig(LogConfig logConfig){
        List<LogContentFormatConfig> logContentFormatConfigs = logConfig.formatContentConfig().parallelStream().sorted(Comparator.comparing(LogContentFormatConfig::executeSequence)).toList();
        //store first extract result
        List<String> sourceKeyList = new ArrayList<>();
        //store extract result after first extract
        List<String> middleResultList = new ArrayList<>();
        for(LogContentFormatConfig formatConfig : logContentFormatConfigs){
            LogContentFormatService formatHandler = formatTypeToHandlerMap.get(formatConfig.type());
            if(Objects.nonNull(formatHandler)){
                if(formatConfig.executeSequence() == 0L){
                    sourceKeyList = formatHandler.firstExtractValueKey(formatConfig, logConfig.contentTemplate());
                    middleResultList = sourceKeyList;
                } else {
                    middleResultList = formatHandler.extractValueKey(formatConfig, middleResultList);
                }
            }
        }
        //merge source key and extract result list to return
        Map<String, String> resultList = new HashMap<>();
        for(int index = 0; index < sourceKeyList.size(); index++){
            resultList.put(sourceKeyList.get(index), middleResultList.get(index));
        }
        return resultList;
    }

    public LogContentFormatFactory(List<LogContentFormatService> formatServiceList, ApplicationConfigUtil applicationConfigUtil, LogConfigRepository logConfigRepository) {
        formatTypeToHandlerMap = formatServiceList.parallelStream().collect(Collectors.toMap(LogContentFormatService::formatType, Function.identity()));

        CONFIG_RULE_ID_TO_VALUE_KEY_CACHE = CacheBuilder.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(applicationConfigUtil.get(ApplicationConfigKey.receiveLogConfigCacheExpireSecond.getKey()).map(Long::parseLong).orElse(1L), TimeUnit.SECONDS)
                .build(new CacheLoader<>() {


                    @Override
                    public Map<String, String> load(String logConfigId) {
                        return logConfigRepository.getByConfigId(logConfigId)
                                .map(config -> extractValueKeyFromConfig(config))
                                .orElse(null);
                    }
                });
    }
}
