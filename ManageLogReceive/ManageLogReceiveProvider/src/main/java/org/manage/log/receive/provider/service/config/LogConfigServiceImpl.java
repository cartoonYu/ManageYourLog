package org.manage.log.receive.provider.service.config;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.manage.log.common.model.config.LogConfig;
import org.manage.log.receive.provider.repository.LogConfigRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cartoon
 * @since 2022/11/14 21:53
 */
@Service
public class LogConfigServiceImpl implements LogConfigService {

    private final LogConfigRepository logConfigRepository;

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
    public List<ImmutablePair<String, String>> extractValueKey(LogConfig logConfig) {
        String contentTemplate = logConfig.getContentTemplate();
        //get value key like '#{aaa.bbb.ccc.}'
        String regex = "#\\{([A-Za-z]+\\.+)+\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(contentTemplate);
        List<String> valueFlagList = new ArrayList<>();
        while (matcher.find()){
            valueFlagList.add(matcher.group());
        }
        regex = "([A-Za-z]+\\.+)+";
        pattern = Pattern.compile(regex);
        Pattern finalPattern = pattern;
        return valueFlagList.stream().map(valueFlag -> {
            Matcher innerMatcher = finalPattern.matcher(valueFlag);
            while (innerMatcher.find()){
                // matchString like this: aaa.
                String matchString = innerMatcher.group();
                //remove . from matchString
                return ImmutablePair.of(valueFlag, matchString.substring(0, matchString.length() - 1));
            }
            return null;
        }).filter(Objects::nonNull).toList();
    }


    @Override
    public List<LogConfig> getAll() {
        return logConfigRepository.getAll();
    }

    public LogConfigServiceImpl(LogConfigRepository logConfigRepository) {
        this.logConfigRepository = logConfigRepository;
    }
}
