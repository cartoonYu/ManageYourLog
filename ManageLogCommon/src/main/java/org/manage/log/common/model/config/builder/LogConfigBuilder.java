package org.manage.log.common.model.config.builder;


import org.manage.log.common.constants.LogRecordIndexSort;
import org.manage.log.common.constants.LogRecordSort;
import org.manage.log.common.constants.OperatorSort;
import org.manage.log.common.model.config.LogConfig;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @since 2022/11/15 21:14
 */
public class LogConfigBuilder {

    private String ruleName;

    private LogRecordSort logRecordSort;

    private OperatorSort operatorSort;

    private LogRecordIndexSort indexSort;

    private String description;

    private Long version = 1L;

    private LocalDateTime createTime = LocalDateTime.now();

    private LocalDateTime modifyTime = LocalDateTime.now();

    private LogConfigFactory logConfigFactory;

    public static LogConfigBuilder instance(LogConfigFactory logConfigFactory){
        return new LogConfigBuilder(logConfigFactory);
    }

    private LogConfigBuilder(LogConfigFactory logConfigFactory) {
        this.logConfigFactory = logConfigFactory;
    }

    public LogConfigBuilder setRuleName(String ruleName) {
        this.ruleName = ruleName;
        return this;
    }

    public LogConfigBuilder setLogRecordSort(LogRecordSort logRecordSort) {
        this.logRecordSort = logRecordSort;
        return this;
    }

    public LogConfigBuilder setOperatorSort(OperatorSort operatorSort) {
        this.operatorSort = operatorSort;
        return this;
    }

    public LogConfigBuilder setIndexSort(LogRecordIndexSort indexSort) {
        this.indexSort = indexSort;
        return this;
    }

    public LogConfigBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public LogConfig build(){
        LogConfig logConfig = new LogConfig();
        logConfig
                .setRuleId(logConfigFactory.generateRuleId())
                .setRuleName(ruleName)
                .setLogRecordSort(logRecordSort)
                .setOperatorSort(operatorSort)
                .setIndexSort(indexSort)
                .setDescription(description)
                .setVersion(version)
                .setCreateTime(createTime)
                .setModifyTime(modifyTime);
        logConfigFactory.check(logConfig);
        return logConfig;
    }
}
