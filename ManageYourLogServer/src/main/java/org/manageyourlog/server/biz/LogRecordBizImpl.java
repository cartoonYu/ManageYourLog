package org.manageyourlog.server.biz;

import org.manageyourlog.common.util.CollectionUtil;
import org.manageyourlog.common.util.IdGenerateUtil;
import org.manageyourlog.server.model.LogRecord;
import org.manageyourlog.server.model.LogRecordIndex;
import org.manageyourlog.server.repository.LogRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Optional.ofNullable;

/**
 * @author cartoon
 * @date 2021/10/23 17:40
 */
@Service
public class LogRecordBizImpl implements LogRecordBiz{

    @Autowired
    private LogRecordRepository logRecordRepository;

    @Override
    public boolean saveRecord(LogRecord logRecord) {
        return ofNullable(logRecord).map(record -> {
            record = initLogRecord(record);
            return logRecordRepository.save(record);
        }).orElse(false);
    }

    @Override
    public boolean saveRecord(List<LogRecord> logRecords) {
        return ofNullable(logRecords).map(recordList -> {
            if(CollectionUtil.judgeIsEmpty(recordList)){
                return false;
            }
            recordList.forEach(record -> record = initLogRecord(record));
            return logRecordRepository.save(recordList);
        }).orElse(false);
    }

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

    private LogRecord initLogRecord(LogRecord logRecord){
        String recordId = IdGenerateUtil.generate(13);
        return ofNullable(logRecord).map(record -> {
            record.setRecordId(recordId)
                    .setVersion(1);
            List<LogRecordIndex> logRecordIndices = record.getIndexList();
            ofNullable(logRecordIndices)
                    .ifPresent(indexList ->
                            indexList.forEach(index ->
                                    index.setIndexId(IdGenerateUtil.generate(13))
                                            .setVersion(1)
                                            .setLogRecordId(recordId)
                            )
                    );
            return record;
        }).orElse(null);
    }
}
