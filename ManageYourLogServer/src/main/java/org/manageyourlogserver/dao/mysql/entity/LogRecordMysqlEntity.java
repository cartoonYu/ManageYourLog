package org.manageyourlogserver.dao.mysql.entity;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/06 00:12
 */
public class LogRecordMysqlEntity {

    private Long recordId;

    private String content;

    private String operatorSort;

    private String operator;

    private Long logRecordSortId;

    private String indexIds;

    private Integer version;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public Long getRecordId() {
        return recordId;
    }

    public LogRecordMysqlEntity setRecordId(Long recordId) {
        this.recordId = recordId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public LogRecordMysqlEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public String getOperatorSort() {
        return operatorSort;
    }

    public LogRecordMysqlEntity setOperatorSort(String operatorSort) {
        this.operatorSort = operatorSort;
        return this;
    }

    public String getOperator() {
        return operator;
    }

    public LogRecordMysqlEntity setOperator(String operator) {
        this.operator = operator;
        return this;
    }

    public Long getLogRecordSortId() {
        return logRecordSortId;
    }

    public LogRecordMysqlEntity setLogRecordSortId(Long logRecordSortId) {
        this.logRecordSortId = logRecordSortId;
        return this;
    }

    public String getIndexIds() {
        return indexIds;
    }

    public LogRecordMysqlEntity setIndexIds(String indexIds) {
        this.indexIds = indexIds;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public LogRecordMysqlEntity setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LogRecordMysqlEntity setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public LogRecordMysqlEntity setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }
}
