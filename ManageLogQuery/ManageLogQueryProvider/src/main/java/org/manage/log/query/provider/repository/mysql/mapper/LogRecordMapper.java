package org.manage.log.query.provider.repository.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.manage.log.query.provider.repository.mysql.model.LogRecordMysqlPO;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface LogRecordMapper {

    List<LogRecordMysqlPO> getById(List<String> recordIds);

    List<LogRecordMysqlPO> getByTime(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}