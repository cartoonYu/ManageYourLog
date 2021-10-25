package org.manageyourlogtest.server.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.manageyourlogserver.dao.mock.LogRecordIndexMockEntity;
import org.manageyourlogserver.dao.mock.MockLogRecordIndexDao;
import org.manageyourlogtest.base.BaseTest;
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
