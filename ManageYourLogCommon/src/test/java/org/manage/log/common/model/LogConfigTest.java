package org.manage.log.common.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manage.log.common.MockData;
import org.manage.log.common.model.config.LogConfig;

/**
 * @author cartoon
 * @since 2022/10/24 16:36
 */
@DisplayName("log config test")
public class LogConfigTest {

    @DisplayName("[normal] test getter method return not null")
    @Test
    public void testGetterReturnNotNull(){
        LogConfig logConfig = MockData.initLogConfig();
        Assertions.assertNotNull(logConfig.getRuleId());
        Assertions.assertNotNull(logConfig.getRuleName());
        Assertions.assertNotNull(logConfig.getLogRecordSort());
        Assertions.assertNotNull(logConfig.getOperatorSort());
        Assertions.assertNotNull(logConfig.getIndexSort());
        Assertions.assertNotNull(logConfig.getDescription());
        Assertions.assertNotNull(logConfig.getVersion());
        Assertions.assertNotNull(logConfig.getCreateTime());
        Assertions.assertNotNull(logConfig.getModifyTime());
    }
}
