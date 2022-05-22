package org.manage.log.receive.repository.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.manage.log.receive.repository.mysql.model.LogRecordIndexMysqlPO;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface LogRecordIndexMapper {

    int batchInsert(List<LogRecordIndexMysqlPO> indexList);
}
