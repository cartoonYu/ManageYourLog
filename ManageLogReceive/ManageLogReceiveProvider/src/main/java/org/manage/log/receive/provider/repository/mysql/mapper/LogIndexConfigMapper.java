package org.manage.log.receive.provider.repository.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.manage.log.receive.provider.repository.mysql.model.config.LogIndexConfigMysqlPO;

import java.util.List;

/**
 * @author cartoon
 * @date 2022/6/5 18:32
 */
@Mapper
public interface LogIndexConfigMapper {

    int addList(List<LogIndexConfigMysqlPO> poList);

    List<LogIndexConfigMysqlPO> getAll();

    List<LogIndexConfigMysqlPO> getByLogConfigId(String logConfigId);

    List<LogIndexConfigMysqlPO> getByConfigIdList(List<String> logConfigIdList);
}
