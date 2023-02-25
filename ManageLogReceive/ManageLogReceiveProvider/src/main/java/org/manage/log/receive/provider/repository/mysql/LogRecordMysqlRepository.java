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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * mysql store repository
 * @author cartoon
 * @date 2021/11/25 20:30
 */
@Repository
@LoadBean(loadConfigKey = "store.load.mode", mode = "mysql")
public class LogRecordMysqlRepository implements LogRecordRepository {

    private final MysqlDatasourceOperate mysqlDatasourceOperate;

    private final LogRecordMapper logRecordMapper;

    private final LogRecordIndexMapper logRecordIndexMapper;

    @Override
    public boolean save(List<LogRecord> logRecords) {
        List<ImmutablePair<LogRecordMysqlPO, List<LogRecordIndexMysqlPO>>> logInfos = LogRecordMysqlBuilder.getInstance().convertToPo(logRecords);
        List<LogRecordMysqlPO> logRecordPos = logInfos.parallelStream().map(ImmutablePair::getLeft).toList();
        List<LogRecordIndexMysqlPO> logRecordIndexPos = logInfos.parallelStream().map(ImmutablePair::getRight).flatMap(Collection::stream).toList();

        List<ImmutablePair<Supplier<Integer>, Integer>> executeInfos = new ArrayList<>();
        if(!logRecordIndexPos.isEmpty()){
            executeInfos.add(ImmutablePair.of(() -> logRecordIndexMapper.batchInsert(logRecordIndexPos), logRecordIndexPos.size()));
        }
        executeInfos.add(ImmutablePair.of(() -> logRecordMapper.batchInsert(logRecordPos), logRecordPos.size()));
        return mysqlDatasourceOperate.executeDML(executeInfos);
    }

    public LogRecordMysqlRepository(MysqlDatasourceOperate mysqlDatasourceOperate,
                                    LogRecordMapper logRecordMapper,
                                    LogRecordIndexMapper logRecordIndexMapper) {
        this.mysqlDatasourceOperate = mysqlDatasourceOperate;
        this.logRecordMapper = logRecordMapper;
        this.logRecordIndexMapper = logRecordIndexMapper;
    }
}
