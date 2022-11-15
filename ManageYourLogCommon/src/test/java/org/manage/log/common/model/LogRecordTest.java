package org.manage.log.common.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manage.log.common.MockData;
import org.manage.log.common.model.log.LogRecord;

/**
 * @author cartoon
 * @since 2022/10/24 16:36
 */
@DisplayName("log record test")
public class LogRecordTest {

    @DisplayName("[normal] test getter method return not null")
    @Test
    public void testGetterReturnNotNull(){
        LogRecord logRecord = MockData.initLogRecord();
        logRecord.addIndex(MockData.initLogRecordIndex());
        Assertions.assertNotNull(logRecord.getRecordId());
        Assertions.assertNotNull(logRecord.getContent());
        Assertions.assertNotNull(logRecord.getOperatorSort());
        Assertions.assertNotNull(logRecord.getOperator());
        Assertions.assertNotNull(logRecord.getLogRecordSort());
        Assertions.assertNotNull(logRecord.getIndexList());
        Assertions.assertNotNull(logRecord.getVersion());
        Assertions.assertNotNull(logRecord.getCreateTime());
        Assertions.assertNotNull(logRecord.getModifyTime());
    }

    @DisplayName("[abnormal] test add index is null")
    @Test
    public void testAddIndexIsNull(){
        LogRecord logRecord = MockData.initLogRecord();
        logRecord.addIndex(null);
        Assertions.assertEquals(1, logRecord.getIndexList().size());
    }

    @DisplayName("[abnormal] test add index list is null")
    @Test
    public void testAddIndexListIsNull(){
        LogRecord logRecord = MockData.initLogRecord();
        logRecord.addIndexList(null);
        Assertions.assertEquals(1, logRecord.getIndexList().size());
    }
}
