package org.manage.log.receive.repository;

import com.google.common.collect.ImmutableList;
import org.apache.ibatis.exceptions.PersistenceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.manage.log.common.model.LogRecord;
import org.manage.log.receive.base.BaseTest;
import org.manage.log.receive.util.DefineModelUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author cartoon
 * @date 2021/12/11 23:25
 */
@DisplayName("log repository test")
public class LogRecordRepositoryTest extends BaseTest {

    @Autowired
    private List<LogRecordRepository> logRecordRepositoryList;

    @Order(1)
    @DisplayName("single log save test")
    @Test
    public void testSaveSingleLog(){
        logRecordRepositoryList.forEach(logRecordRepository -> {
            LogRecord logRecord = DefineModelUtil.defineLogRecord();
            Assertions.assertTrue(logRecordRepository.save(logRecord));
        });
    }

    @Order(2)
    @DisplayName("log list save test")
    @Test
    public void testSaveLogList(){
        logRecordRepositoryList.forEach(logRecordRepository -> {
            LogRecord logRecord = DefineModelUtil.defineLogRecord();
            Assertions.assertTrue(logRecordRepository.save(ImmutableList.of(logRecord)));
        });
    }

    @Autowired
    private LogRecordRepository logRecordRepository;

    @DisplayName("test rollback")
    @Order(4)
    @Test
    public void testRollback(){
        LogRecord logRecord = DefineModelUtil.defineLogRecord();
        logRecord.getIndexList().get(0).setIndexId("2222");
        logRecordRepository.save(logRecord);
        LogRecord logRecord1 = DefineModelUtil.defineLogRecord();
        logRecord1.getIndexList().get(0).setIndexId("2222");
        Assertions.assertThrows(PersistenceException.class, () -> logRecordRepository.save(logRecord1));
    }
}