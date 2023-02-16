package org.manage.log.common.model.config.builder;

import org.manage.log.common.model.config.LogContentFormatConfig;
import org.manage.log.common.model.log.constants.LogRecordSort;
import org.manage.log.common.model.log.constants.OperatorSort;
import org.manage.log.common.model.config.LogConfig;
import org.manage.log.common.model.config.LogIndexConfig;
import org.manage.log.common.util.IdGenerateUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author cartoon
 * @since 2022/11/15 21:14
 */
@Component
public class LogConfigFactory {

    public void check(LogConfig logConfig){
        Assert.notNull(logConfig.ruleId(), "config id must not null");
        Assert.notNull(logConfig.ruleName(), "config name must not null");
        Assert.notNull(logConfig.logRecordSort(), "config record sort must not null");
        Assert.notNull(logConfig.operatorSort(), "config operator sort must not null");
        Assert.notNull(logConfig.contentTemplate(), "config content template must not null");
        Assert.notNull(logConfig.version(), "config version must not null");
        Assert.notNull(logConfig.createTime(), "config create time must not null");
        Assert.notNull(logConfig.modifyTime(), "config modify time must not null");
    }

    public String generateRuleId(){
        return IdGenerateUtil.getInstance().generate(13);
    }

    public LogConfig build(String ruleName, LogRecordSort logRecordSort,
                           OperatorSort operatorSort, String contentTemplate,
                           List<LogIndexConfig> logIndexConfigs,
                           List<LogContentFormatConfig> contentFormatConfigList,
                           String description){
        return LogConfigBuilder.instance(this)
                .setRuleName(ruleName)
                .setLogRecordSort(logRecordSort)
                .setOperatorSort(operatorSort)
                .setContentTemplate(contentTemplate)
                .setIndexConfigList(logIndexConfigs)
                .setContentFormatConfigList(contentFormatConfigList)
                .setDescription(description)
                .build();
    }

    private LogConfigFactory(){

    }
}
