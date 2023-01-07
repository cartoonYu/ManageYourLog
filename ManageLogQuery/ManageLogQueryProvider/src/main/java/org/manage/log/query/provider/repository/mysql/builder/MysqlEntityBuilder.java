package org.manage.log.query.provider.repository.mysql.builder;

import com.google.common.collect.ImmutableList;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.manage.log.common.model.log.constants.LogRecordIndexSort;
import org.manage.log.common.model.log.constants.LogRecordSort;
import org.manage.log.common.model.log.constants.OperatorSort;
import org.manage.log.common.model.log.LogRecord;
import org.manage.log.common.model.log.LogRecordIndex;
import org.manage.log.common.model.log.builder.LogRecordFactory;
import org.manage.log.query.provider.repository.LogRecordMysqlRepository;
import org.manage.log.query.provider.repository.mysql.model.LogRecordIndexMysqlPO;
import org.manage.log.query.provider.repository.mysql.model.LogRecordMysqlPO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * mysql converter, convert between mysql po and model
 * @author cartoon
 * @date 2021/11/25 20:34
 */
@Component
public class MysqlEntityBuilder {

    private final LogRecordFactory logRecordFactory;

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
            List<LogRecordIndex> indexList = logRecordIndexMysqlPOList.stream().map(indexPo ->
                    new LogRecordIndex(
                        indexPo.getIndexId(),
                        indexPo.getLogRecordId(),
                        LogRecordIndexSort.parse(indexPo.getSort()),
                        indexPo.getIndexValue(),
                        indexPo.getVersion(),
                        indexPo.getCreateTime(),
                        indexPo.getModifyTime()
            )).toList();

            LogRecord logRecord = new LogRecord(
                    po.getRecordId(),
                    po.getContent(),
                    OperatorSort.parse(po.getOperatorSort()),
                    po.getOperator(),
                    LogRecordSort.parse(po.getLogRecordSort()),
                    indexList,
                    po.getVersion(),
                    po.getCreateTime(),
                    po.getModifyTime()
            );
            logRecordFactory.check(logRecord);
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
        logRecordMysqlPO.setRecordId(logRecord.recordId())
                .setContent(logRecord.content())
                .setOperatorSort(logRecord.operatorSort().getSortDescription())
                .setOperator(logRecord.operator())
                .setLogRecordSort(logRecord.logRecordSort().getSortDescription())
                .setVersion(logRecord.version())
                .setCreateTime(logRecord.createTime())
                .setModifyTime(logRecord.modifyTime());
        if(CollectionUtils.isNotEmpty(logRecord.indexList())){
            logRecordMysqlPO.setIndexIds(logRecord.indexList().stream().map(LogRecordIndex::indexId).collect(Collectors.joining(LogRecordMysqlRepository.INDEX_SPLIT_CHARACTER)));
        }
        //convert index model to po
        List<LogRecordIndexMysqlPO> indexMysqlPOS = ofNullable(logRecord.indexList()).map(
                indexList -> indexList.stream().map(
                        index -> {
                            LogRecordIndexMysqlPO indexMysqlPO = new LogRecordIndexMysqlPO();
                            indexMysqlPO.setIndexId(index.indexId())
                                    .setLogRecordId(index.logRecordId())
                                    .setSort(index.logRecordIndexSort().getSortDescription())
                                    .setIndexValue(index.indexValue())
                                    .setVersion(index.version())
                                    .setCreateTime(index.createTime())
                                    .setModifyTime(index.modifyTime());
                            return indexMysqlPO;
                        }
                ).collect(Collectors.toList())
        ).orElse(ImmutableList.of());
        return ImmutablePair.of(logRecordMysqlPO, indexMysqlPOS);
    }

    public MysqlEntityBuilder(LogRecordFactory logRecordFactory) {
        this.logRecordFactory = logRecordFactory;
    }
}
