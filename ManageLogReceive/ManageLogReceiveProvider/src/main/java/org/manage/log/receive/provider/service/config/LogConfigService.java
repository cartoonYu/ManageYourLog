package org.manage.log.receive.provider.service.config;

import org.manage.log.common.model.config.LogConfig;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author cartoon
 * @since 2022/11/14 20:51
 */
public interface LogConfigService {

    boolean add(LogConfig logConfig);

    Optional<LogConfig> getConfigByName(String configName);

    Optional<LogConfig> getConfigByRuleId(String configId);

    List<LogConfig> getByConfigNameList(List<String> configNameList);

    String formatContent(LogConfig logConfig, Map<String, String> valuePropertyToValueMap);

    List<LogConfig> getAll();
}
