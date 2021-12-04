package org.manageyourlog.server.repository;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.manageyourlog.server.converter.repository.MockDataConverter;
import org.manageyourlog.server.dao.mock.LogRecordIndexMockEntity;
import org.manageyourlog.server.dao.mock.LogRecordMockEntity;
import org.manageyourlog.server.dao.mock.MockLogRecordDataDao;
import org.manageyourlog.server.dao.mock.MockLogRecordIndexDao;
import org.manageyourlog.server.model.LogRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * default repository, storage mode depend on user choice
 * @author cartoon
 * @date 2021/10/23 17:27
 */
@Repository
public class DefaultLogRecordRepository implements LogRecordRepository{

    @Autowired
    private MockLogRecordIndexDao mockLogRecordIndexDao;

    @Autowired
    private MockLogRecordDataDao mockLogRecordDataDao;

    @Override
    public boolean save(LogRecord logRecord) {
        log.info("default log record, save log: {}", JSONObject.toJSONString(logRecord));
        return true;
    }

    @Override
    public boolean save(List<LogRecord> logRecords) {
        log.info("default log record, save log: {}", JSONArray.toJSONString(logRecords));
        return true;
    }

    @Override
    public List<LogRecord> getByIndex(String index) {
        List<LogRecordIndexMockEntity> logIndexList = mockLogRecordIndexDao.getMockData();
        List<LogRecordMockEntity> logList = mockLogRecordDataDao.getMockData();
        return MockDataConverter.convert(logList, logIndexList);
    }

    @Override
    public List<LogRecord> getByTime(LocalDateTime startTime, LocalDateTime endTime) {
        List<LogRecordIndexMockEntity> logIndexList = mockLogRecordIndexDao.getMockData();
        List<LogRecordMockEntity> logList = mockLogRecordDataDao.getMockData();
        return MockDataConverter.convert(logList, logIndexList);
    }

    @Override
    public List<LogRecord> getByIndexAndTime(String index, LocalDateTime startTime, LocalDateTime endTime) {
        List<LogRecordIndexMockEntity> logIndexList = mockLogRecordIndexDao.getMockData();
        List<LogRecordMockEntity> logList = mockLogRecordDataDao.getMockData();
        return MockDataConverter.convert(logList, logIndexList);
    }
}
