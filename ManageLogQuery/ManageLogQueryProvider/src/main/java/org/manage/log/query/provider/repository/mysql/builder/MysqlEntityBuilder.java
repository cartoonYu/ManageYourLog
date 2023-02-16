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
        Map<String, List<LogRecordIndexMysqlPO>> recordIdToIndexListMap = logRecordIndexMysqlPOList.stream().collect(Collectors.groupingBy(LogRecordIndexMysqlPO::logRecordId));
        return ofNullable(logRecordMysqlPO)
                .map(logRecordList ->
                        logRecordList.stream()
                                .map(logRecord -> convertToModel(logRecord, recordIdToIndexListMap.get(logRecord.recordId())))
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
                        indexPo.indexId(),
                        indexPo.logRecordId(),
                        LogRecordIndexSort.parse(indexPo.sort()),
                        indexPo.indexValue(),
                        indexPo.version(),
                        indexPo.createTime(),
                        indexPo.modifyTime()
            )).toList();

            LogRecord logRecord = new LogRecord(
                    po.recordId(),
                    po.content(),
                    OperatorSort.parse(po.operatorSort()),
                    po.operator(),
                    LogRecordSort.parse(po.logRecordSort()),
                    indexList,
                    po.version(),
                    po.createTime(),
                    po.modifyTime()
            );
            logRecordFactory.check(logRecord);
            return logRecord;
        }).orElse(null);
    }

    public MysqlEntityBuilder(LogRecordFactory logRecordFactory) {
        this.logRecordFactory = logRecordFactory;
    }
}
