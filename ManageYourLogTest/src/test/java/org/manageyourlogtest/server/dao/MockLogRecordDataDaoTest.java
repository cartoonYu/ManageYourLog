package org.manageyourlogtest.server.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.manageyourlogserver.dao.mock.LogRecordMockEntity;
import org.manageyourlogserver.dao.mock.MockLogRecordDataDao;
import org.manageyourlogtest.base.BaseTest;
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