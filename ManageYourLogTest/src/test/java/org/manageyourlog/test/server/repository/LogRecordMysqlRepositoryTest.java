package org.manageyourlog.test.server.repository;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.manageyourlog.server.model.LogRecord;
import org.manageyourlog.server.repository.LogRecordRepository;
import org.manageyourlog.test.base.BaseTest;
import org.manageyourlog.test.util.DefineModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author cartoon
 * @date 2021/12/11 23:25
 */
public class LogRecordMysqlRepositoryTest extends BaseTest {

    private static String indexValue = "111";

    @Autowired
    @Qualifier("logRecordMysqlRepository")
    private LogRecordRepository logRecordRepository;

    @Order(1)
    @Test
    public void testSaveSingleLog(){
        LogRecord logRecord = DefineModelUtil.defineLogRecord();
        Assertions.assertTrue(logRecordRepository.save(logRecord));
    }

    @Order(2)
    @Test
    public void testSaveLogList(){
        LogRecord logRecord = DefineModelUtil.defineLogRecord();
        Assertions.assertTrue(logRecordRepository.save(ImmutableList.of(logRecord)));
    }

    @Order(3)
    @Test
    public void getByIndex(){
        List<LogRecord> logRecords = logRecordRepository.getByIndex(indexValue);
        Assertions.assertFalse(logRecords.isEmpty());
    }

    @Order(3)
    @Test
    public void testGetByTime(){
        List<LogRecord> logRecords = logRecordRepository.getByTime(LocalDateTime.now().plusSeconds(-1), LocalDateTime.now());
        Assertions.assertFalse(logRecords.isEmpty());
    }

    @Order(3)
    @Test
    public void testGetByIndexAndTime(){
        List<LogRecord> logRecords = logRecordRepository.getByIndexAndTime(indexValue, LocalDateTime.now().plusSeconds(-1), LocalDateTime.now());
        Assertions.assertFalse(logRecords.isEmpty());
    }
}
