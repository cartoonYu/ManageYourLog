package org.manageyourlog.server.repository;

import org.manageyourlog.server.model.LogRecord;

import java.time.LocalDateTime;
import java.util.List;

public interface LogRecordRepository {

    boolean save(LogRecord logRecord);

    boolean save(List<LogRecord> logRecords);

    List<LogRecord> getByIndex(String index);

    List<LogRecord> getByTime(LocalDateTime startTime, LocalDateTime endTime);

    List<LogRecord> getByIndexAndTime(String index, LocalDateTime startTime, LocalDateTime endTime);
}
