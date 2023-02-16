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
        //unique id
        String ruleId,
        //config name
        //upper-layer services use to identify which config to use
        String ruleName,
        //log sort，use to fill upload log sort
        LogRecordSort logRecordSort,
        //operator sort, use to fill log operator sort
        OperatorSort operatorSort,
        //log content template
        //upper-layer services only need to send necessary data for logs.
        //config uses template to format the final logs
        String contentTemplate,
        //log index config, use to format log index
        List<LogIndexConfig> indexConfigList,
        //format content template value key config
        //use to extract content template value key
        //transfer to equals upper-layer services income value key
        List<LogContentFormatConfig> formatContentConfig,
        String description,
        Long version,
        LocalDateTime createTime,
        LocalDateTime modifyTime) {
}
