package org.manage.log.receive.facade.dto.config.query;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/12/15 21:28
 */
public class LogIndexConfigDto {

    private String ruleName;

    private String logRecordIndexSort;

    private String valueIndexKey;

    private String description;

    private Long version;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public String getRuleName() {
        return ruleName;
    }

    public LogIndexConfigDto setRuleName(String ruleName) {
        this.ruleName = ruleName;
        return this;
    }

    public String getLogRecordIndexSort() {
        return logRecordIndexSort;
    }

    public LogIndexConfigDto setLogRecordIndexSort(String logRecordIndexSort) {
        this.logRecordIndexSort = logRecordIndexSort;
        return this;
    }

    public String getValueIndexKey() {
        return valueIndexKey;
    }

    public LogIndexConfigDto setValueIndexKey(String valueIndexKey) {
        this.valueIndexKey = valueIndexKey;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public LogIndexConfigDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public LogIndexConfigDto setVersion(Long version) {
        this.version = version;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LogIndexConfigDto setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public LogIndexConfigDto setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }
}
