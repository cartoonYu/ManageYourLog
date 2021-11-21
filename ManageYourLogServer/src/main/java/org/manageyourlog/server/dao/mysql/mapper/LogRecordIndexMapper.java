package org.manageyourlog.server.dao.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.manageyourlog.server.dao.mysql.LogRecordIndexMysqlPO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface LogRecordIndexMapper {

    int insert(LogRecordIndexMysqlPO index);

    int batchInsert(List<LogRecordIndexMysqlPO> indexList);

    List<LogRecordIndexMysqlPO> getByIndex(String indexValue);

    List<LogRecordIndexMysqlPO> getByIndexAndTime(@Param("indexValue") String indexValue, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
