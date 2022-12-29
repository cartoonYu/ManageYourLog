package org.manage.log.receive.provider.repository.mysql;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.manage.log.common.model.log.LogRecord;
import org.manage.log.common.util.factory.LoadBean;
import org.manage.log.receive.provider.repository.LogRecordRepository;
import org.manage.log.receive.provider.repository.mysql.builder.LogRecordMysqlBuilder;
import org.manage.log.receive.provider.repository.mysql.mapper.LogRecordIndexMapper;
import org.manage.log.receive.provider.repository.mysql.mapper.LogRecordMapper;
import org.manage.log.receive.provider.repository.mysql.model.LogRecordIndexMysqlPO;
import org.manage.log.receive.provider.repository.mysql.model.LogRecordMysqlPO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * mysql store repository
 * @author cartoon
 * @date 2021/11/25 20:30
 */
@Repository
@LoadBean(loadConfigKey = "store.load.mode", mode = "mysql")
public class LogRecordMysqlRepository implements LogRecordRepository {

    public static final String INDEX_SPLIT_CHARACTER = ",";

    @Resource
    private MysqlDatasourceOperate mysqlDatasourceOperate;

    @Override
    public boolean save(List<LogRecord> logRecords) {
        List<ImmutablePair<LogRecordMysqlPO, List<LogRecordIndexMysqlPO>>> logInfos = LogRecordMysqlBuilder.getInstance().convertToPo(logRecords);
        List<LogRecordMysqlPO> logRecordPos = logInfos.stream().map(ImmutablePair::getLeft).toList();
        List<LogRecordIndexMysqlPO> logRecordIndexPos = logInfos.stream().map(ImmutablePair::getRight).flatMap(Collection::stream).toList();
        boolean insertIndexResult = logRecordIndexPos.isEmpty() || mysqlDatasourceOperate.executeDML(LogRecordIndexMapper.class, mapper -> mapper.batchInsert(logRecordIndexPos) == logRecordIndexPos.size());
        if(!insertIndexResult){
            return false;
        }
        return mysqlDatasourceOperate.executeDML(LogRecordMapper.class, mapper -> mapper.batchInsert(logRecordPos) == logRecordPos.size(), true);
    }
}
