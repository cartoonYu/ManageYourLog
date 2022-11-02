package org.manage.log.common.model;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manage.log.common.MockData;

/**
 * @author cartoon.yu
 * @since 2022/10/31 15:41
 */
@DisplayName("log record index test")
public class LogRecordIndexTest {

    @DisplayName("[normal] test getter method return not null")
    @Test
    public void testGetterReturnNotNull(){
        LogRecordIndex logRecordIndex = MockData.initLogRecordIndex();
        Assertions.assertNotNull(logRecordIndex.getIndexId());
        Assertions.assertNotNull(logRecordIndex.getLogRecordId());
        Assertions.assertNotNull(logRecordIndex.getLogRecordIndexSort());
        Assertions.assertNotNull(logRecordIndex.getIndexValue());
        Assertions.assertNotNull(logRecordIndex.getVersion());
        Assertions.assertNotNull(logRecordIndex.getCreateTime());
        Assertions.assertNotNull(logRecordIndex.getModifyTime());
    }
}
