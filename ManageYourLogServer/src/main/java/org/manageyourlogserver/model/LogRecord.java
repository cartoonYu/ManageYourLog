package org.manageyourlogserver.model;


import org.manageyourlogcommon.constants.LogRecordSort;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/05 23:48
 */
public class LogRecord {

    private String recordId;

    private String content;

    private String operatorSort;

    private String operator;

    private LogRecordSort logRecordSort;

    private List<LogRecordIndex> indexList;

    private Integer version;

    private LocalDateTime createTime;

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

    public String getOperatorSort() {
        return operatorSort;
    }

    public LogRecord setOperatorSort(String operatorSort) {
        this.operatorSort = operatorSort;
        return this;
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

    public LogRecord setIndexList(List<LogRecordIndex> indexList) {
        this.indexList = indexList;
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
