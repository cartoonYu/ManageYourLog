package org.manage.log.receive.provider.repository.mysql.model.config;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @date 2022/6/4 17:58
 */
public record LogConfigMysqlPO(
        String ruleId, String ruleName,
        String logRecordSort, String operatorSort,
        String contentTemplate, String description,
        Long version, LocalDateTime createTime, LocalDateTime modifyTime) {

    @Override
    public String ruleId() {
        return ruleId;
    }

    @Override
    public String ruleName() {
        return ruleName;
    }

    @Override
    public String logRecordSort() {
        return logRecordSort;
    }

    @Override
    public String operatorSort() {
        return operatorSort;
    }

    @Override
    public String contentTemplate() {
        return contentTemplate;
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
