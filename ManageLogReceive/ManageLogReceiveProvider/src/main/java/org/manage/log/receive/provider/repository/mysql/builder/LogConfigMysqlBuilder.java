package org.manage.log.receive.provider.repository.mysql.builder;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.manage.log.common.model.log.constants.LogRecordIndexSort;
import org.manage.log.common.model.log.constants.LogRecordSort;
import org.manage.log.common.model.log.constants.OperatorSort;
import org.manage.log.common.model.config.LogConfig;
import org.manage.log.common.model.config.LogIndexConfig;
import org.manage.log.common.model.config.builder.LogConfigFactory;
import org.manage.log.common.model.config.builder.LogIndexConfigFactory;
import org.manage.log.receive.provider.repository.mysql.model.config.LogConfigMysqlPO;
import org.manage.log.receive.provider.repository.mysql.model.config.LogContentFormatConfigMysqlPO;
import org.manage.log.receive.provider.repository.mysql.model.config.LogIndexConfigMysqlPO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author cartoon
 * @date 2022/6/4 17:53
 */
@Component
public class LogConfigMysqlBuilder {

    private final LogConfigFactory logConfigFactory;

    private final LogIndexConfigFactory logIndexConfigFactory;

    public ImmutablePair<LogConfigMysqlPO, ImmutablePair<List<LogIndexConfigMysqlPO>, List<LogContentFormatConfigMysqlPO>>> convert(LogConfig source){
        return ImmutablePair.of(convertToLogConfig(source), ImmutablePair.of(convertToLogIndexConfig(source), convertToContentFormatConfig(source)));
    }

    public List<LogContentFormatConfigMysqlPO> convertToContentFormatConfig(LogConfig logConfig){
        return logConfig.formatContentConfig().stream().map(formatContentConfig ->
                new LogContentFormatConfigMysqlPO(
                    formatContentConfig.ruleId(), formatContentConfig.ruleName(),
                    formatContentConfig.type().getType(), formatContentConfig.value(),
                    formatContentConfig.executeSequence(), formatContentConfig.version(),
                    formatContentConfig.createTime(), formatContentConfig.modifyTime()
        )).toList();
    }

    public List<LogIndexConfigMysqlPO> convertToLogIndexConfig(LogConfig logConfig){
        return logConfig.indexConfigList().stream().map(indexConfig -> {
            LogIndexConfigMysqlPO result = new LogIndexConfigMysqlPO();
            result.setRuleId(indexConfig.ruleId())
                    .setRuleName(indexConfig.ruleName())
                    .setLogConfigId(logConfig.ruleId())
                    .setSort(indexConfig.logRecordIndexSort().getSortDescription())
                    .setValueIndexKey(indexConfig.valueIndexKey())
                    .setDescription(indexConfig.description())
                    .setVersion(indexConfig.version())
                    .setCreateTime(indexConfig.createTime())
                    .setModifyTime(indexConfig.modifyTime());
            return result;
        }).toList();
    }

    public LogConfigMysqlPO convertToLogConfig(LogConfig source){
        LogConfigMysqlPO res = new LogConfigMysqlPO();
        res.setRuleId(source.ruleId())
                .setRuleName(source.ruleName())
                .setLogRecordSort(source.logRecordSort().getSortDescription())
                .setOperatorSort(source.operatorSort().getSortDescription())
                .setContentTemplate(source.contentTemplate())
                .setDescription(source.description())
                .setVersion(source.version())
                .setCreateTime(source.createTime())
                .setModifyTime(source.modifyTime());
        return res;
    }

    public LogConfig convert(LogConfigMysqlPO source, List<LogIndexConfigMysqlPO> indexList){
        if(Objects.isNull(source)){
            return null;
        }
        LogConfig logConfig = new LogConfig(
                source.getRuleId(),
                source.getRuleName(),
                LogRecordSort.parse(source.getLogRecordSort()),
                OperatorSort.parse(source.getOperatorSort()),
                source.getContentTemplate(),
                convert(indexList),
                //todo content format config mysql po define
                ImmutableList.of(),
                source.getDescription(),
                source.getVersion(),
                source.getCreateTime(),
                source.getModifyTime()
        );
        logConfigFactory.check(logConfig);
        return logConfig;
    }

    private List<LogIndexConfig> convert(List<LogIndexConfigMysqlPO> indexList){
        return indexList.stream().map(this::convert).toList();
    }

    private LogIndexConfig convert(LogIndexConfigMysqlPO index){
        LogIndexConfig logIndexConfig = new LogIndexConfig(index.getRuleId(),
                index.getRuleName(),
                LogRecordIndexSort.parse(index.getSort()),
                index.getValueIndexKey(),
                index.getDescription(),
                index.getVersion(),
                index.getCreateTime(),
                index.getModifyTime());
        logIndexConfigFactory.check(logIndexConfig);
        return logIndexConfig;
    }

    public LogConfigMysqlBuilder(LogConfigFactory logConfigFactory,
                                 LogIndexConfigFactory logIndexConfigFactory) {
        this.logConfigFactory = logConfigFactory;
        this.logIndexConfigFactory = logIndexConfigFactory;
    }
}
