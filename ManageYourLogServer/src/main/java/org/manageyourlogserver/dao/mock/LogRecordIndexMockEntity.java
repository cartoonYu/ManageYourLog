package org.manageyourlogserver.dao.mock;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/26 00:17
 */
public class LogRecordIndexMockEntity {

    private String indexId;

    private String logRecordId;

    private String logRecordIndexSort;

    private String indexValue;

    private Integer version;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public String getIndexId() {
        return indexId;
    }

    public LogRecordIndexMockEntity setIndexId(String indexId) {
        this.indexId = indexId;
        return this;
    }

    public String getLogRecordId() {
        return logRecordId;
    }

    public LogRecordIndexMockEntity setLogRecordId(String logRecordId) {
        this.logRecordId = logRecordId;
        return this;
    }

    public String getLogRecordIndexSort() {
        return logRecordIndexSort;
    }

    public LogRecordIndexMockEntity setLogRecordIndexSort(String logRecordIndexSort) {
        this.logRecordIndexSort = logRecordIndexSort;
        return this;
    }

    public String getIndexValue() {
        return indexValue;
    }

    public LogRecordIndexMockEntity setIndexValue(String indexValue) {
        this.indexValue = indexValue;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public LogRecordIndexMockEntity setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LogRecordIndexMockEntity setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public LogRecordIndexMockEntity setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }
}
