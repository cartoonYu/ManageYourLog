package org.manageyourlog.server.dao.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.manageyourlog.server.dao.mysql.model.LogRecordIndexMysqlPO;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface LogRecordIndexMapper {

    int insert(LogRecordIndexMysqlPO index);

    int batchInsert(List<LogRecordIndexMysqlPO> indexList);

    List<LogRecordIndexMysqlPO> getByIndex(String indexValue);

    List<LogRecordIndexMysqlPO> getByIndexIds(List<String> indexIds);

    List<LogRecordIndexMysqlPO> getByIndexAndTime(@Param("indexValue") String indexValue, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
