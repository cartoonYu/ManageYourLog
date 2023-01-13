package org.manage.log.receive.provider.service.config.content.format;


import org.manage.log.common.model.config.LogContentFormatConfig;
import org.manage.log.common.model.config.constants.LogContentFormatType;

import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 * @since 2023/1/11 20:51
 */
public interface LogContentFormatService {

    LogContentFormatType formatType();

    List<String> extractValueKey(LogContentFormatConfig formatConfig, String contentTemplate);

    List<String> extractValueKey(LogContentFormatConfig formatConfigs, List<String> sourceValueKeyList);

}
