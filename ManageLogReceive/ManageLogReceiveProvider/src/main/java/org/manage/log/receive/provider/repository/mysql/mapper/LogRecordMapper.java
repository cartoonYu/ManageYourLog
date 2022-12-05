package org.manage.log.receive.provider.repository.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.manage.log.receive.provider.repository.mysql.model.LogRecordMysqlPO;

import java.util.List;

@Mapper
public interface LogRecordMapper {

    int insert(LogRecordMysqlPO logRecordMysqlPO);

    int batchInsert(List<LogRecordMysqlPO> logRecordMysqlPOS);
}