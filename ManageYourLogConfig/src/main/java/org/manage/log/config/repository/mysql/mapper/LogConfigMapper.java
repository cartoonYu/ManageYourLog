package org.manage.log.config.repository.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.manage.log.config.repository.mysql.model.LogConfigMysqlPO;

import java.util.List;

/**
 * @author cartoon.yu
 * @date 2022/6/5 18:32
 */
@Mapper
public interface LogConfigMapper {

    int add(LogConfigMysqlPO po);

    LogConfigMysqlPO getByConfigName(String configName);

    List<LogConfigMysqlPO> getAll();
}
