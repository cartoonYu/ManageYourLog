package org.manage.log.receive.provider.repository.mysql.model.config;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @version 1.0
 * @since 2023/1/8 20:12
 */
public record LogContentFormatConfigMysqlPO(
        // rule id
        String ruleId,
        //rule name
        String ruleName,
        //parent config id,
        String logConfigId,
        //choose format type mode
        String type,
        //format content basis value
        String value,
        //execute sequence
        Long executeSequence,
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
    public String logConfigId() {
        return logConfigId;
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public Long executeSequence() {
        return executeSequence;
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
