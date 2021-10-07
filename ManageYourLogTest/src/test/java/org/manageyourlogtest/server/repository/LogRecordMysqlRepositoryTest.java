package org.manageyourlogtest.server.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.manageyourlogserver.model.LogRecord;
import org.manageyourlogserver.repository.LogRecordRepository;
import org.manageyourlogtest.base.BaseTest;
import org.manageyourlogtest.util.DefineModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/08 00:03
 */
public class LogRecordMysqlRepositoryTest extends BaseTest {

    @Autowired
    @Qualifier("LogRecordMysqlRepository")
    private LogRecordRepository logRecordRepository;

    @Test
    public void testSave(){
        LogRecord logRecord = DefineModelUtil.defineLogRecord();
        Assertions.assertTrue(logRecordRepository.save(logRecord));
    }
}
