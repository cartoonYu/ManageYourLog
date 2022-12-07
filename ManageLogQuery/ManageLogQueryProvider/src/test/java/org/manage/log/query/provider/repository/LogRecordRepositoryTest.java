package org.manage.log.query.provider.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manage.log.base.test.BaseTest;
import org.manage.log.common.model.log.LogRecord;
import org.manage.log.common.util.LocalDateTimeFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author cartoon
 * @date 2021/12/11 23:25
 */
@DisplayName("log repository test")
public class LogRecordRepositoryTest extends BaseTest {

    private static String indexValue = "111";

    private static LocalDateTime baseTime = LocalDateTimeFormatUtil.getInstance().format("2021-10-26 00:00:00");

    @Autowired
    private List<LogRecordRepository> logRecordRepositoryList;

    @DisplayName("get log by index test")
    @Test
    public void getByIndex(){
        logRecordRepositoryList.forEach(logRecordRepository -> {
            List<LogRecord> logRecords = logRecordRepository.getByIndex(indexValue);
            Assertions.assertFalse(logRecords.isEmpty());
        });
    }

    @DisplayName("get log by time test")
    @Test
    public void testGetByTime(){
        logRecordRepositoryList.forEach(logRecordRepository -> {
            List<LogRecord> logRecords = logRecordRepository.getByTime(baseTime.plusSeconds(-1), baseTime);
            Assertions.assertFalse(logRecords.isEmpty());
        });
    }

    @DisplayName("get log by index and time test")
    @Test
    public void testGetByIndexAndTime(){
        logRecordRepositoryList.forEach(logRecordRepository -> {
            List<LogRecord> logRecords = logRecordRepository.getByIndexAndTime(indexValue, baseTime.plusSeconds(-1), baseTime.plusSeconds(1));
            Assertions.assertFalse(logRecords.isEmpty());
        });
    }
}
