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
        Assertions.assertNotNull(logRecord.recordId());
        Assertions.assertNotNull(logRecord.content());
        Assertions.assertNotNull(logRecord.operatorSort());
        Assertions.assertNotNull(logRecord.operator());
        Assertions.assertNotNull(logRecord.logRecordSort());
        Assertions.assertNotNull(logRecord.indexList());
        Assertions.assertNotNull(logRecord.version());
        Assertions.assertNotNull(logRecord.createTime());
        Assertions.assertNotNull(logRecord.modifyTime());
    }
}
