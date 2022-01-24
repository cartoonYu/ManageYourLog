package org.manageyourlog.server.repository;

import org.manageyourlog.common.util.GsonUtil;
import org.manageyourlog.server.repository.builder.MockDataBuilder;
import org.manageyourlog.server.dao.mock.model.LogRecordIndexMockEntity;
import org.manageyourlog.server.dao.mock.model.LogRecordMockEntity;
import org.manageyourlog.server.dao.mock.dao.MockLogRecordDataDao;
import org.manageyourlog.server.dao.mock.dao.MockLogRecordIndexDao;
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
        log.info("default log record, save log: {}", GsonUtil.getInstance().writeJson(logRecord));
        return true;
    }

    @Override
    public boolean save(List<LogRecord> logRecords) {
        log.info("default log record, save log: {}", GsonUtil.getInstance().writeJson(logRecords));
        return true;
    }

    @Override
    public List<LogRecord> getByIndex(String index) {
        List<LogRecordIndexMockEntity> logIndexList = mockLogRecordIndexDao.getMockData();
        List<LogRecordMockEntity> logList = mockLogRecordDataDao.getMockData();
        return MockDataBuilder.getInstance().convert(logList, logIndexList);
    }

    @Override
    public List<LogRecord> getByTime(LocalDateTime startTime, LocalDateTime endTime) {
        List<LogRecordIndexMockEntity> logIndexList = mockLogRecordIndexDao.getMockData();
        List<LogRecordMockEntity> logList = mockLogRecordDataDao.getMockData();
        return MockDataBuilder.getInstance().convert(logList, logIndexList);
    }

    @Override
    public List<LogRecord> getByIndexAndTime(String index, LocalDateTime startTime, LocalDateTime endTime) {
        List<LogRecordIndexMockEntity> logIndexList = mockLogRecordIndexDao.getMockData();
        List<LogRecordMockEntity> logList = mockLogRecordDataDao.getMockData();
        return MockDataBuilder.getInstance().convert(logList, logIndexList);
    }
}
