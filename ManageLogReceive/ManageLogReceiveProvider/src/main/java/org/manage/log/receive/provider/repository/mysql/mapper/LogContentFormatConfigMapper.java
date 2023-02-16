package org.manage.log.receive.provider.repository.mysql.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.manage.log.receive.provider.repository.mysql.model.config.LogContentFormatConfigMysqlPO;

import java.util.List;

@Mapper
public interface LogContentFormatConfigMapper {

    int batchInsert(List<LogContentFormatConfigMysqlPO> pos);

    List<LogContentFormatConfigMysqlPO> getByConfigId(String configId);

    List<LogContentFormatConfigMysqlPO> getByConfigIdList(List<String> configIdList);

    List<LogContentFormatConfigMysqlPO> getAll();
}