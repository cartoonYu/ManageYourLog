package org.manage.log.receive.provider.repository;

import com.google.common.collect.ImmutableList;
import org.apache.ibatis.exceptions.PersistenceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.manage.log.base.test.BaseTest;
import org.manage.log.common.model.log.LogRecord;
import org.manage.log.common.model.log.LogRecordIndex;
import org.manage.log.common.util.IdGenerateUtil;
import org.manage.log.receive.provider.util.DefineModelUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/12/6 20:27
 */
@DisplayName("log repository test")
public class LogRecordRepositoryTest extends BaseTest {

    @Autowired
    private List<LogRecordRepository> logRecordRepositoryList;

    @Order(1)
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
    @Order(2)
    @Test
    public void testRollback(){
        String recordId = IdGenerateUtil.getInstance().generate(13);
        List<LogRecordIndex> indexList = ImmutableList.of(DefineModelUtil.defineLogRecordIndex(recordId, "2222"));
        LogRecord logRecord = DefineModelUtil.defineLogRecord(recordId, indexList);

        Assertions.assertTrue(logRecordRepository.save(ImmutableList.of(logRecord)));
        Assertions.assertFalse(logRecordRepository.save(ImmutableList.of(logRecord)));
    }
}