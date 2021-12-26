package org.manageyourlog.server.service.query;

import org.manageyourlog.server.model.LogRecord;
import org.manageyourlog.server.repository.LogRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author cartoon
 * @date 2021/12/24 20:50
 */
@Service
public class QueryLogServiceImpl implements QueryLogService {

    @Autowired
    private LogRecordRepository logRecordRepository;

    @Override
    public List<LogRecord> getLogs(String index) {
        return logRecordRepository.getByIndex(index);
    }

    @Override
    public List<LogRecord> getLogs(LocalDateTime startTime, LocalDateTime endTime) {
        return logRecordRepository.getByTime(startTime, endTime);
    }

    @Override
    public List<LogRecord> getLogs(String index, LocalDateTime startTime, LocalDateTime endTime) {
        return logRecordRepository.getByIndexAndTime(index, startTime, endTime);
    }
}
