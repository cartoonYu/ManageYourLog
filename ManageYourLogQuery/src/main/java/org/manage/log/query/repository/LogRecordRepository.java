package org.manage.log.query.repository;

import org.manage.log.common.model.log.LogRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

public interface LogRecordRepository {

    Logger log = LoggerFactory.getLogger(LogRecordRepository.class);

    List<LogRecord> getByIndex(String index);

    List<LogRecord> getByTime(LocalDateTime startTime, LocalDateTime endTime);

    List<LogRecord> getByIndexAndTime(String index, LocalDateTime startTime, LocalDateTime endTime);
}
