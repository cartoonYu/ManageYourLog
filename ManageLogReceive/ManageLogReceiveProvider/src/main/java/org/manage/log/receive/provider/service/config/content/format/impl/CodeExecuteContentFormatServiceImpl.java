package org.manage.log.receive.provider.service.config.content.format.impl;

import org.manage.log.common.model.config.LogContentFormatConfig;
import org.manage.log.common.model.config.constants.LogContentFormatType;
import org.manage.log.receive.provider.service.config.content.format.LogContentFormatService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 * @since 2023/1/12 20:43
 */
@Component
public class CodeExecuteContentFormatServiceImpl implements LogContentFormatService {

    @Override
    public LogContentFormatType formatType() {
        return LogContentFormatType.CODE_EXECUTE;
    }

    @Override
    public List<String> extractValueKey(LogContentFormatConfig formatConfig, String contentTemplate) {
        //todo use aviator to execute
        return null;
    }

    @Override
    public List<String> extractValueKey(LogContentFormatConfig formatConfigs, List<String> sourceValueKeyList) {
        //todo use aviator to execute
        //remove . from matchString default
        return sourceValueKeyList.stream().map(sourceValueKey -> sourceValueKey.substring(0, sourceValueKey.length() - 1)).toList();
    }
}
