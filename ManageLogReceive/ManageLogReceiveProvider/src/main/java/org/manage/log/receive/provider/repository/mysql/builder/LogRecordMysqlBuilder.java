package org.manage.log.receive.provider.repository.mysql.builder;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.manage.log.common.model.log.LogRecord;
import org.manage.log.common.model.log.LogRecordIndex;
import org.manage.log.receive.provider.repository.mysql.LogRecordMysqlRepository;
import org.manage.log.receive.provider.repository.mysql.model.LogRecordIndexMysqlPO;
import org.manage.log.receive.provider.repository.mysql.model.LogRecordMysqlPO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * mysql converter, convert between mysql po and model
 * @author cartoon
 * @date 2021/11/25 20:34
 */
public class LogRecordMysqlBuilder {

    private static final LogRecordMysqlBuilder INSTANCE = new LogRecordMysqlBuilder();

    public static LogRecordMysqlBuilder getInstance(){
        return INSTANCE;
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
                ).toList()
        ).orElse(Collections.emptyList());
        return ImmutablePair.of(logRecordMysqlPO, indexMysqlPOS);
    }
}
