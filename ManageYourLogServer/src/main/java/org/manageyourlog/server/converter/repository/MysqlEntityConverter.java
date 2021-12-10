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
import org.manageyourlog.server.repository.LogRecordMysqlRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * mysql converter, convert between mysql po and model
 * @author cartoon
 * @date 2021/11/25 20:34
 */
public class MysqlEntityConverter {

    /**
     * convert po list to model list
     * @param logRecordMysqlPO log record po list
     * @param logRecordIndexMysqlPOList incoming log's index list
     * @return log record model list
     */
    public static List<LogRecord> convertToModel(List<LogRecordMysqlPO> logRecordMysqlPO, List<LogRecordIndexMysqlPO> logRecordIndexMysqlPOList){
        Map<String, List<LogRecordIndexMysqlPO>> recordIdToIndexListMap = logRecordIndexMysqlPOList.stream().collect(Collectors.groupingBy(LogRecordIndexMysqlPO::getLogRecordId));
        return ofNullable(logRecordMysqlPO)
                .map(logRecordList ->
                        logRecordList.stream()
                                .map(logRecord -> convertToModel(logRecord, recordIdToIndexListMap.get(logRecord.getRecordId())))
                                .collect(Collectors.toList()))
                .orElse(ImmutableList.of());
    }

    /**
     * convert po to model
     * @param logRecordMysqlPO log record po list
     * @param logRecordIndexMysqlPOList incoming log's index list
     * @return log record model
     */
    public static LogRecord convertToModel(LogRecordMysqlPO logRecordMysqlPO, List<LogRecordIndexMysqlPO> logRecordIndexMysqlPOList){
        return ofNullable(logRecordMysqlPO).map(po -> {
            LogRecord logRecord = new LogRecord();
            logRecord.setRecordId(po.getRecordId())
                    .setContent(po.getContent())
                    .setOperatorSort(po.getOperatorSort())
                    .setOperator(po.getOperator())
                    .setLogRecordSort(LogRecordSort.parse(po.getLogRecordSort()))
                    .setVersion(po.getVersion())
                    .setCreateTime(po.getCreateTime())
                    .setModifyTime(po.getModifyTime())
                    .setIndexList(logRecordIndexMysqlPOList.stream().map(indexPo -> {
                        LogRecordIndex logRecordIndex = new LogRecordIndex();
                        logRecordIndex.setIndexId(indexPo.getIndexId())
                                .setLogRecordId(indexPo.getLogRecordId())
                                .setLogRecordIndexSort(LogRecordIndexSort.parse(indexPo.getSortId()))
                                .setIndexValue(indexPo.getIndexValue())
                                .setVersion(indexPo.getVersion())
                                .setCreateTime(indexPo.getCreateTime())
                                .setModifyTime(indexPo.getModifyTime());
                        return logRecordIndex;
                    }).collect(Collectors.toList()));
            return logRecord;
        }).orElse(null);
    }

    /**
     * convert model list to po list
     * @param logRecords model list
     * @return record and its index list
     */
    public static List<ImmutablePair<LogRecordMysqlPO, List<LogRecordIndexMysqlPO>>> convertToPo(List<LogRecord> logRecords){
        return logRecords.stream().map(MysqlEntityConverter::convertToPo).collect(Collectors.toList());
    }

    /**
     * convert model to po
     * @param logRecord model
     * @return record and its index list po
     */
    public static ImmutablePair<LogRecordMysqlPO, List<LogRecordIndexMysqlPO>> convertToPo(LogRecord logRecord){
        //convert log model to po
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
            logRecordMysqlPO.setIndexIds(logRecord.getIndexList().stream().map(LogRecordIndex::getIndexId).collect(Collectors.joining(LogRecordMysqlRepository.indexSplitCharacter)));
        }
        //convert index model to po
        List<LogRecordIndexMysqlPO> indexMysqlPOS = ofNullable(logRecord.getIndexList()).map(
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
        ).orElse(ImmutableList.of());
        return ImmutablePair.of(logRecordMysqlPO, indexMysqlPOS);
    }
}
