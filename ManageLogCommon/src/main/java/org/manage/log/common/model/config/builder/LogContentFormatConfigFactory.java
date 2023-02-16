package org.manage.log.common.model.config.builder;

import org.manage.log.common.model.config.LogContentFormatConfig;
import org.manage.log.common.model.config.constants.LogContentFormatType;
import org.manage.log.common.util.IdGenerateUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @version 1.0
 * @since 2023/1/8 20:17
 */
@Component
public class LogContentFormatConfigFactory {

    public String generateRuleId(){
        return IdGenerateUtil.getInstance().generate(13);
    }

    public void check(LogContentFormatConfig logContentFormatConfig){
        Assert.notNull(logContentFormatConfig.ruleId(), "ruleId must not be null");
        Assert.notNull(logContentFormatConfig.ruleName(), "ruleId must not be null");
        Assert.notNull(logContentFormatConfig.type(), "type must not be null");
        Assert.notNull(logContentFormatConfig.value(), "value must not be null");
        Assert.notNull(logContentFormatConfig.executeSequence(), "executeSequence must not be null");
        Assert.notNull(logContentFormatConfig.version(), "version must not be null");
        Assert.notNull(logContentFormatConfig.createTime(), "createTime must not be null");
        Assert.notNull(logContentFormatConfig.modifyTime(), "modifyTime must not be null");
    }

    public LogContentFormatConfig build(
            String ruleId, String ruleName, LogContentFormatType type,
            String value, Long executeSequence, Long version,
            LocalDateTime createTime, LocalDateTime modifyTime){
        return LogContentFormatConfigBuilder.getInstance(this)
                .setRuleId(ruleId)
                .setRuleName(ruleName)
                .setType(type)
                .setValue(value)
                .setExecuteSequence(executeSequence)
                .setVersion(version)
                .setCreateTime(createTime)
                .setModifyTime(modifyTime)
                .build();
    }

    public LogContentFormatConfig build(
            String ruleName, LogContentFormatType type,
            String value, Long executeSequence){
        return LogContentFormatConfigBuilder.getInstance(this)
                .setRuleName(ruleName)
                .setType(type)
                .setValue(value)
                .setExecuteSequence(executeSequence)
                .build();
    }
}
