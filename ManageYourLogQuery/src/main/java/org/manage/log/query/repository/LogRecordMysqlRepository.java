package org.manage.log.query.repository;

import com.google.common.collect.ImmutableList;
import org.manage.log.common.model.LogRecord;
import org.manage.log.common.util.factory.LoadBean;
import org.manage.log.query.repository.mysql.builder.MysqlEntityBuilder;
import org.manage.log.query.repository.mysql.mapper.LogRecordIndexMapper;
import org.manage.log.query.repository.mysql.mapper.LogRecordMapper;
import org.manage.log.query.repository.mysql.model.LogRecordIndexMysqlPO;
import org.manage.log.query.repository.mysql.model.LogRecordMysqlPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * mysql store repository
 * @author cartoon
 * @date 2021/11/25 20:30
 */
@Repository
@LoadBean(primaryConfigKey = "store.mode", loadConfigKey = "store.load.mode", mode = "mysql")
public class LogRecordMysqlRepository implements LogRecordRepository {

    public static final String INDEX_SPLIT_CHARACTER = ",";

    @Autowired
    private LogRecordIndexMapper logRecordIndexMapper;

    @Autowired
    private LogRecordMapper logRecordMapper;

    @Override
    public List<LogRecord> getByIndex(String index) {
        List<LogRecordIndexMysqlPO> indexPoList = logRecordIndexMapper.getByIndex(index);
        return getIndexListByIndexList(indexPoList);
    }

    @Override
    public List<LogRecord> getByTime(LocalDateTime startTime, LocalDateTime endTime) {
        List<LogRecordMysqlPO> logRecordMysqlPoList = logRecordMapper.getByTime(startTime, endTime);
        //get all index id which is related to log record
        List<String> indexIds = getIndexIdFromRecordList(logRecordMysqlPoList);
        //get all index by index id
        List<LogRecordIndexMysqlPO> indexMysqlList = logRecordIndexMapper.getByIndexIds(indexIds);
        return MysqlEntityBuilder.getInstance().convertToModel(logRecordMysqlPoList, indexMysqlList);
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
        List<String> recordIds = sourceIndexMysqlList.stream().map(LogRecordIndexMysqlPO::getLogRecordId).toList();
        //get record by id from database
        List<LogRecordMysqlPO> logRecordMysqlPoList = logRecordMapper.getById(recordIds);
        //get all index id which is related to log record
        List<String> indexIds = getIndexIdFromRecordList(logRecordMysqlPoList);
        //get all index by index id
        List<LogRecordIndexMysqlPO> indexMysqlList = logRecordIndexMapper.getByIndexIds(indexIds);
        return MysqlEntityBuilder.getInstance().convertToModel(logRecordMysqlPoList, indexMysqlList);
    }

    private List<String> getIndexIdFromRecordList(List<LogRecordMysqlPO> recordMysqlList){
        return recordMysqlList.stream()
                .map(LogRecordMysqlPO::getIndexIds)
                .map(indexId -> Arrays.stream(indexId.split(INDEX_SPLIT_CHARACTER)).collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .toList();
    }
}
