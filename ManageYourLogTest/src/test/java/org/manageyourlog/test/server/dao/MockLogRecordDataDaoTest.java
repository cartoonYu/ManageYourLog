package org.manageyourlog.test.server.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manageyourlog.server.dao.mock.LogRecordMockEntity;
import org.manageyourlog.server.dao.mock.MockLogRecordDataDao;
import org.manageyourlog.test.base.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author cartoon
 * @date 2021/10/25 00:35
 */
@DisplayName("mock log dao test")
public class MockLogRecordDataDaoTest extends BaseTest {

    @Autowired
    private MockLogRecordDataDao mockLogRecordDataDao;

    @DisplayName("get mock data test")
    @Test
    public void testMockLogRecordData(){
        List<LogRecordMockEntity> logRecords = mockLogRecordDataDao.getMockData();
        Assertions.assertNotNull(logRecords);
    }
}
