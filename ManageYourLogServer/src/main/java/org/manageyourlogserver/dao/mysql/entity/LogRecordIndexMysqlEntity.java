package org.manageyourlogserver.dao.mysql.entity;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/06 00:13
 */
public class LogRecordIndexMysqlEntity {

    private Long indexId;

    private Long logRecordId;

    private Long indexSortIndexId;

    private String indexValue;

    private Integer version;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public Long getIndexId() {
        return indexId;
    }

    public LogRecordIndexMysqlEntity setIndexId(Long indexId) {
        this.indexId = indexId;
        return this;
    }

    public Long getLogRecordId() {
        return logRecordId;
    }

    public LogRecordIndexMysqlEntity setLogRecordId(Long logRecordId) {
        this.logRecordId = logRecordId;
        return this;
    }

    public Long getIndexSortIndexId() {
        return indexSortIndexId;
    }

    public LogRecordIndexMysqlEntity setIndexSortIndexId(Long indexSortIndexId) {
        this.indexSortIndexId = indexSortIndexId;
        return this;
    }

    public String getIndexValue() {
        return indexValue;
    }

    public LogRecordIndexMysqlEntity setIndexValue(String indexValue) {
        this.indexValue = indexValue;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public LogRecordIndexMysqlEntity setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LogRecordIndexMysqlEntity setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public LogRecordIndexMysqlEntity setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }
}
