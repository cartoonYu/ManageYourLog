package org.manageyourlogserver.dao.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.manageyourlogserver.dao.mysql.entity.LogRecordMysqlEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LogRecordMysqlMapper {

    int insert(LogRecordMysqlEntity logRecordMysqlEntity);

    LogRecordMysqlEntity selectByRecordId(Long recordId);

    List<LogRecordMysqlEntity> selectByRecordIds(List<Long> recordIds);
}
