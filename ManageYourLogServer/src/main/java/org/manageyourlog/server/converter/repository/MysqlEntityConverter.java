package org.manageyourlog.server.converter.repository;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.manageyourlog.common.constants.LogRecordIndexSort;
import org.manageyourlog.common.constants.LogRecordSort;
import org.manageyourlog.common.util.CollectionUtil;
import org.manageyourlog.server.dao.mysql.LogRecordIndexMysqlPO;
import org.manageyourlog.server.dao.mysql.LogRecordMysqlPO;
import org.manageyourlog.server.model.LogRecord;
import org.manageyourlog.server.model.LogRecordIndex;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * @author cartoon
 * @date 2021/11/25 20:34
 */
public class MysqlEntityConverter {

    public static List<LogRecord> convert(List<LogRecordMysqlPO> logRecordMysqlPO, List<LogRecordIndexMysqlPO> logRecordIndexMysqlPOList){
        //TODO
        return ImmutableList.of();
    }

    public static LogRecord convert(LogRecordMysqlPO logRecordMysqlPO, List<LogRecordIndexMysqlPO> logRecordIndexMysqlPOList){
        return ofNullable(logRecordMysqlPO).map(po -> {
            LogRecord logRecord = new LogRecord();
            logRecord.setRecordId(po.getRecordId())
                    .setContent(po.getContent())
                    .setOperatorSort(po.getOperatorSort())
                    .setOperator(po.getOperator())
                    .setLogRecordSort(LogRecordSort.parse(po.getLogRecordSort()))
                    .setIndexList(convert(logRecordIndexMysqlPOList))
                    .setVersion(po.getVersion())
                    .setCreateTime(po.getCreateTime())
                    .setModifyTime(po.getModifyTime());
            return logRecord;
        }).orElse(null);
    }

    private static List<LogRecordIndex> convert(List<LogRecordIndexMysqlPO> logRecordIndexMysqlPOList){
        return ofNullable(logRecordIndexMysqlPOList)
                .map(list -> list.stream().map(MysqlEntityConverter::convert).collect(Collectors.toList()))
                .orElse(null);
    }

    private static LogRecordIndex convert(LogRecordIndexMysqlPO logRecordIndexMysqlPO){
        return ofNullable(logRecordIndexMysqlPO).map(po -> {
            LogRecordIndex logRecordIndex = new LogRecordIndex();
            logRecordIndex.setIndexId(po.getIndexId())
                    .setLogRecordId(po.getLogRecordId())
                    .setLogRecordIndexSort(LogRecordIndexSort.parse(po.getSortId()))
                    .setIndexValue(po.getIndexValue())
                    .setVersion(po.getVersion())
                    .setCreateTime(po.getCreateTime())
                    .setModifyTime(po.getModifyTime());
            return logRecordIndex;
        }).orElse(null);
    }

    public static ImmutablePair<LogRecordMysqlPO, List<LogRecordIndexMysqlPO>> convertToPo(LogRecord logRecord){
        LogRecordMysqlPO logRecordMysqlPO = convertToLogRecordPo(logRecord);
        List<LogRecordIndexMysqlPO> indexMysqlPOS = convertToIndexPo(logRecord.getIndexList());
        return ImmutablePair.of(logRecordMysqlPO, indexMysqlPOS);
    }

    public static List<ImmutablePair<LogRecordMysqlPO, List<LogRecordIndexMysqlPO>>> convertToPo(List<LogRecord> logRecords){
        return logRecords.stream().map(MysqlEntityConverter::convertToPo).collect(Collectors.toList());
    }



    private static LogRecordMysqlPO convertToLogRecordPo(LogRecord logRecord){
        LogRecordMysqlPO logRecordMysqlPO = new LogRecordMysqlPO();
        logRecordMysqlPO.setRecordId(logRecord.getRecordId())
                .setContent(logRecord.getContent())
                .setOperatorSort(logRecord.getOperatorSort())
                .setOperator(logRecord.getOperator())
                .setLogRecordSort(logRecord.getLogRecordSort().getSortId())
                .setVersion(logRecord.getVersion())
                .setCreateTime(logRecord.getCreateTime())
                .setModifyTime(logRecord.getModifyTime());
        if(CollectionUtil.judgeIsNotEmpty(logRecord.getIndexList())){
            logRecordMysqlPO.setIndexIds(logRecord.getIndexList().stream().map(LogRecordIndex::getIndexId).collect(Collectors.joining(",")));
        }
        return logRecordMysqlPO;
    }

    private static List<LogRecordIndexMysqlPO> convertToIndexPo(List<LogRecordIndex> logRecordIndices){
        return ofNullable(logRecordIndices).map(
              indexList -> indexList.stream().map(
                      index -> {
                          LogRecordIndexMysqlPO indexMysqlPO = new LogRecordIndexMysqlPO();
                          indexMysqlPO.setIndexId(index.getIndexId())
                                  .setLogRecordId(index.getLogRecordId())
                                  .setSortId(index.getLogRecordIndexSort().getSortId())
                                  .setIndexValue(index.getIndexValue())
                                  .setVersion(index.getVersion())
                                  .setCreateTime(index.getCreateTime())
                                  .setModifyTime(index.getModifyTime());
                          return indexMysqlPO;
                      }
              ).collect(Collectors.toList())
        ).orElse(null);
    }
}
