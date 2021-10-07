package org.manageyourlogserver.repository.impl;

import org.manageyourlogserver.dao.mysql.converter.LogRecordConverter;
import org.manageyourlogserver.dao.mysql.entity.LogRecordMysqlEntity;
import org.manageyourlogserver.dao.mysql.mapper.LogRecordMysqlMapper;
import org.manageyourlogserver.model.LogRecord;
import org.manageyourlogserver.model.LogRecordIndex;
import org.manageyourlogserver.repository.LogRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/07 19:35
 */
@Repository("LogRecordMysqlRepository")
public class LogRecordMysqlRepository implements LogRecordRepository {

    @Autowired
    private LogRecordMysqlMapper logRecordMysqlMapper;

    @Override
    public boolean save(LogRecord logRecord) {
        LogRecordMysqlEntity recordMysqlEntity = LogRecordConverter.convertToMysqlEntity(logRecord);
        return logRecordMysqlMapper.insert(recordMysqlEntity) == 1;
    }

    @Override
    public List<LogRecord> getByIndex(LogRecordIndex logRecordIndex) {
        return null;
    }
}
