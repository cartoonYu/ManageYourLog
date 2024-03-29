package org.manage.log.common.model.config.builder;


import org.manage.log.common.model.config.LogContentFormatConfig;
import org.manage.log.common.model.log.constants.LogRecordSort;
import org.manage.log.common.model.log.constants.OperatorSort;
import org.manage.log.common.model.config.LogConfig;
import org.manage.log.common.model.config.LogIndexConfig;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author cartoon
 * @since 2022/11/15 21:14
 */
public class LogConfigBuilder {

    private String ruleId;

    private String ruleName;

    private LogRecordSort logRecordSort;

    private OperatorSort operatorSort;

    private String contentTemplate;

    private List<LogIndexConfig> indexConfigList = new ArrayList<>();

    private List<LogContentFormatConfig> contentFormatConfigList = new ArrayList<>();

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

    public LogConfigBuilder setRuleId(String ruleId) {
        this.ruleId = ruleId;
        return this;
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

    public LogConfigBuilder setContentTemplate(String contentTemplate) {
        this.contentTemplate = contentTemplate;
        return this;
    }

    public LogConfigBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public LogConfigBuilder setIndexConfigList(List<LogIndexConfig> indexConfigList){
        if(Objects.isNull(indexConfigList)){
            return this;
        }
        this.indexConfigList.addAll(indexConfigList);
        return this;
    }

    public LogConfigBuilder setContentFormatConfigList(List<LogContentFormatConfig> contentFormatConfigList){
        if(Objects.isNull(contentFormatConfigList)){
            return this;
        }
        this.contentFormatConfigList.addAll(contentFormatConfigList);
        return this;
    }


    public LogConfig build(){
        if(Objects.isNull(ruleId)){
            ruleId = logConfigFactory.generateRuleId();
        }
        LogConfig logConfig = new LogConfig(
                ruleId,
                ruleName,
                logRecordSort,
                operatorSort,
                contentTemplate,
                indexConfigList,
                contentFormatConfigList,
                description,
                version,
                createTime,
                modifyTime
        );
        logConfigFactory.check(logConfig);
        return logConfig;
    }
}
