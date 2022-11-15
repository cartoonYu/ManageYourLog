package org.manage.log.query.service;

import com.google.common.collect.ImmutableList;
import org.manage.log.common.model.log.LogRecord;
import org.manage.log.query.repository.LogRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        return Optional.ofNullable(index).map(value -> logRecordRepository.getByIndex(index)).orElse(ImmutableList.of());
    }

    @Override
    public List<LogRecord> getLogs(LocalDateTime startTime, LocalDateTime endTime) {
        if(Objects.isNull(startTime) || Objects.isNull(endTime)){
            log.warn("query log, query between time, param is illegal, startTime: {}, endTime: {}", startTime, endTime);
            return ImmutableList.of();
        }
        return logRecordRepository.getByTime(startTime, endTime);
    }

    @Override
    public List<LogRecord> getLogs(String index, LocalDateTime startTime, LocalDateTime endTime) {
        if(Objects.isNull(startTime) || Objects.isNull(endTime) || Objects.isNull(index)){
            log.warn("query log, query between time, param is illegal, startTime: {}, endTime: {}", startTime, endTime);
            return ImmutableList.of();
        }
        return logRecordRepository.getByIndexAndTime(index, startTime, endTime);
    }
}
