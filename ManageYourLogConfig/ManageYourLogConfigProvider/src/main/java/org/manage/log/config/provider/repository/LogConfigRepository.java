package org.manage.log.config.provider.repository;

import org.manage.log.common.model.config.LogConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author cartoon
 * @date 2022/6/4 17:48
 */
public interface LogConfigRepository {

    Logger log = LoggerFactory.getLogger(LogConfigRepository.class);

    boolean add(LogConfig logConfig);

    LogConfig getByConfigName(String configName);

    List<LogConfig> getAll();
}
