package org.manage.log.config.facade.dto;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @since 2022/11/15 21:30
 */
public class LogConfigDto {

    private String ruleName;

    private String logRecordSort;

    private String operatorSort;

    private String indexSort;

    private String description;

    private Long version;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public String getRuleName() {
        return ruleName;
    }

    public LogConfigDto setRuleName(String ruleName) {
        this.ruleName = ruleName;
        return this;
    }

    public String getLogRecordSort() {
        return logRecordSort;
    }

    public LogConfigDto setLogRecordSort(String logRecordSort) {
        this.logRecordSort = logRecordSort;
        return this;
    }

    public String getOperatorSort() {
        return operatorSort;
    }

    public LogConfigDto setOperatorSort(String operatorSort) {
        this.operatorSort = operatorSort;
        return this;
    }

    public String getIndexSort() {
        return indexSort;
    }

    public LogConfigDto setIndexSort(String indexSort) {
        this.indexSort = indexSort;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public LogConfigDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public LogConfigDto setVersion(Long version) {
        this.version = version;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LogConfigDto setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public LogConfigDto setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }
}
