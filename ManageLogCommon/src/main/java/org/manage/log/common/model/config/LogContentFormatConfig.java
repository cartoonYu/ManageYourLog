package org.manage.log.common.model.config;

import org.manage.log.common.model.config.constants.LogContentFormatType;

import java.time.LocalDateTime;

/**
 * todo need to modeling
 * @author cartoon
 * @version 1.0
 * @since 2023/1/6 22:43
 */
public record LogContentFormatConfig(
        String ruleId,
        String ruleNam,
        LogContentFormatType type,
        String value,
        LocalDateTime createTime,
        LocalDateTime modifyTime) {
}
