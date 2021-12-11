package org.manageyourlog.server.repository;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.manageyourlog.common.util.CollectionUtil;
import org.manageyourlog.server.converter.repository.MysqlEntityConverter;
import org.manageyourlog.server.dao.mysql.LogRecordIndexMysqlPO;
import org.manageyourlog.server.dao.mysql.LogRecordMysqlPO;
import org.manageyourlog.server.dao.mysql.mapper.LogRecordIndexMapper;
import org.manageyourlog.server.dao.mysql.mapper.LogRecordMapper;
import org.manageyourlog.server.model.LogRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cartoon
 * @date 2021/11/25 20:30
 */
@Repository
public class LogRecordMysqlRepository implements LogRecordRepository{

    public static final String indexSplitCharacter = ",";

    @Autowired
    private LogRecordIndexMapper logRecordIndexMapper;

    @Autowired
    private LogRecordMapper logRecordMapper;

    @Override
    public boolean save(LogRecord logRecord) {
        ImmutablePair<LogRecordMysqlPO, List<LogRecordIndexMysqlPO>> logInfos = MysqlEntityConverter.convertToPo(logRecord);
        return logRecordMapper.insert(logInfos.getLeft()) == 1
                && logRecordIndexMapper.batchInsert(logInfos.getRight()) == logInfos.getRight().size();
    }

    @Override
    public boolean save(List<LogRecord> logRecords) {
        List<ImmutablePair<LogRecordMysqlPO, List<LogRecordIndexMysqlPO>>> logInfos = MysqlEntityConverter.convertToPo(logRecords);
        List<LogRecordMysqlPO> logRecordPos = logInfos.stream().map(ImmutablePair::getLeft).collect(Collectors.toList());
        List<LogRecordIndexMysqlPO> logRecordIndexPos = logInfos.stream().map(ImmutablePair::getRight).flatMap(Collection::stream).collect(Collectors.toList());
        return logRecordMapper.batchInsert(logRecordPos) == logRecordPos.size()
                 && logRecordIndexMapper.batchInsert(logRecordIndexPos) == logRecordIndexPos.size();
    }

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
        List<LogRecordIndexMysqlPO> indexMysqlPOS = logRecordIndexMapper.getByIndexIds(indexIds);
        return MysqlEntityConverter.convertToModel(logRecordMysqlPoList, indexMysqlPOS);
    }

    @Override
    public List<LogRecord> getByIndexAndTime(String index, LocalDateTime startTime, LocalDateTime endTime) {
        List<LogRecordIndexMysqlPO> indexMysqlPOList = logRecordIndexMapper.getByIndexAndTime(index, startTime, endTime);
        return getIndexListByIndexList(indexMysqlPOList);
    }

    private List<LogRecord> getIndexListByIndexList(List<LogRecordIndexMysqlPO> indexMysqlPOList){
        //get all record id from index list
        List<String> recordIds = indexMysqlPOList.stream().map(LogRecordIndexMysqlPO::getLogRecordId).collect(Collectors.toList());
        //get record by id from database
        List<LogRecordMysqlPO> logRecordMysqlPoList = logRecordMapper.getById(recordIds);
        //get all index id which is related to log record
        List<String> indexIds = getIndexIdFromRecordList(logRecordMysqlPoList);
        //get all index by index id
        List<LogRecordIndexMysqlPO> indexMysqlPOS = logRecordIndexMapper.getByIndexIds(indexIds);
        return MysqlEntityConverter.convertToModel(logRecordMysqlPoList, indexMysqlPOS);
    }

    private List<String> getIndexIdFromRecordList(List<LogRecordMysqlPO> recordMysqlPOS){
        return recordMysqlPOS.stream()
                .map(LogRecordMysqlPO::getIndexIds)
                .map(indexId -> Arrays.stream(indexId.split(indexSplitCharacter)).collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
