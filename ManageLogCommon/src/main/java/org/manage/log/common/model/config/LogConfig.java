package org.manage.log.common.model.config;

import org.manage.log.common.model.log.constants.LogRecordSort;
import org.manage.log.common.model.log.constants.OperatorSort;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author cartoon
 * @date 2022/6/4 16:46
 */
public record LogConfig(
        String ruleId,
        String ruleName,
        LogRecordSort logRecordSort,
        OperatorSort operatorSort,
        String contentTemplate,
        List<LogIndexConfig> indexConfigList,
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
    public LogRecordSort logRecordSort() {
        return logRecordSort;
    }

    @Override
    public OperatorSort operatorSort() {
        return operatorSort;
    }

    @Override
    public String contentTemplate() {
        return contentTemplate;
    }

    @Override
    public List<LogIndexConfig> indexConfigList() {
        return indexConfigList;
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
