package org.manage.log.receive.provider.repository.mysql.model.config;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/12/11 10:59
 */
public class LogIndexConfigMysqlPO {

    private String ruleId;

    private String ruleName;

    private String logConfigId;

    private String sort;

    private Long valueIndex;

    private String valueIndexKey;

    private String description;

    private Long version;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public String getRuleId() {
        return ruleId;
    }

    public LogIndexConfigMysqlPO setRuleId(String ruleId) {
        this.ruleId = ruleId;
        return this;
    }

    public String getRuleName() {
        return ruleName;
    }

    public LogIndexConfigMysqlPO setRuleName(String ruleName) {
        this.ruleName = ruleName;
        return this;
    }

    public LogIndexConfigMysqlPO setLogConfigId(String logConfigId) {
        this.logConfigId = logConfigId;
        return this;
    }

    public String getSort() {
        return sort;
    }

    public LogIndexConfigMysqlPO setSort(String sort) {
        this.sort = sort;
        return this;
    }

    public Long getValueIndex() {
        return valueIndex;
    }

    public LogIndexConfigMysqlPO setValueIndex(Long valueIndex) {
        this.valueIndex = valueIndex;
        return this;
    }

    public String getValueIndexKey() {
        return valueIndexKey;
    }

    public LogIndexConfigMysqlPO setValueIndexKey(String valueIndexKey) {
        this.valueIndexKey = valueIndexKey;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public LogIndexConfigMysqlPO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public LogIndexConfigMysqlPO setVersion(Long version) {
        this.version = version;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LogIndexConfigMysqlPO setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public LogIndexConfigMysqlPO setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    public String getLogConfigId() {
        return logConfigId;
    }
}
