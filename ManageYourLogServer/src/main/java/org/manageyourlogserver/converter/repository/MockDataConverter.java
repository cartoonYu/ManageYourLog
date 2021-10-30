package org.manageyourlogserver.converter.repository;

import org.manageyourlogcommon.constants.LogRecordIndexSort;
import org.manageyourlogcommon.constants.LogRecordSort;
import org.manageyourlogserver.dao.mock.LogRecordIndexMockEntity;
import org.manageyourlogserver.dao.mock.LogRecordMockEntity;
import org.manageyourlogserver.model.LogRecord;
import org.manageyourlogserver.model.LogRecordIndex;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/26 00:29
 */
public class MockDataConverter {

    public static List<LogRecord> convert(List<LogRecordMockEntity> recordMockEntities, List<LogRecordIndexMockEntity> recordIndexMockEntities){
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

    private static List<LogRecordIndex> convert(List<LogRecordIndexMockEntity> recordIndexMockEntities){
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
