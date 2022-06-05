package org.manage.log.config.repository.mysql.model;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @date 2022/6/4 17:58
 */
public class LogConfigMysqlPO {

    private String ruleId;

    private String ruleName;

    private String logRecordSort;

    private String operatorSort;

    private String indexSort;

    private String description;

    private Long version;

    private LocalDateTime createTime;

    private LocalDateTime modifyName;

    public String getRuleId() {
        return ruleId;
    }

    public LogConfigMysqlPO setRuleId(String ruleId) {
        this.ruleId = ruleId;
        return this;
    }

    public String getRuleName() {
        return ruleName;
    }

    public LogConfigMysqlPO setRuleName(String ruleName) {
        this.ruleName = ruleName;
        return this;
    }

    public String getLogRecordSort() {
        return logRecordSort;
    }

    public LogConfigMysqlPO setLogRecordSort(String logRecordSort) {
        this.logRecordSort = logRecordSort;
        return this;
    }

    public String getOperatorSort() {
        return operatorSort;
    }

    public LogConfigMysqlPO setOperatorSort(String operatorSort) {
        this.operatorSort = operatorSort;
        return this;
    }

    public String getIndexSort() {
        return indexSort;
    }

    public LogConfigMysqlPO setIndexSort(String indexSort) {
        this.indexSort = indexSort;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public LogConfigMysqlPO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public LogConfigMysqlPO setVersion(Long version) {
        this.version = version;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LogConfigMysqlPO setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getModifyName() {
        return modifyName;
    }

    public LogConfigMysqlPO setModifyName(LocalDateTime modifyName) {
        this.modifyName = modifyName;
        return this;
    }
}
