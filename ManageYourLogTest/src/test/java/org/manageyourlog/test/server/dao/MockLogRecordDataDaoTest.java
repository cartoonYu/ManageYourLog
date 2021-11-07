package org.manageyourlog.test.server.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.manageyourlog.server.mock.LogRecordMockEntity;
import org.manageyourlog.server.mock.MockLogRecordDataDao;
import org.manageyourlog.test.base.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author cartoon
 * @date 2021/10/25 00:35
 */
public class MockLogRecordDataDaoTest extends BaseTest {

    @Autowired
    private MockLogRecordDataDao mockLogRecordDataDao;

    @Test
    public void testMockLogRecordData(){
        List<LogRecordMockEntity> logRecords = mockLogRecordDataDao.getMockData();
        Assertions.assertNotNull(logRecords);
    }
}