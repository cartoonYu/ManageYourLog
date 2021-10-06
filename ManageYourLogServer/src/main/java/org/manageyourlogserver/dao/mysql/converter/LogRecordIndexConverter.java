package org.manageyourlogserver.dao.mysql.converter;

import org.manageyourlogcommon.constants.LogRecordIndexSort;
import org.manageyourlogserver.dao.mysql.entity.LogRecordIndexMysqlEntity;
import org.manageyourlogserver.model.LogRecordIndex;


import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/06 10:33
 */
public class LogRecordIndexConverter {

    public static List<LogRecordIndex> convert(List<LogRecordIndexMysqlEntity> mysqlEntityList){
        return ofNullable(mysqlEntityList)
                .map(entityList -> entityList.stream()
                        .map(LogRecordIndexConverter::convert)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    public static LogRecordIndex convert(LogRecordIndexMysqlEntity mysqlEntity){
        return ofNullable(mysqlEntity).map(entity -> {
            LogRecordIndex res = new LogRecordIndex();
            res.setIndexId(entity.getIndexId())
                    .setLogRecordId(entity.getLogRecordId())
                    .setLogRecordIndexSort(LogRecordIndexSort.parse(mysqlEntity.getIndexSortIndexId()))
                    .setIndexValue(mysqlEntity.getIndexValue())
                    .setVersion(mysqlEntity.getVersion())
                    .setCreateTime(mysqlEntity.getCreateTime())
                    .setModifyTime(mysqlEntity.getModifyTime());
            return res;
        }).orElse(null);
    }

    public static List<LogRecordIndexMysqlEntity> convertToMysqlEntity(List<LogRecordIndex> logRecordIndices){
        return ofNullable(logRecordIndices)
                .map(indexList -> indexList.stream()
                        .map(LogRecordIndexConverter::convertToMysqlEntity)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    public static LogRecordIndexMysqlEntity convertToMysqlEntity(LogRecordIndex logRecordIndex){
        return ofNullable(logRecordIndex).map(index -> {
            LogRecordIndexMysqlEntity res = new LogRecordIndexMysqlEntity();
            res.setIndexId(index.getIndexId())
                    .setLogRecordId(index.getLogRecordId())
                    .setIndexSortIndexId(index.getLogRecordIndexSort().getSortId())
                    .setIndexValue(index.getIndexValue())
                    .setVersion(index.getVersion())
                    .setCreateTime(index.getCreateTime())
                    .setModifyTime(index.getModifyTime());
            return res;
        }).orElse(null);
    }
}
