package org.manage.log.common.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manage.log.common.MockData;
import org.manage.log.common.model.log.LogRecordIndex;

/**
 * @author cartoon
 * @since 2022/10/31 15:41
 */
@DisplayName("log record index test")
public class LogRecordIndexTest {

    @DisplayName("[normal] test getter method return not null")
    @Test
    public void testGetterReturnNotNull(){
        LogRecordIndex logRecordIndex = MockData.initLogRecordIndex();
        Assertions.assertNotNull(logRecordIndex.indexId());
        Assertions.assertNotNull(logRecordIndex.logRecordIndexSort());
        Assertions.assertNotNull(logRecordIndex.indexValue());
        Assertions.assertNotNull(logRecordIndex.version());
        Assertions.assertNotNull(logRecordIndex.createTime());
        Assertions.assertNotNull(logRecordIndex.modifyTime());
    }
}
