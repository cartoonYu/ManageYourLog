package org.manage.log.query.service;

import org.manage.log.common.model.LogRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author cartoon
 * @date 2021/12/24 20:50
 */
public interface QueryLogService {

    Logger log = LoggerFactory.getLogger(QueryLogService.class);

    List<LogRecord> getLogs(String index);

    List<LogRecord> getLogs(LocalDateTime startTime, LocalDateTime endTime);

    List<LogRecord> getLogs(String index, LocalDateTime startTime, LocalDateTime endTime);

}
