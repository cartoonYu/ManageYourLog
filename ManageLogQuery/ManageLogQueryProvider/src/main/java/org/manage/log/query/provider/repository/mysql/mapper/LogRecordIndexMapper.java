package org.manage.log.query.provider.repository.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.manage.log.query.provider.repository.mysql.model.LogRecordIndexMysqlPO;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface LogRecordIndexMapper {

    List<LogRecordIndexMysqlPO> getByRecordIdList(List<String> recordIds);

    List<LogRecordIndexMysqlPO> getByIndex(String indexValue);

    List<LogRecordIndexMysqlPO> getByIndexAndTime(@Param("indexValue") String indexValue, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
