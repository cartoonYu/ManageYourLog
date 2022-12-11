package org.manage.log.receive.provider.repository.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.manage.log.receive.provider.repository.mysql.model.LogConfigMysqlPO;

import java.util.List;

/**
 * @author cartoon
 * @date 2022/6/5 18:32
 */
@Mapper
public interface LogConfigMapper {


    int add(LogConfigMysqlPO po);

    LogConfigMysqlPO getByConfigName(String configName);

    List<LogConfigMysqlPO> getAll();
}
