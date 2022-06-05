package org.manage.log.config.repository;

import org.manage.log.common.model.LogConfig;

import java.util.List;

/**
 * @author cartoon
 * @date 2022/6/4 17:48
 */
public interface LogConfigRepository {

    boolean add(LogConfig logConfig);

    LogConfig getByConfigName(String configName);

    List<LogConfig> getAll();
}
