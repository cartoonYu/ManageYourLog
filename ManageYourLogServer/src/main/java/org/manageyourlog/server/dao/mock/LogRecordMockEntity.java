package org.manageyourlog.server.dao.mock;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/26 00:08
 */
public class LogRecordMockEntity {

    private String recordId;

    private String content;

    private String operatorSort;

    private String operator;

    private String logRecordSort;

    private List<String> indexList;

    private Integer version;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public String getRecordId() {
        return recordId;
    }

    public LogRecordMockEntity setRecordId(String recordId) {
        this.recordId = recordId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public LogRecordMockEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public String getOperatorSort() {
        return operatorSort;
    }

    public LogRecordMockEntity setOperatorSort(String operatorSort) {
        this.operatorSort = operatorSort;
        return this;
    }

    public String getOperator() {
        return operator;
    }

    public LogRecordMockEntity setOperator(String operator) {
        this.operator = operator;
        return this;
    }

    public String getLogRecordSort() {
        return logRecordSort;
    }

    public LogRecordMockEntity setLogRecordSort(String logRecordSort) {
        this.logRecordSort = logRecordSort;
        return this;
    }

    public List<String> getIndexList() {
        return indexList;
    }

    public LogRecordMockEntity setIndexList(List<String> indexList) {
        this.indexList = indexList;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public LogRecordMockEntity setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LogRecordMockEntity setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public LogRecordMockEntity setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }
}
