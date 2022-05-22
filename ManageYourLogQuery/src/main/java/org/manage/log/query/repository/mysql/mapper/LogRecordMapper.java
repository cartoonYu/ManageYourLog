package org.manage.log.query.repository.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.manage.log.query.repository.mysql.model.LogRecordMysqlPO;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface LogRecordMapper {

    int insert(LogRecordMysqlPO logRecordMysqlPO);

    int batchInsert(List<LogRecordMysqlPO> logRecordMysqlPOS);

    List<LogRecordMysqlPO> getById(List<String> recordIds);

    List<LogRecordMysqlPO> getByTime(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    List<LogRecordMysqlPO> getByIndexAndTime(@Param("recordIds") List<String> recordIds, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}