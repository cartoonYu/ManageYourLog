package org.manage.log.repository;

import org.manage.log.repository.model.LogRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

public interface LogRecordRepository {

    Logger log = LoggerFactory.getLogger(LogRecordRepository.class);

    boolean save(LogRecord logRecord);

    boolean save(List<LogRecord> logRecords);

    List<LogRecord> getByIndex(String index);

    List<LogRecord> getByTime(LocalDateTime startTime, LocalDateTime endTime);

    List<LogRecord> getByIndexAndTime(String index, LocalDateTime startTime, LocalDateTime endTime);
}
