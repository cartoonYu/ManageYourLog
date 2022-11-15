package org.manage.log.common.model.log;


import org.manage.log.common.constants.LogRecordSort;
import org.manage.log.common.constants.OperatorSort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/05 23:48
 */
public class LogRecord {

    /**
     * record id, identify record single
     */
    private String recordId;

    /**
     * record source content
     */
    private String content;

    /**
     * operate record sort
     */
    private OperatorSort operatorSort;

    /**
     * operate current record handles
     */
    private String operator;

    /**
     * record sort
     */
    private LogRecordSort logRecordSort;

    /**
     * record index list
     */
    private List<LogRecordIndex> indexList = new ArrayList<>();

    private Integer version;

    /**
     * record create time
     */
    private LocalDateTime createTime;

    /**
     * record last modify time
     */
    private LocalDateTime modifyTime;

    public String getRecordId() {
        return recordId;
    }

    public LogRecord setRecordId(String recordId) {
        this.recordId = recordId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public LogRecord setContent(String content) {
        this.content = content;
        return this;
    }

    public LogRecord setOperatorSort(OperatorSort operatorSort) {
        this.operatorSort = operatorSort;
        return this;
    }

    public OperatorSort getOperatorSort() {
        return operatorSort;
    }

    public String getOperator() {
        return operator;
    }

    public LogRecord setOperator(String operator) {
        this.operator = operator;
        return this;
    }

    public LogRecordSort getLogRecordSort() {
        return logRecordSort;
    }

    public LogRecord setLogRecordSort(LogRecordSort logRecordSort) {
        this.logRecordSort = logRecordSort;
        return this;
    }

    public List<LogRecordIndex> getIndexList() {
        return indexList;
    }

    public LogRecord addIndex(LogRecordIndex index){
        if(Objects.isNull(index)){
            return this;
        }
        this.indexList.add(index);
        return this;
    }

    public LogRecord addIndexList(List<LogRecordIndex> indexList){
        if(Objects.isNull(indexList)){
            return this;
        }
        this.indexList.addAll(indexList);
        return this;
    }


    public Integer getVersion() {
        return version;
    }

    public LogRecord setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LogRecord setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public LogRecord setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }
}
