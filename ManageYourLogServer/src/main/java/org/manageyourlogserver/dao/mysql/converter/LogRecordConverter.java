package org.manageyourlogserver.dao.mysql.converter;

import org.manageyourlogcommon.constants.LogRecordSort;
import org.manageyourlogserver.dao.mysql.entity.LogRecordMysqlEntity;
import org.manageyourlogserver.model.LogRecord;
import org.manageyourlogserver.model.LogRecordIndex;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/06 10:57
 */
public class LogRecordConverter {

    public static List<LogRecord> convert(List<LogRecordMysqlEntity> mysqlEntityList, List<LogRecordIndex> recordIndexList){
        return ofNullable(mysqlEntityList)
                .map(entityList -> entityList.stream().map(entity -> {
                    List<LogRecordIndex> recordIndices = ofNullable(recordIndexList)
                            .map(indexList -> indexList.stream().filter(index -> index.getLogRecordId().equals(entity.getRecordId()))
                                    .collect(Collectors.toList())).orElse(null);
                    return convert(entity, recordIndices);
                }).collect(Collectors.toList()))
                .orElse(null);
    }

    public static LogRecord convert(LogRecordMysqlEntity mysqlEntity, List<LogRecordIndex> indexList){
        return ofNullable(mysqlEntity)
                .map(entity -> {
                    LogRecord res = new LogRecord();
                    res.setRecordId(entity.getRecordId())
                            .setContent(entity.getContent())
                            .setOperatorSort(entity.getOperatorSort())
                            .setOperator(entity.getOperator())
                            .setLogRecordSort(LogRecordSort.parse(entity.getLogRecordSortId()))
                            .setIndexList(indexList)
                            .setVersion(entity.getVersion())
                            .setCreateTime(entity.getCreateTime())
                            .setModifyTime(entity.getModifyTime());
                    return res;
                })
                .orElse(null);
    }

    public static List<LogRecordMysqlEntity> convertToMysqlEntity(List<LogRecord> logRecords){
        return ofNullable(logRecords)
                .map(recordList -> recordList.stream()
                        .map(LogRecordConverter::convertToMysqlEntity)
                        .collect(Collectors.toList()))
                .orElse(null);
    }


    public static LogRecordMysqlEntity convertToMysqlEntity(LogRecord logRecord){
        return ofNullable(logRecord)
                .map(record -> {
                    LogRecordMysqlEntity res = new LogRecordMysqlEntity();
                    res.setRecordId(record.getRecordId())
                            .setContent(record.getContent())
                            .setOperatorSort(record.getOperatorSort())
                            .setOperator(record.getOperator())
                            .setLogRecordSortId(record.getLogRecordSort().getSortId())
                            .setIndexIds(record.getIndexList().stream().map(index -> Long.toString(index.getIndexId())).collect(Collectors.joining(",")))
                            .setVersion(record.getVersion())
                            .setCreateTime(record.getCreateTime())
                            .setModifyTime(record.getModifyTime());
                    return res;
                })
                .orElse(null);
    }
}
