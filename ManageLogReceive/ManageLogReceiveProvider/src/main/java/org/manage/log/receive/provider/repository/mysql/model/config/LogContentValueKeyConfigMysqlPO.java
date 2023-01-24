package org.manage.log.receive.provider.repository.mysql.model.config;

import java.time.LocalDateTime;

public record LogContentValueKeyConfigMysqlPO(
        String ruleId,
        String configId,
        String sourceKey,
        String currentKey,
        Long sequence,
        Long version,
        LocalDateTime createTime,
        LocalDateTime modifyTime) {
}
