package org.manage.log.config.repository.mysql;

import org.manage.log.common.model.LogConfig;
import org.manage.log.config.repository.LogConfigRepository;
import org.manage.log.config.repository.factory.StoreRepositoryLoadCondition;
import org.manage.log.config.repository.factory.StoreRepositoryMode;
import org.manage.log.config.repository.mysql.builder.LogConfigMysqlBuilder;
import org.manage.log.config.repository.mysql.mapper.LogConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cartoon
 * @date 2022/6/4 17:52
 */
@StoreRepositoryLoadCondition(mode = StoreRepositoryMode.Mysql)
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
