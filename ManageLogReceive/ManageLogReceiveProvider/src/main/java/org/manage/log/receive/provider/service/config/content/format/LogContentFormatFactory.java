package org.manage.log.receive.provider.service.config.content.format;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.manage.log.common.model.config.LogConfig;
import org.manage.log.common.model.config.LogContentFormatConfig;
import org.manage.log.common.model.config.constants.LogContentFormatType;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author cartoon.yu
 * @version 1.0
 * @since 2023/1/12 20:01
 */
@Component
public class LogContentFormatFactory {

    private final Map<LogContentFormatType, LogContentFormatService> formatTypeToHandlerMap;

    public String format(LogConfig logConfig, Map<String, String> valuePropertyToValueMap){
        List<ImmutablePair<String, String>> extractValueKeyList = extractValueKey(logConfig);
        String content = logConfig.contentTemplate();
        for(ImmutablePair<String, String> extractValueKey : extractValueKeyList){
            content = content.replace(extractValueKey.getLeft(), valuePropertyToValueMap.get(extractValueKey.getRight()));
        }
        return content;
    }

    private List<ImmutablePair<String, String>> extractValueKey(LogConfig logConfig){
        List<LogContentFormatConfig> logContentFormatConfigs = logConfig.formatContentConfig().stream().sorted(Comparator.comparing(LogContentFormatConfig::executeSequence)).toList();

        //store first extract result
        List<String> sourceKeyList = new ArrayList<>();
        //store extract result after first extract
        List<String> middleResultList = new ArrayList<>();
        for(LogContentFormatConfig formatConfig : logContentFormatConfigs){
            LogContentFormatService formatHandler = formatTypeToHandlerMap.get(formatConfig.type());
            if(Objects.nonNull(formatHandler)){
                if(formatConfig.executeSequence() == 0L){
                    sourceKeyList = formatHandler.extractValueKey(formatConfig, logConfig.contentTemplate());
                    middleResultList = sourceKeyList;
                } else {
                    middleResultList = formatHandler.extractValueKey(formatConfig, middleResultList);
                }
            }
        }
        //merge source key and extract result list to return
        List<ImmutablePair<String, String>> resultList = new ArrayList<>();
        for(int index = 0; index < sourceKeyList.size(); index++){
            resultList.add(ImmutablePair.of(sourceKeyList.get(index), middleResultList.get(index)));
        }
        return resultList;
    }

    public LogContentFormatFactory(List<LogContentFormatService> formatServiceList) {
        formatTypeToHandlerMap = formatServiceList.stream().collect(Collectors.toMap(LogContentFormatService::formatType, Function.identity()));
    }
}
