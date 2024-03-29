package org.manage.log.receive.provider.repository.mysql.builder;

import org.manage.log.common.model.config.LogContentFormatConfig;
import org.manage.log.common.model.config.builder.LogContentFormatConfigFactory;
import org.manage.log.common.model.config.constants.LogContentFormatType;
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

    private final LogContentFormatConfigFactory logContentFormatConfigFactory;

    public List<LogContentFormatConfigMysqlPO> convertToContentFormatConfig(LogConfig logConfig){
        return logConfig.formatContentConfig().parallelStream().map(formatContentConfig ->
                new LogContentFormatConfigMysqlPO(
                    formatContentConfig.ruleId(), formatContentConfig.ruleName(), logConfig.ruleId(),
                    formatContentConfig.type().getType(), formatContentConfig.value(),
                    formatContentConfig.executeSequence(), formatContentConfig.version(),
                    formatContentConfig.createTime(), formatContentConfig.modifyTime()
        )).toList();
    }

    public List<LogIndexConfigMysqlPO> convertToLogIndexConfig(LogConfig logConfig){
        return logConfig.indexConfigList().parallelStream().map(indexConfig ->
                new LogIndexConfigMysqlPO(
                    indexConfig.ruleId(), indexConfig.ruleName(), logConfig.ruleId(),
                    indexConfig.logRecordIndexSort().getSortDescription(), indexConfig.valueIndexKey(),
                    indexConfig.description(), indexConfig.version(), indexConfig.createTime(), indexConfig.modifyTime()
        )).toList();
    }

    public LogConfigMysqlPO convertToLogConfig(LogConfig source){
        return new LogConfigMysqlPO(
                source.ruleId(), source.ruleName(),
                source.logRecordSort().getSortDescription(), source.operatorSort().getSortDescription(),
                source.contentTemplate(), source.description(),
                source.version(), source.createTime(),source.modifyTime()
        );
    }

    public LogConfig convert(LogConfigMysqlPO source, List<LogIndexConfigMysqlPO> indexList,
                             List<LogContentFormatConfigMysqlPO> contentFormatConfigList){
        if(Objects.isNull(source)){
            return null;
        }
        LogConfig logConfig = new LogConfig(
                source.ruleId(),
                source.ruleName(),
                LogRecordSort.parse(source.logRecordSort()),
                OperatorSort.parse(source.operatorSort()),
                source.contentTemplate(),
                convert(indexList),
                convertContentFormatConfig(contentFormatConfigList),
                source.description(),
                source.version(),
                source.createTime(),
                source.modifyTime()
        );
        logConfigFactory.check(logConfig);
        return logConfig;
    }

    private List<LogIndexConfig> convert(List<LogIndexConfigMysqlPO> indexList){
        return indexList.parallelStream().map(this::convert).toList();
    }

    private LogIndexConfig convert(LogIndexConfigMysqlPO index){
        LogIndexConfig logIndexConfig = new LogIndexConfig(index.ruleId(),
                index.ruleName(),
                LogRecordIndexSort.parse(index.sort()),
                index.valueIndexKey(),
                index.description(),
                index.version(),
                index.createTime(),
                index.modifyTime());
        logIndexConfigFactory.check(logIndexConfig);
        return logIndexConfig;
    }

    private List<LogContentFormatConfig> convertContentFormatConfig(List<LogContentFormatConfigMysqlPO> contentFormatConfigList){
        return contentFormatConfigList.parallelStream().filter(Objects::nonNull).map(this::convert).toList();
    }

    private LogContentFormatConfig convert(LogContentFormatConfigMysqlPO formatConfig){
        LogContentFormatConfig logContentFormatConfig = new LogContentFormatConfig(
                formatConfig.ruleId(), formatConfig.ruleName(),
                LogContentFormatType.parse(formatConfig.type()), formatConfig.value(),
                formatConfig.executeSequence(), formatConfig.version(),
                formatConfig.createTime(), formatConfig.modifyTime()
        );
        logContentFormatConfigFactory.check(logContentFormatConfig);
        return logContentFormatConfig;
    }

    public LogConfigMysqlBuilder(LogConfigFactory logConfigFactory,
                                 LogIndexConfigFactory logIndexConfigFactory,
                                 LogContentFormatConfigFactory contentFormatConfigFactory) {
        this.logConfigFactory = logConfigFactory;
        this.logIndexConfigFactory = logIndexConfigFactory;
        this.logContentFormatConfigFactory = contentFormatConfigFactory;
    }
}
