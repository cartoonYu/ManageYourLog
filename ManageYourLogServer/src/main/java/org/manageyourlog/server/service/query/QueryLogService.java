package org.manageyourlog.server.service.query;

import org.manageyourlog.server.model.LogRecord;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author cartoon
 * @date 2021/12/24 20:50
 */
public interface QueryLogService {

    List<LogRecord> getLogs(String index);

    List<LogRecord> getLogs(LocalDateTime startTime, LocalDateTime endTime);

    List<LogRecord> getLogs(String index, LocalDateTime startTime, LocalDateTime endTime);

}