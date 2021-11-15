package org.manageyourlog.test.server.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.manageyourlog.server.repository.DefaultLogRecordRepository;
import org.manageyourlog.server.repository.LogRecordRepository;
import org.manageyourlog.server.repository.RepositoryFactory;
import org.manageyourlog.test.base.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/16 00:10
 */
public class RepositoryFactoryTest extends BaseTest {

    @Autowired
    private RepositoryFactory repositoryFactory;

    @Test
    public void testDefault(){
        LogRecordRepository logRecordRepository = repositoryFactory.get();
        Assertions.assertTrue(logRecordRepository instanceof DefaultLogRecordRepository);
    }
}
