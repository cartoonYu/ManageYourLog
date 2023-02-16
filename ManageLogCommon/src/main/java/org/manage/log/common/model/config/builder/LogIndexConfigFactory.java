package org.manage.log.common.model.config.builder;

import org.manage.log.common.model.log.constants.LogRecordIndexSort;
import org.manage.log.common.model.config.LogIndexConfig;
import org.manage.log.common.util.IdGenerateUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/12/12 20:48
 */
@Component
public class LogIndexConfigFactory {

    public void check(LogIndexConfig logIndexConfig){
        Assert.notNull(logIndexConfig.ruleId(), "rule id must not be null");
        Assert.notNull(logIndexConfig.ruleName(), "rule name must not be null");
        Assert.notNull(logIndexConfig.logRecordIndexSort(), "log record index sort must not be null");
        Assert.notNull(logIndexConfig.valueIndexKey(), "value index key must not be null");
        Assert.notNull(logIndexConfig.version(), "version must not be null");
        Assert.notNull(logIndexConfig.createTime(), "create time must not be null");
        Assert.notNull(logIndexConfig.modifyTime(), "modify time must not be null");
    }

    public String generateRuleId(){
        return IdGenerateUtil.getInstance().generate(13);
    }

    public LogIndexConfig build(String ruleName, LogRecordIndexSort logRecordIndexSort,
                                String valueIndexKey, String description){
        return LogIndexConfigBuilder.getInstance(this)
                .setRuleName(ruleName)
                .setLogRecordIndexSort(logRecordIndexSort)
                .setValueIndexKey(valueIndexKey)
                .setDescription(description).build();
    }
}
