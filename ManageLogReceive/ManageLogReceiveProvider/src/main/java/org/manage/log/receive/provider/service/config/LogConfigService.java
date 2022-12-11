package org.manage.log.receive.provider.service.config;

import org.manage.log.common.model.config.LogConfig;

import java.util.List;

/**
 * @author cartoon
 * @since 2022/11/14 20:51
 */
public interface LogConfigService {

    boolean add(LogConfig logConfig);

    LogConfig getConfigByName(String configName);

    List<LogConfig> getAll();
}
