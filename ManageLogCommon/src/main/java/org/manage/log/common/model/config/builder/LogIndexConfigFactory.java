package org.manage.log.common.model.config.builder;

import org.manage.log.common.constants.LogRecordIndexSort;
import org.manage.log.common.model.config.LogIndexConfig;
import org.manage.log.common.util.IdGenerateUtil;
import org.springframework.util.Assert;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/12/12 20:48
 */
public class LogIndexConfigFactory {

    private static final LogIndexConfigFactory INSTANCE = new LogIndexConfigFactory();

    public static LogIndexConfigFactory getInstance(){
        return INSTANCE;
    }

    public void check(LogIndexConfig logIndexConfig){
        Assert.notNull(logIndexConfig.getRuleId(), "rule id must not be null");
        Assert.notNull(logIndexConfig.getRuleName(), "rule name must not be null");
        Assert.notNull(logIndexConfig.getLogRecordIndexSort(), "log record index sort must not be null");
        Assert.notNull(logIndexConfig.getValueIndex(), "value index sort must not be null");
        Assert.notNull(logIndexConfig.getVersion(), "version must not be null");
        Assert.notNull(logIndexConfig.getCreateTime(), "create time must not be null");
        Assert.notNull(logIndexConfig.getModifyTime(), "modify time must not be null");
    }

    public String generateRuleId(){
        return IdGenerateUtil.getInstance().generate(13);
    }

    public LogIndexConfig build(String ruleName, LogRecordIndexSort logRecordIndexSort,
                                Long valueIndex, String description){
        return LogIndexConfigBuilder.getInstance(this)
                .setRuleName(ruleName)
                .setLogRecordIndexSort(logRecordIndexSort)
                .setValueIndex(valueIndex)
                .setDescription(description).build();
    }
}
