package org.manage.log.receive.provider.service.config.content.format;


import org.apache.commons.lang3.tuple.ImmutablePair;
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

    List<ImmutablePair<String, String>> extractValueKey(List<LogContentFormatConfig> formatConfigs, Integer currentIndex);

}
