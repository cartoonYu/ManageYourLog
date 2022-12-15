package org.manage.log.common.model.config.builder;


import org.manage.log.common.constants.LogRecordIndexSort;
import org.manage.log.common.constants.LogRecordSort;
import org.manage.log.common.constants.OperatorSort;
import org.manage.log.common.model.config.LogConfig;
import org.manage.log.common.model.config.LogIndexConfig;
import org.manage.log.common.util.IdGenerateUtil;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author cartoon
 * @since 2022/11/15 21:14
 */
public class LogConfigFactory {

    private static final LogConfigFactory INSTANCE = new LogConfigFactory();

    public static LogConfigFactory getInstance(){
        return INSTANCE;
    }

    public void check(LogConfig logConfig){
        Assert.notNull(logConfig.getRuleId(), "config id must not null");
        Assert.notNull(logConfig.getRuleName(), "config name must not null");
        Assert.notNull(logConfig.getLogRecordSort(), "config record sort must not null");
        Assert.notNull(logConfig.getOperatorSort(), "config operator sort must not null");
        Assert.notNull(logConfig.getIndexConfigList(), "config index list must not null");
        Assert.notNull(logConfig.getVersion(), "config version must not null");
        Assert.notNull(logConfig.getCreateTime(), "config create time must not null");
        Assert.notNull(logConfig.getModifyTime(), "config modify time must not null");
    }

    public String generateRuleId(){
        return IdGenerateUtil.getInstance().generate(13);
    }

    public LogConfig build(String ruleName, LogRecordSort logRecordSort, OperatorSort operatorSort,
                           List<LogIndexConfig> logIndexConfigs, String description){
        return LogConfigBuilder.instance(this)
                .setRuleName(ruleName)
                .setLogRecordSort(logRecordSort)
                .setOperatorSort(operatorSort)
                .setIndexConfigList(logIndexConfigs)
                .setDescription(description)
                .build();
    }

    private LogConfigFactory(){

    }
}
