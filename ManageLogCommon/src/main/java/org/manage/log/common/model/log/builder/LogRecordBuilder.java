package org.manage.log.common.model.log.builder;

import org.manage.log.common.constants.LogRecordIndexSort;
import org.manage.log.common.constants.LogRecordSort;
import org.manage.log.common.constants.OperatorSort;
import org.manage.log.common.model.log.LogRecord;
import org.manage.log.common.model.log.LogRecordIndex;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/12/20 23:08
 */
public class LogRecordBuilder {

    private String recordId;

    private String content;

    private OperatorSort operatorSort;

    private String operator;

    private LogRecordSort logRecordSort;

    private Map<String, LogRecordIndexSort> indexValueToIndexSortMap = new HashMap<>();

    private Integer version = 1;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime = LocalDateTime.now();

    public LogRecordBuilder setContent(String content) {
        if(Objects.isNull(content)){
            return this;
        }
        this.content = content;
        return this;
    }

    public LogRecordBuilder setOperatorSort(OperatorSort operatorSort) {
        if(Objects.isNull(operatorSort)){
            return this;
        }
        this.operatorSort = operatorSort;
        return this;
    }

    public LogRecordBuilder setOperator(String operator) {
        if(Objects.isNull(operator)){
            return this;
        }
        this.operator = operator;
        return this;
    }

    public LogRecordBuilder setLogRecordSort(LogRecordSort logRecordSort) {
        if(Objects.isNull(logRecordSort)){
            return this;
        }
        this.logRecordSort = logRecordSort;
        return this;
    }

    public LogRecordBuilder setIndexValueToIndexSortMap(Map<String, LogRecordIndexSort> indexValueToIndexSortMap) {
        if(Objects.isNull(indexValueToIndexSortMap)){
            return this;
        }
        this.indexValueToIndexSortMap.putAll(indexValueToIndexSortMap);
        return this;
    }

    public LogRecordBuilder setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public static LogRecordBuilder getInstance(LogRecordFactory logRecordFactory, LogIndexFactory indexFactory){
        return new LogRecordBuilder(logRecordFactory, indexFactory);
    }

    private LogRecordFactory logRecordFactory;

    private LogIndexFactory logIndexFactory;

    private LogRecordBuilder(LogRecordFactory logRecordFactory, LogIndexFactory indexFactory) {
        this.logRecordFactory = logRecordFactory;
        this.logIndexFactory = indexFactory;
    }

    public LogRecord build(){
        recordId = logRecordFactory.generateRecordId();
        List<LogRecordIndex> indexList = logIndexFactory.build(recordId, indexValueToIndexSortMap);
        LogRecord logRecord = new LogRecord();
        logRecord.setRecordId(recordId)
                .setContent(content)
                .setOperatorSort(operatorSort)
                .setOperator(operator)
                .setLogRecordSort(logRecordSort)
                .addIndexList(indexList)
                .setVersion(version)
                .setCreateTime(createTime)
                .setModifyTime(modifyTime);
        logRecordFactory.check(logRecord);
        return logRecord;
    }
}
