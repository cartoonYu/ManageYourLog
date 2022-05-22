package org.manage.log.common.model;

import org.manage.log.common.constants.LogRecordIndexSort;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/05 23:56
 */
public class LogRecordIndex {

    private String indexId;

    private String logRecordId;

    private LogRecordIndexSort logRecordIndexSort;

    private String indexValue;

    private Integer version;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public String getIndexId() {
        return indexId;
    }

    public LogRecordIndex setIndexId(String indexId) {
        this.indexId = indexId;
        return this;
    }

    public String getLogRecordId() {
        return logRecordId;
    }

    public LogRecordIndex setLogRecordId(String logRecordId) {
        this.logRecordId = logRecordId;
        return this;
    }

    public LogRecordIndexSort getLogRecordIndexSort() {
        return logRecordIndexSort;
    }

    public LogRecordIndex setLogRecordIndexSort(LogRecordIndexSort logRecordIndexSort) {
        this.logRecordIndexSort = logRecordIndexSort;
        return this;
    }

    public String getIndexValue() {
        return indexValue;
    }

    public LogRecordIndex setIndexValue(String indexValue) {
        this.indexValue = indexValue;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public LogRecordIndex setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LogRecordIndex setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public LogRecordIndex setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }
}
