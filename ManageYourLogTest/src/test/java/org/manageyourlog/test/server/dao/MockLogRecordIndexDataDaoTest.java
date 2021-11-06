package org.manageyourlog.test.server.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.manageyourlog.server.mock.LogRecordIndexMockEntity;
import org.manageyourlog.server.mock.MockLogRecordIndexDao;
import org.manageyourlog.test.base.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/26 00:19
 */
public class MockLogRecordIndexDataDaoTest extends BaseTest {

    @Autowired
    private MockLogRecordIndexDao mockLogRecordIndexDao;

    @Test
    public void testGetData(){
        List<LogRecordIndexMockEntity> res = mockLogRecordIndexDao.getMockData();
        Assertions.assertNotNull(res);
    }
}
