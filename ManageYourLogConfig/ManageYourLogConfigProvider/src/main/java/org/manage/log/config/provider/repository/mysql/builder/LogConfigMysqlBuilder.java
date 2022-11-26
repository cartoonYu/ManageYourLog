package org.manage.log.config.provider.repository.mysql.builder;

import org.manage.log.common.constants.LogRecordIndexSort;
import org.manage.log.common.constants.LogRecordSort;
import org.manage.log.common.constants.OperatorSort;
import org.manage.log.common.model.config.LogConfig;
import org.manage.log.config.provider.repository.mysql.model.LogConfigMysqlPO;

import java.util.Objects;

/**
 * @author cartoon
 * @date 2022/6/4 17:53
 */
public class LogConfigMysqlBuilder {

    private static final LogConfigMysqlBuilder INSTANCE = new LogConfigMysqlBuilder();

    public LogConfigMysqlPO convert(LogConfig source){
        LogConfigMysqlPO res = new LogConfigMysqlPO();
        res.setRuleId(source.getRuleId())
                .setRuleName(source.getRuleName())
                .setLogRecordSort(source.getLogRecordSort().getSortDescription())
                .setOperatorSort(source.getOperatorSort().getSortDescription())
                .setIndexSort(source.getIndexSort().getSortDescription())
                .setDescription(source.getDescription())
                .setVersion(source.getVersion())
                .setCreateTime(source.getCreateTime())
                .setModifyTime(source.getModifyTime());
        return res;
    }

    public LogConfig convert(LogConfigMysqlPO source){
        if(Objects.isNull(source)){
            return null;
        }
        LogConfig res = new LogConfig();
        res.setRuleId(source.getRuleId())
                .setRuleName(source.getRuleName())
                .setLogRecordSort(LogRecordSort.parse(source.getLogRecordSort()))
                .setOperatorSort(OperatorSort.parse(source.getOperatorSort()))
                .setIndexSort(LogRecordIndexSort.parse(source.getIndexSort()))
                .setDescription(source.getDescription())
                .setVersion(source.getVersion())
                .setCreateTime(source.getCreateTime())
                .setModifyTime(source.getModifyTime());
        return res;
    }

    public static LogConfigMysqlBuilder getInstance(){
        return INSTANCE;
    }
}
