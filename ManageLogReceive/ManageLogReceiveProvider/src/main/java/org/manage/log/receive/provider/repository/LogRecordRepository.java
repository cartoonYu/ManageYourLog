package org.manage.log.receive.provider.repository;

import org.manage.log.common.model.log.LogRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public interface LogRecordRepository {

    Logger log = LoggerFactory.getLogger(LogRecordRepository.class);

    boolean save(List<LogRecord> logRecords);
}
