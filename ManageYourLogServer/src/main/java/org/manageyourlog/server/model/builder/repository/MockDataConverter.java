package org.manageyourlog.server.model.builder.repository;

import org.manageyourlog.common.constants.LogRecordIndexSort;
import org.manageyourlog.common.constants.LogRecordSort;
import org.manageyourlog.server.dao.mock.model.LogRecordIndexMockEntity;
import org.manageyourlog.server.dao.mock.model.LogRecordMockEntity;
import org.manageyourlog.server.model.LogRecord;
import org.manageyourlog.server.model.LogRecordIndex;
import org.manageyourlog.server.model.builder.service.LogRecordConverter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/26 00:29
 */
public class MockDataConverter {

    private static final MockDataConverter INSTANCE = new MockDataConverter();

    public static MockDataConverter getInstance(){
        return INSTANCE;
    }

    public List<LogRecord> convert(List<LogRecordMockEntity> recordMockEntities, List<LogRecordIndexMockEntity> recordIndexMockEntities){
        return recordMockEntities.stream().map(record -> {
            LogRecord logRecord = new LogRecord();
            logRecord.setRecordId(record.getRecordId())
                    .setContent(record.getContent())
                    .setOperatorSort(record.getOperatorSort())
                    .setOperator(record.getOperator())
                    .setLogRecordSort(LogRecordSort.parse(record.getLogRecordSort()))
                    .setIndexList(convert(recordIndexMockEntities))
                    .setVersion(record.getVersion())
                    .setCreateTime(record.getCreateTime())
                    .setModifyTime(record.getModifyTime());
            return logRecord;
        }).collect(Collectors.toList());
    }

    private List<LogRecordIndex> convert(List<LogRecordIndexMockEntity> recordIndexMockEntities){
        return recordIndexMockEntities.stream().map(index -> {
            LogRecordIndex logRecordIndex = new LogRecordIndex();
            logRecordIndex.setIndexId(index.getIndexId())
                    .setLogRecordId(index.getLogRecordId())
                    .setLogRecordIndexSort(LogRecordIndexSort.parse(index.getLogRecordIndexSort()))
                    .setIndexValue(index.getIndexValue())
                    .setVersion(index.getVersion())
                    .setCreateTime(index.getCreateTime())
                    .setModifyTime(index.getModifyTime());
            return logRecordIndex;
        }).collect(Collectors.toList());
    }
}
