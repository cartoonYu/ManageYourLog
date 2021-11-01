package org.manageyourlogserver.biz;

import org.manageyourlogserver.model.LogRecord;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author cartoon
 * @date 2021/10/23 17:31
 */
public interface LogRecordBiz {

    boolean saveRecord(LogRecord logRecord);

    boolean saveRecord(List<LogRecord> logRecords);

    List<LogRecord> getLogs(String index);

    List<LogRecord> getLogs(LocalDateTime startTime, LocalDateTime endTime);

    List<LogRecord> getLogs(String index, LocalDateTime startTime, LocalDateTime endTime);
}
