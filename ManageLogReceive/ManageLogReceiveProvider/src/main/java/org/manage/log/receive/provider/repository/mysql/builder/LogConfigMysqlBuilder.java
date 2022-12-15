package org.manage.log.receive.provider.repository.mysql.builder;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.manage.log.common.constants.LogRecordIndexSort;
import org.manage.log.common.constants.LogRecordSort;
import org.manage.log.common.constants.OperatorSort;
import org.manage.log.common.model.config.LogConfig;
import org.manage.log.common.model.config.LogIndexConfig;
import org.manage.log.receive.provider.repository.mysql.model.LogConfigMysqlPO;
import org.manage.log.receive.provider.repository.mysql.model.LogIndexConfigMysqlPO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author cartoon
 * @date 2022/6/4 17:53
 */
public class LogConfigMysqlBuilder {

    private static final LogConfigMysqlBuilder INSTANCE = new LogConfigMysqlBuilder();

    public ImmutablePair<LogConfigMysqlPO, List<LogIndexConfigMysqlPO>> convert(LogConfig source){
        return ImmutablePair.of(convertToLogConfig(source), convertToLogIndexConfig(source));
    }

    private List<LogIndexConfigMysqlPO> convertToLogIndexConfig(LogConfig logConfig){
        return logConfig.getIndexConfigList().stream().map(indexConfig -> {
            LogIndexConfigMysqlPO result = new LogIndexConfigMysqlPO();
            result.setRuleId(indexConfig.getRuleId())
                    .setRuleName(indexConfig.getRuleName())
                    .setLogConfigId(logConfig.getRuleId())
                    .setSort(indexConfig.getLogRecordIndexSort().getSortDescription())
                    .setValueIndex(indexConfig.getValueIndex())
                    .setDescription(indexConfig.getDescription())
                    .setVersion(indexConfig.getVersion())
                    .setCreateTime(indexConfig.getCreateTime())
                    .setModifyTime(indexConfig.getModifyTime());
            return result;
        }).collect(Collectors.toList());
    }

    private LogConfigMysqlPO convertToLogConfig(LogConfig source){
        LogConfigMysqlPO res = new LogConfigMysqlPO();
        res.setRuleId(source.getRuleId())
                .setRuleName(source.getRuleName())
                .setLogRecordSort(source.getLogRecordSort().getSortDescription())
                .setOperatorSort(source.getOperatorSort().getSortDescription())
                .setDescription(source.getDescription())
                .setVersion(source.getVersion())
                .setCreateTime(source.getCreateTime())
                .setModifyTime(source.getModifyTime());
        return res;
    }

    public LogConfig convert(LogConfigMysqlPO source, List<LogIndexConfigMysqlPO> indexList){
        if(Objects.isNull(source)){
            return null;
        }
        LogConfig res = new LogConfig();
        res.setRuleId(source.getRuleId())
                .setRuleName(source.getRuleName())
                .setLogRecordSort(LogRecordSort.parse(source.getLogRecordSort()))
                .setOperatorSort(OperatorSort.parse(source.getOperatorSort()))
                .addIndexConfig(convert(indexList))
                .setDescription(source.getDescription())
                .setVersion(source.getVersion())
                .setCreateTime(source.getCreateTime())
                .setModifyTime(source.getModifyTime());
        return res;
    }

    private List<LogIndexConfig> convert(List<LogIndexConfigMysqlPO> indexList){
        return indexList.stream().map(this::convert).collect(Collectors.toList());
    }

    private LogIndexConfig convert(LogIndexConfigMysqlPO index){
        LogIndexConfig result = new LogIndexConfig();
        result.setRuleId(index.getRuleId())
                .setRuleName(index.getRuleName())
                .setLogRecordIndexSort(LogRecordIndexSort.parse(index.getSort()))
                .setValueIndex(index.getValueIndex())
                .setDescription(index.getDescription())
                .setVersion(index.getVersion())
                .setCreateTime(index.getCreateTime())
                .setModifyTime(index.getModifyTime());
        return result;
    }

    public static LogConfigMysqlBuilder getInstance(){
        return INSTANCE;
    }
}
