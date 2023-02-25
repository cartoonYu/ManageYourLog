package org.manage.log.common.model.log.builder;

import org.manage.log.common.model.log.constants.LogRecordIndexSort;
import org.manage.log.common.model.log.constants.LogRecordSort;
import org.manage.log.common.model.log.constants.OperatorSort;
import org.manage.log.common.model.log.LogRecord;
import org.manage.log.common.model.log.LogRecordIndex;

import java.time.LocalDateTime;
import java.util.*;

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

    private List<LogRecordIndex> indexList = new ArrayList<>();

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

    public LogRecordBuilder setIndexList(List<LogRecordIndex> indexList) {
        if(Objects.isNull(indexList) || indexList.isEmpty()){
            return this;
        }
        this.indexList.addAll(indexList);
        return this;
    }

    public LogRecordBuilder setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public static LogRecordBuilder getInstance(LogRecordFactory logRecordFactory){
        return new LogRecordBuilder(logRecordFactory);
    }

    private LogRecordFactory logRecordFactory;

    private LogRecordBuilder(LogRecordFactory logRecordFactory) {
        this.logRecordFactory = logRecordFactory;
    }

    public LogRecord build(){
        recordId = logRecordFactory.generateRecordId();
        LogRecord logRecord = new LogRecord(
                recordId,
                content,
                operatorSort,
                operator,
                logRecordSort,
                indexList,
                version,
                createTime,
                modifyTime
        );
        logRecordFactory.check(logRecord);
        return logRecord;
    }
}
