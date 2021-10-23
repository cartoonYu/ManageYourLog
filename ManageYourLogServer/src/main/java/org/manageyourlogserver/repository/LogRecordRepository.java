package org.manageyourlogserver.repository;

import org.manageyourlogserver.model.LogRecord;

import java.util.List;

public interface LogRecordRepository {

    boolean save(LogRecord logRecord);

    boolean save(List<LogRecord> logRecords);

    List<LogRecord> getByIndex(String index);
}
