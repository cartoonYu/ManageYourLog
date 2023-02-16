package org.manage.log.common.model.config;

import org.manage.log.common.model.config.constants.LogContentFormatType;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @version 1.0
 * @since 2023/1/6 22:43
 */
public record LogContentFormatConfig(
        // rule id
        String ruleId,
        //rule name
        String ruleName,
        //choose format type mode
        LogContentFormatType type,
        //format content basis value
        String value,
        //execute sequence
        Long executeSequence,
        Long version,
        LocalDateTime createTime,
        LocalDateTime modifyTime) {
}
