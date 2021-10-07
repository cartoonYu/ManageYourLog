package org.manageyourlogserver.dao.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.manageyourlogserver.dao.mysql.entity.LogRecordIndexMysqlEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LogRecordIndexMysqlMapper {

    int insert(LogRecordIndexMysqlEntity indexMysqlEntity);

    List<LogRecordIndexMysqlEntity> selectByIndexValue(String indexValue);
}
