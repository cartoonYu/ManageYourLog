package org.manageyourlogserver.repository;

import org.manageyourlogserver.model.LogRecord;
import org.manageyourlogserver.model.LogRecordIndex;

import java.util.List;

public interface LogRecordRepository {

    boolean save(LogRecord logRecord);

    List<LogRecord> getByIndex(LogRecordIndex logRecordIndex);

}
