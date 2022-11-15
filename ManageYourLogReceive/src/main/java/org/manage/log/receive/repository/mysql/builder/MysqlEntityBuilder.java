package org.manage.log.receive.repository.mysql.builder;

import com.google.common.collect.ImmutableList;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.manage.log.common.constants.LogRecordIndexSort;
import org.manage.log.common.constants.LogRecordSort;
import org.manage.log.common.constants.OperatorSort;
import org.manage.log.common.model.log.LogRecord;
import org.manage.log.common.model.log.LogRecordIndex;
import org.manage.log.receive.repository.LogRecordMysqlRepository;
import org.manage.log.receive.repository.mysql.model.LogRecordIndexMysqlPO;
import org.manage.log.receive.repository.mysql.model.LogRecordMysqlPO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * mysql converter, convert between mysql po and model
 * @author cartoon
 * @date 2021/11/25 20:34
 */
public class MysqlEntityBuilder {

    private static final MysqlEntityBuilder INSTANCE = new MysqlEntityBuilder();

    public static MysqlEntityBuilder getInstance(){
        return INSTANCE;
    }

    /**
     * convert po list to model list
     * @param logRecordMysqlPO log record po list
     * @param logRecordIndexMysqlPOList incoming log's index list
     * @return log record model list
     */
    public List<LogRecord> convertToModel(List<LogRecordMysqlPO> logRecordMysqlPO, List<LogRecordIndexMysqlPO> logRecordIndexMysqlPOList){
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
    public LogRecord convertToModel(LogRecordMysqlPO logRecordMysqlPO, List<LogRecordIndexMysqlPO> logRecordIndexMysqlPOList){
        return ofNullable(logRecordMysqlPO).map(po -> {
            LogRecord logRecord = new LogRecord();
            logRecord.setRecordId(po.getRecordId())
                    .setContent(po.getContent())
                    .setOperatorSort(OperatorSort.parse(po.getOperatorSort()))
                    .setOperator(po.getOperator())
                    .setLogRecordSort(LogRecordSort.parse(po.getLogRecordSort()))
                    .setVersion(po.getVersion())
                    .setCreateTime(po.getCreateTime())
                    .setModifyTime(po.getModifyTime())
                    .addIndexList(logRecordIndexMysqlPOList.stream().map(indexPo -> {
                        LogRecordIndex logRecordIndex = new LogRecordIndex();
                        logRecordIndex.setIndexId(indexPo.getIndexId())
                                .setLogRecordId(indexPo.getLogRecordId())
                                .setLogRecordIndexSort(LogRecordIndexSort.parse(indexPo.getSort()))
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
    public List<ImmutablePair<LogRecordMysqlPO, List<LogRecordIndexMysqlPO>>> convertToPo(List<LogRecord> logRecords){
        return logRecords.stream().map(this::convertToPo).collect(Collectors.toList());
    }

    /**
     * convert model to po
     * @param logRecord model
     * @return record and its index list po
     */
    public ImmutablePair<LogRecordMysqlPO, List<LogRecordIndexMysqlPO>> convertToPo(LogRecord logRecord){
        //convert log model to po
        LogRecordMysqlPO logRecordMysqlPO = new LogRecordMysqlPO();
        logRecordMysqlPO.setRecordId(logRecord.getRecordId())
                .setContent(logRecord.getContent())
                .setOperatorSort(logRecord.getOperatorSort().getSortDescription())
                .setOperator(logRecord.getOperator())
                .setLogRecordSort(logRecord.getLogRecordSort().getSortDescription())
                .setVersion(logRecord.getVersion())
                .setCreateTime(logRecord.getCreateTime())
                .setModifyTime(logRecord.getModifyTime());
        if(CollectionUtils.isNotEmpty(logRecord.getIndexList())){
            logRecordMysqlPO.setIndexIds(logRecord.getIndexList().stream().map(LogRecordIndex::getIndexId).collect(Collectors.joining(LogRecordMysqlRepository.INDEX_SPLIT_CHARACTER)));
        }
        //convert index model to po
        List<LogRecordIndexMysqlPO> indexMysqlPOS = ofNullable(logRecord.getIndexList()).map(
                indexList -> indexList.stream().map(
                        index -> {
                            LogRecordIndexMysqlPO indexMysqlPO = new LogRecordIndexMysqlPO();
                            indexMysqlPO.setIndexId(index.getIndexId())
                                    .setLogRecordId(index.getLogRecordId())
                                    .setSort(index.getLogRecordIndexSort().getSortDescription())
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
