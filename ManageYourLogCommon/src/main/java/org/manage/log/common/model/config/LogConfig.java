package org.manage.log.common.model.config;

import org.manage.log.common.constants.LogRecordIndexSort;
import org.manage.log.common.constants.LogRecordSort;
import org.manage.log.common.constants.OperatorSort;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @date 2022/6/4 16:46
 */
public class LogConfig {

    private String ruleId;

    private String ruleName;

    private LogRecordSort logRecordSort;

    private OperatorSort operatorSort;

    private LogRecordIndexSort indexSort;

    private String description;

    private Long version;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public String getRuleId() {
        return ruleId;
    }

    public LogConfig setRuleId(String ruleId) {
        this.ruleId = ruleId;
        return this;
    }

    public String getRuleName() {
        return ruleName;
    }

    public LogConfig setRuleName(String ruleName) {
        this.ruleName = ruleName;
        return this;
    }

    public LogRecordSort getLogRecordSort() {
        return logRecordSort;
    }

    public LogConfig setLogRecordSort(LogRecordSort logRecordSort) {
        this.logRecordSort = logRecordSort;
        return this;
    }

    public OperatorSort getOperatorSort() {
        return operatorSort;
    }

    public LogConfig setOperatorSort(OperatorSort operatorSort) {
        this.operatorSort = operatorSort;
        return this;
    }

    public LogRecordIndexSort getIndexSort() {
        return indexSort;
    }

    public LogConfig setIndexSort(LogRecordIndexSort indexSort) {
        this.indexSort = indexSort;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public LogConfig setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public LogConfig setVersion(Long version) {
        this.version = version;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LogConfig setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }
}
