package org.manage.log.common.model.config.builder;

import org.manage.log.common.model.log.constants.LogRecordIndexSort;
import org.manage.log.common.model.config.LogIndexConfig;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/12/12 20:39
 */
public class LogIndexConfigBuilder {

    private String ruleName;

    private LogRecordIndexSort logRecordIndexSort;
    
    private String valueIndexKey;

    private String description;

    private Long version = 1L;

    private LocalDateTime createTime = LocalDateTime.now();

    private LocalDateTime modifyTime = LocalDateTime.now();

    public LogIndexConfigBuilder setRuleName(String ruleName) {
        if(Objects.isNull(ruleName)){
            return this;
        }
        this.ruleName = ruleName;
        return this;
    }

    public LogIndexConfigBuilder setLogRecordIndexSort(LogRecordIndexSort logRecordIndexSort) {
        if(Objects.isNull(logRecordIndexSort)){
            return this;
        }
        this.logRecordIndexSort = logRecordIndexSort;
        return this;
    }

    public LogIndexConfigBuilder setValueIndexKey(String valueIndexKey) {
        if(Objects.isNull(valueIndexKey)){
            return this;
        }
        this.valueIndexKey = valueIndexKey;
        return this;
    }

    public LogIndexConfigBuilder setDescription(String description) {
        if(Objects.isNull(description)){
            return this;
        }
        this.description = description;
        return this;
    }

    private LogIndexConfigFactory logIndexConfigFactory;

    public static LogIndexConfigBuilder getInstance(LogIndexConfigFactory logIndexConfigFactory){
        return new LogIndexConfigBuilder(logIndexConfigFactory);
    }

    private LogIndexConfigBuilder(LogIndexConfigFactory logIndexConfigFactory) {
        this.logIndexConfigFactory = logIndexConfigFactory;
    }

    public LogIndexConfig build(){
        LogIndexConfig config = new LogIndexConfig(
                logIndexConfigFactory.generateRuleId(),
                ruleName,
                logRecordIndexSort,
                valueIndexKey,
                description,
                version,
                createTime,
                modifyTime
        );
        logIndexConfigFactory.check(config);
        return config;
    }
}
