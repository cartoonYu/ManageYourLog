package org.manage.log.common.model.config;

import org.manage.log.common.model.log.constants.LogRecordIndexSort;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/12/11 20:59
 */
public record LogIndexConfig(
        String ruleId,
        String ruleName,
        LogRecordIndexSort logRecordIndexSort,
        String valueIndexKey,
        String description,
        Long version,
        LocalDateTime createTime,
        LocalDateTime modifyTime) {

    @Override
    public String ruleId() {
        return ruleId;
    }

    @Override
    public String ruleName() {
        return ruleName;
    }

    @Override
    public LogRecordIndexSort logRecordIndexSort() {
        return logRecordIndexSort;
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
