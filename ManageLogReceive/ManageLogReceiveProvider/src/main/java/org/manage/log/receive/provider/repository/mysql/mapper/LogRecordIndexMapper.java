package org.manage.log.receive.provider.repository.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.manage.log.receive.provider.repository.mysql.model.LogRecordIndexMysqlPO;

import java.util.List;

@Mapper
public interface LogRecordIndexMapper {

    int batchInsert(List<LogRecordIndexMysqlPO> indexList);
}
