package org.manage.log.receive.provider.repository.mysql.model.config;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/12/11 10:59
 */
public record LogIndexConfigMysqlPO(
        String ruleId, String ruleName, String logConfigId,
        String sort, String valueIndexKey,
        String description, Long version, LocalDateTime createTime, LocalDateTime modifyTime) {

    @Override
    public String ruleId() {
        return ruleId;
    }

    @Override
    public String ruleName() {
        return ruleName;
    }

    @Override
    public String logConfigId() {
        return logConfigId;
    }

    @Override
    public String sort() {
        return sort;
    }

    @Override
    public String valueIndexKey() {
        return valueIndexKey;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public Long version() {
        return version;
    }

    @Override
    public LocalDateTime createTime() {
        return createTime;
    }

    @Override
    public LocalDateTime modifyTime() {
        return modifyTime;
    }
}
