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
        List<LogContentFormatConfig> formatContentConfig,
        List<LogContentValueKeyConfig> contentValueKeyConfigs,
        String description,
        Long version,
        LocalDateTime createTime,
        LocalDateTime modifyTime) {
}
