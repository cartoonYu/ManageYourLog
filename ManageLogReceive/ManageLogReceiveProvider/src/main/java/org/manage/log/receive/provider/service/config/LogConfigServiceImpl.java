package org.manage.log.receive.provider.service.config;

import org.manage.log.common.model.config.LogConfig;
import org.manage.log.receive.provider.repository.LogConfigRepository;
import org.manage.log.receive.provider.service.config.content.format.LogContentFormatFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @author cartoon
 * @since 2022/11/14 21:53
 */
@Service
public class LogConfigServiceImpl implements LogConfigService {

    private final LogConfigRepository logConfigRepository;

    private final LogContentFormatFactory logContentFormatFactory;

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
    public List<LogConfig> getByConfigNameList(List<String> configNameList) {
        return logConfigRepository.getByConfigNameList(configNameList);
    }

    @Override
    public String formatContent(LogConfig logConfig, Map<String, String> valuePropertyToValueMap) {
        Map<String, String> extractValueKeyList = logContentFormatFactory.extractValueKey(logConfig);
        String content = logConfig.contentTemplate();
        for(Map.Entry<String, String> entry : extractValueKeyList.entrySet()){
            content = content.replace(entry.getKey(), valuePropertyToValueMap.get(entry.getValue()));
        }
        return content;
    }

    @Override
    public List<LogConfig> getAll() {
        return logConfigRepository.getAll();
    }

    public LogConfigServiceImpl(LogConfigRepository logConfigRepository,
                                    LogContentFormatFactory logContentFormatFactory) {
        this.logConfigRepository = logConfigRepository;
        this.logContentFormatFactory = logContentFormatFactory;
    }
}
