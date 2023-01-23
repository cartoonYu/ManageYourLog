package org.manage.log.receive.provider.service.config.content.format.impl;

import org.manage.log.common.model.config.LogContentFormatConfig;
import org.manage.log.common.model.config.constants.LogContentFormatType;
import org.manage.log.receive.provider.service.config.content.format.LogContentFormatService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cartoon
 * @version 1.0
 * @since 2023/1/12 20:06
 */
@Component
public class RegularExpressionMatchContentFormatServiceImpl implements LogContentFormatService {

    @Override
    public LogContentFormatType formatType() {
        return LogContentFormatType.REGULAR_EXPRESSION_MATCH;
    }

    @Override
    public List<String> firstExtractValueKey(LogContentFormatConfig formatConfig, String contentTemplate) {
        String regex = formatConfig.value();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(contentTemplate);
        List<String> resultList = new ArrayList<>();
        while (matcher.find()){
            resultList.add(matcher.group());
        }
        return resultList;
    }

    @Override
    public List<String> extractValueKey(LogContentFormatConfig formatConfig, List<String> sourceValueKeyList) {
        String regex = formatConfig.value();
        final Pattern pattern = Pattern.compile(regex);

        return sourceValueKeyList.stream().map(sourceValueKey -> {
            Matcher matcher = pattern.matcher(sourceValueKey);
            while (matcher.find()){
                //return first match result
                return matcher.group();
            }
            return null;
        }).filter(Objects::nonNull).toList();

    }
}
