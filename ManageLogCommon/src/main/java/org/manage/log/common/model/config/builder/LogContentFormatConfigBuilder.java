package org.manage.log.common.model.config.builder;

import org.manage.log.common.model.config.LogContentFormatConfig;
import org.manage.log.common.model.config.constants.LogContentFormatType;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author cartoon
 * @version 1.0
 * @since 2023/1/8 20:17
 */
public class LogContentFormatConfigBuilder {

    String ruleId;

    String ruleName;

    LogContentFormatType type;

    String value;

    Long executeSequence;

    Long version = 1L;

    LocalDateTime createTime = LocalDateTime.now();

    LocalDateTime modifyTime = LocalDateTime.now();

    public LogContentFormatConfigBuilder setRuleId(String ruleId) {
        this.ruleId = ruleId;
        return this;
    }

    public LogContentFormatConfigBuilder setRuleName(String ruleName) {
        this.ruleName = ruleName;
        return this;
    }

    public LogContentFormatConfigBuilder setType(LogContentFormatType type) {
        this.type = type;
        return this;
    }

    public LogContentFormatConfigBuilder setValue(String value) {
        this.value = value;
        return this;
    }

    public LogContentFormatConfigBuilder setExecuteSequence(Long executeSequence) {
        this.executeSequence = executeSequence;
        return this;
    }

    public LogContentFormatConfigBuilder setVersion(Long version) {
        this.version = version;
        return this;
    }

    public LogContentFormatConfigBuilder setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LogContentFormatConfigBuilder setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    private LogContentFormatConfigFactory contentFormatConfigFactory;

    public static LogContentFormatConfigBuilder getInstance(LogContentFormatConfigFactory logContentFormatConfigFactory){
        return new LogContentFormatConfigBuilder(logContentFormatConfigFactory);
    }

    private LogContentFormatConfigBuilder(LogContentFormatConfigFactory logContentFormatConfigFactory) {
        this.contentFormatConfigFactory = logContentFormatConfigFactory;
    }

    public LogContentFormatConfig build(){
        if(Objects.isNull(ruleId)){
            ruleId = contentFormatConfigFactory.generateRuleId();
        }
        LogContentFormatConfig logContentFormatConfig = new LogContentFormatConfig(
                ruleId, ruleName,
                type, value,
                executeSequence, version,
                createTime, modifyTime
        );
        contentFormatConfigFactory.check(logContentFormatConfig);
        return logContentFormatConfig;
    }
}
