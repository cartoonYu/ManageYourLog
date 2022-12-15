package org.manage.log.common.model.config;

import org.manage.log.common.constants.LogRecordIndexSort;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/12/11 20:59
 */
public class LogIndexConfig {

    private String ruleId;

    private String ruleName;

    private LogRecordIndexSort logRecordIndexSort;

    private Long valueIndex;

    private String description;

    private Long version;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public String getRuleId() {
        return ruleId;
    }

    public LogIndexConfig setRuleId(String ruleId) {
        this.ruleId = ruleId;
        return this;
    }

    public String getRuleName() {
        return ruleName;
    }

    public LogIndexConfig setRuleName(String ruleName) {
        this.ruleName = ruleName;
        return this;
    }

    public LogRecordIndexSort getLogRecordIndexSort() {
        return logRecordIndexSort;
    }

    public LogIndexConfig setLogRecordIndexSort(LogRecordIndexSort logRecordIndexSort) {
        this.logRecordIndexSort = logRecordIndexSort;
        return this;
    }

    public Long getValueIndex() {
        return valueIndex;
    }

    public LogIndexConfig setValueIndex(Long valueIndex) {
        this.valueIndex = valueIndex;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public LogIndexConfig setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public LogIndexConfig setVersion(Long version) {
        this.version = version;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LogIndexConfig setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public LogIndexConfig setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }
}
