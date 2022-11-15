package org.manage.log.config.facade;

import org.manage.log.config.facade.dto.LogConfigDto;

/**
 * @author cartoon
 * @since 2022/11/15 20:30
 */
public interface ConfigService {

    LogConfigDto getByName(String configName);
}
