package org.manageyourlogserver.model;

import org.manageyourlogcommon.constants.LogRecordIndexSort;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/05 23:56
 */
public class LogRecordIndex {

    private Long indexId;

    private Long logRecordId;

    private LogRecordIndexSort logRecordIndexSort;

    private String indexValue;

    private Integer version;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public Long getIndexId() {
        return indexId;
    }

    public LogRecordIndex setIndexId(Long indexId) {
        this.indexId = indexId;
        return this;
    }

    public Long getLogRecordId() {
        return logRecordId;
    }

    public LogRecordIndex setLogRecordId(Long logRecordId) {
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
