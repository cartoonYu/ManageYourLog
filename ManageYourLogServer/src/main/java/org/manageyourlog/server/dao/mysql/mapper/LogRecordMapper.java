package org.manageyourlog.server.dao.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.manageyourlog.server.dao.mysql.LogRecordMysqlPO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Mapper
public interface LogRecordMapper {

    int insert(LogRecordMysqlPO logRecordMysqlPO);

    int batchInsert(List<LogRecordMysqlPO> logRecordMysqlPOS);

    LogRecordMysqlPO getById(List<String> recordIds);

    List<LogRecordMysqlPO> getByTime(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    List<LogRecordMysqlPO> getByIndexAndTime(@Param("recordIds") List<String> recordIds, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}