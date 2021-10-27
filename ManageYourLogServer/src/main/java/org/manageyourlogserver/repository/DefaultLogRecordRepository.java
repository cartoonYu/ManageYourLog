package org.manageyourlogserver.repository;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.manageyourlogserver.converter.MockDataConverter;
import org.manageyourlogserver.dao.mock.LogRecordIndexMockEntity;
import org.manageyourlogserver.dao.mock.LogRecordMockEntity;
import org.manageyourlogserver.dao.mock.MockLogRecordDataDao;
import org.manageyourlogserver.dao.mock.MockLogRecordIndexDao;
import org.manageyourlogserver.model.LogRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cartoon.yu
 * @date 2021/10/23 17:27
 */
@Repository
public class DefaultLogRecordRepository implements LogRecordRepository{

    private static final Logger log = LoggerFactory.getLogger(DefaultLogRecordRepository.class);

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
}
