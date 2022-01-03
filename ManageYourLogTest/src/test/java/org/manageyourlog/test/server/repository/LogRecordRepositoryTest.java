package org.manageyourlog.test.server.repository;

import com.google.common.collect.ImmutableList;
import org.apache.ibatis.exceptions.PersistenceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.manageyourlog.server.model.LogRecord;
import org.manageyourlog.server.repository.LogRecordRepository;
import org.manageyourlog.test.base.BaseTest;
import org.manageyourlog.test.util.DefineModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author cartoon
 * @date 2021/12/11 23:25
 */
@DisplayName("log repository test")
public class LogRecordRepositoryTest extends BaseTest {

    private static String indexValue = "111";

    private List<LogRecordRepository> logRecordRepositoryList = getAllImplement(LogRecordRepository.class);

    @Order(1)
    @DisplayName("single log save test")
    @Test
    public void testSaveSingleLog(){
        LogRecord logRecord = DefineModelUtil.defineLogRecord();
        logRecordRepositoryList.forEach(logRecordRepository -> Assertions.assertTrue(logRecordRepository.save(logRecord)));
    }

    @Order(2)
    @DisplayName("log list save test")
    @Test
    public void testSaveLogList(){
        LogRecord logRecord = DefineModelUtil.defineLogRecord();
        logRecordRepositoryList.forEach(logRecordRepository -> Assertions.assertTrue(logRecordRepository.save(ImmutableList.of(logRecord))));
    }

    @Order(3)
    @DisplayName("get log by index test")
    @Test
    public void getByIndex(){
        logRecordRepositoryList.forEach(logRecordRepository -> {
            List<LogRecord> logRecords = logRecordRepository.getByIndex(indexValue);
            Assertions.assertFalse(logRecords.isEmpty());
        });
    }

    @DisplayName("get log by time test")
    @Order(3)
    @Test
    public void testGetByTime(){
        logRecordRepositoryList.forEach(logRecordRepository -> {
            List<LogRecord> logRecords = logRecordRepository.getByTime(LocalDateTime.now().plusSeconds(-1), LocalDateTime.now());
            Assertions.assertFalse(logRecords.isEmpty());
        });
    }

    @DisplayName("get log by index and time test")
    @Order(3)
    @Test
    public void testGetByIndexAndTime(){
        logRecordRepositoryList.forEach(logRecordRepository -> {
            List<LogRecord> logRecords = logRecordRepository.getByIndexAndTime(indexValue, LocalDateTime.now().plusSeconds(-1), LocalDateTime.now());
            Assertions.assertFalse(logRecords.isEmpty());
        });
    }

    @Autowired
    private LogRecordRepository logRecordRepository;

    @DisplayName("test rollback")
    @Order(4)
    @Test
    public void testRollback(){
        LogRecord logRecord = DefineModelUtil.defineLogRecord();
        logRecord.getIndexList().get(0).setIndexId("1111");
        logRecordRepository.save(logRecord);
        LogRecord logRecord1 = DefineModelUtil.defineLogRecord();
        logRecord1.getIndexList().get(0).setIndexId("1111");
        try {
            logRecordRepository.save(logRecord1);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("111111");
    }
}
