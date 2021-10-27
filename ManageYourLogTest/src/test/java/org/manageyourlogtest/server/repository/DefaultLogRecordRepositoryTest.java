package org.manageyourlogtest.server.repository;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.manageyourlogserver.model.LogRecord;
import org.manageyourlogserver.repository.LogRecordRepository;
import org.manageyourlogtest.base.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/27 09:37
 */
public class DefaultLogRecordRepositoryTest extends BaseTest {

    @Autowired
    @Qualifier("defaultLogRecordRepository")
    private LogRecordRepository logRecordRepository;

    @Test
    public void testSave(){
        Assertions.assertTrue(logRecordRepository.save(new LogRecord()));
    }


    @Test
    public void testSaveList(){
        Assertions.assertTrue(logRecordRepository.save(ImmutableList.of()));
    }

    @Test
    public void testGetByIndex(){
        Assertions.assertEquals(1, logRecordRepository.getByIndex("111").size());
    }
}
