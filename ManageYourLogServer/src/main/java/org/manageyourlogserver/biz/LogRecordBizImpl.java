package org.manageyourlogserver.biz;

import com.google.common.collect.ImmutableList;
import org.manageyourlogcommon.util.CollectionUtil;
import org.manageyourlogcommon.util.IdGenerateUtil;
import org.manageyourlogserver.model.LogRecord;
import org.manageyourlogserver.model.LogRecordIndex;
import org.manageyourlogserver.repository.LogRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return ofNullable(index)
                .map(indexWord -> logRecordRepository.getByIndex(index))
                .orElse(ImmutableList.of());
    }

    private LogRecord initLogRecord(LogRecord logRecord){
        return ofNullable(logRecord).map(record -> {
            record.setRecordId(IdGenerateUtil.generate(13))
                    .setVersion(1);
            List<LogRecordIndex> logRecordIndices = record.getIndexList();
            ofNullable(logRecordIndices)
                    .ifPresent(indexList ->
                            indexList.forEach(index ->
                                    index.setIndexId(IdGenerateUtil.generate(13))
                                            .setVersion(1)
                            )
                    );
            return record;
        }).orElse(null);
    }
}
