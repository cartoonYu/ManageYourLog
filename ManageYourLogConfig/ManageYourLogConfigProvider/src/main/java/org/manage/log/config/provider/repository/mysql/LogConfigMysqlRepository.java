package org.manage.log.config.provider.repository.mysql;

import org.manage.log.common.model.config.LogConfig;
import org.manage.log.common.util.factory.LoadBean;
import org.manage.log.config.provider.repository.LogConfigRepository;
import org.manage.log.config.provider.repository.mysql.builder.LogConfigMysqlBuilder;
import org.manage.log.config.provider.repository.mysql.mapper.LogConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cartoon
 * @date 2022/6/4 17:52
 */
@LoadBean(primaryConfigKey = "store.mode", loadConfigKey = "store.load.mode", mode = "mysql")
@Repository
public class LogConfigMysqlRepository implements LogConfigRepository {

    @Autowired
    private LogConfigMapper logConfigMapper;

    private final LogConfigMysqlBuilder builder = LogConfigMysqlBuilder.getInstance();

    @Override
    public boolean add(LogConfig logConfig) {
        return logConfigMapper.add(builder.convert(logConfig)) == 1;
    }

    @Override
    public LogConfig getByConfigName(String configName) {
        return builder.convert(logConfigMapper.getByConfigName(configName));
    }

    @Override
    public List<LogConfig> getAll() {
        return  logConfigMapper.getAll().stream().map(builder::convert).collect(Collectors.toList());
    }

}
