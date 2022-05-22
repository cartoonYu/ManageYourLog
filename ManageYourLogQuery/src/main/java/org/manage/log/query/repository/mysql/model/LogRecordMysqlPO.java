package org.manage.log.query.repository.mysql.model;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/20 23:54
 */
public class LogRecordMysqlPO {

    private String recordId;

    private String content;

    private String operatorSort;

    private String operator;

    private String logRecordSort;

    private String indexIds;

    private Integer version;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public String getRecordId() {
        return recordId;
    }

    public LogRecordMysqlPO setRecordId(String recordId) {
        this.recordId = recordId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public LogRecordMysqlPO setContent(String content) {
        this.content = content;
        return this;
    }

    public String getOperatorSort() {
        return operatorSort;
    }

    public LogRecordMysqlPO setOperatorSort(String operatorSort) {
        this.operatorSort = operatorSort;
        return this;
    }

    public String getOperator() {
        return operator;
    }

    public LogRecordMysqlPO setOperator(String operator) {
        this.operator = operator;
        return this;
    }

    public String getLogRecordSort() {
        return logRecordSort;
    }

    public LogRecordMysqlPO setLogRecordSort(String logRecordSort) {
        this.logRecordSort = logRecordSort;
        return this;
    }

    public String getIndexIds() {
        return indexIds;
    }

    public LogRecordMysqlPO setIndexIds(String indexIds) {
        this.indexIds = indexIds;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public LogRecordMysqlPO setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LogRecordMysqlPO setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public LogRecordMysqlPO setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }
}
