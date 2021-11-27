package org.manageyourlog.test.server.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.manageyourlog.server.repository.LogRecordMysqlRepository;
import org.manageyourlog.server.repository.LogRecordRepository;
import org.manageyourlog.test.base.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/16 00:10
 */
public class StoreRepositoryFactoryTest extends BaseTest {

    @Autowired
    private LogRecordRepository logRecordRepository;

    @Test
    public void testDefault(){
        Assertions.assertTrue(logRecordRepository instanceof LogRecordMysqlRepository);
    }
}
