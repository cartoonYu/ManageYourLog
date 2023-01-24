package org.manage.log.common.model.config;

import java.time.LocalDateTime;

public record LogContentValueKeyConfig(
        String ruleId,
        String sourceKey,
        String currentKey,
        Long sequence,
        Long version,
        LocalDateTime createTime,
        LocalDateTime modifyTime) {
}
