package org.manageyourlog.server.dao.mysql.model;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/21 00:04
 */
public class LogRecordIndexMysqlPO {

    private String indexId;

    private String logRecordId;

    private String sort;

    private String indexValue;

    private Integer version;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public String getIndexId() {
        return indexId;
    }

    public LogRecordIndexMysqlPO setIndexId(String indexId) {
        this.indexId = indexId;
        return this;
    }

    public String getLogRecordId() {
        return logRecordId;
    }

    public LogRecordIndexMysqlPO setLogRecordId(String logRecordId) {
        this.logRecordId = logRecordId;
        return this;
    }

    public String getSort() {
        return sort;
    }

    public LogRecordIndexMysqlPO setSort(String sort) {
        this.sort = sort;
        return this;
    }

    public String getIndexValue() {
        return indexValue;
    }

    public LogRecordIndexMysqlPO setIndexValue(String indexValue) {
        this.indexValue = indexValue;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public LogRecordIndexMysqlPO setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LogRecordIndexMysqlPO setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public LogRecordIndexMysqlPO setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }
}
