package org.manage.log.receive.provider.service.config.content.format;


import org.manage.log.common.model.config.LogContentFormatConfig;
import org.manage.log.common.model.config.constants.LogContentFormatType;

import java.util.Collections;
import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 * @since 2023/1/11 20:51
 */
public interface LogContentFormatService {

    LogContentFormatType formatType();

    /**
     * use to extract key from content template, return empty list default
     * @param formatConfig
     * @param contentTemplate
     * @return content key list
     */
    default List<String> firstExtractValueKey(LogContentFormatConfig formatConfig, String contentTemplate){
        return Collections.emptyList();
    }

    List<String> extractValueKey(LogContentFormatConfig formatConfigs, List<String> sourceValueKeyList);

}
