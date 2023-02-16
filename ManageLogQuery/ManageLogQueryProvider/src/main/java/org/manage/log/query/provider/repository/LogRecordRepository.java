package org.manage.log.query.provider.repository;

import org.manage.log.common.model.log.LogRecord;

import java.time.LocalDateTime;
import java.util.List;

public interface LogRecordRepository {

    List<LogRecord> getByIndex(String index);

    List<LogRecord> getByTime(LocalDateTime startTime, LocalDateTime endTime);

    List<LogRecord> getByIndexAndTime(String index, LocalDateTime startTime, LocalDateTime endTime);
}
