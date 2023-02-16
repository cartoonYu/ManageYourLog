package org.manage.log.query.provider.repository;

import com.google.common.collect.ImmutableList;
import org.manage.log.common.model.log.LogRecord;
import org.manage.log.common.util.factory.LoadBean;
import org.manage.log.query.provider.repository.mysql.builder.MysqlEntityBuilder;
import org.manage.log.query.provider.repository.mysql.mapper.LogRecordIndexMapper;
import org.manage.log.query.provider.repository.mysql.mapper.LogRecordMapper;
import org.manage.log.query.provider.repository.mysql.model.LogRecordIndexMysqlPO;
import org.manage.log.query.provider.repository.mysql.model.LogRecordMysqlPO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * mysql store repository
 * @author cartoon
 * @date 2021/11/25 20:30
 */
@Repository
@LoadBean(primaryConfigKey = "store.mode", loadConfigKey = "store.load.mode", mode = "mysql")
public class LogRecordMysqlRepository implements LogRecordRepository {

    private final LogRecordIndexMapper logRecordIndexMapper;

    private final LogRecordMapper logRecordMapper;

    private final MysqlEntityBuilder mysqlEntityBuilder;

    @Override
    public List<LogRecord> getByIndex(String index) {
        List<LogRecordIndexMysqlPO> indexPoList = logRecordIndexMapper.getByIndex(index);
        return getIndexListByIndexList(indexPoList);
    }

    @Override
    public List<LogRecord> getByTime(LocalDateTime startTime, LocalDateTime endTime) {
        List<LogRecordMysqlPO> logRecordMysqlPoList = logRecordMapper.getByTime(startTime, endTime);
        List<String> recordIdList = logRecordMysqlPoList.stream().map(LogRecordMysqlPO::recordId).toList();
        //get all index which is related to log record
        List<LogRecordIndexMysqlPO> indexPoList = logRecordIndexMapper.getByRecordIdList(recordIdList);
        return mysqlEntityBuilder.convertToModel(logRecordMysqlPoList, indexPoList);
    }

    @Override
    public List<LogRecord> getByIndexAndTime(String index, LocalDateTime startTime, LocalDateTime endTime) {
        List<LogRecordIndexMysqlPO> indexMysqlList = logRecordIndexMapper.getByIndexAndTime(index, startTime, endTime);
        return getIndexListByIndexList(indexMysqlList);
    }

    private List<LogRecord> getIndexListByIndexList(List<LogRecordIndexMysqlPO> sourceIndexMysqlList){
        if(sourceIndexMysqlList.isEmpty()){
            return ImmutableList.of();
        }
        //get all record id from index list
        List<String> recordIds = sourceIndexMysqlList.stream().map(LogRecordIndexMysqlPO::logRecordId).toList();
        //get record by id from database
        List<LogRecordMysqlPO> logRecordMysqlPoList = logRecordMapper.getById(recordIds);
        //get all index which is related to log record
        List<LogRecordIndexMysqlPO> indexPoList = logRecordIndexMapper.getByRecordIdList(recordIds);
        return mysqlEntityBuilder.convertToModel(logRecordMysqlPoList, indexPoList);
    }

    public LogRecordMysqlRepository(LogRecordIndexMapper logRecordIndexMapper,
                                    LogRecordMapper logRecordMapper,
                                    MysqlEntityBuilder mysqlEntityBuilder) {
        this.logRecordIndexMapper = logRecordIndexMapper;
        this.logRecordMapper = logRecordMapper;
        this.mysqlEntityBuilder = mysqlEntityBuilder;
    }
}
