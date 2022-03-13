package org.manage.log.server.query.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manage.log.base.BaseTest;
import org.manage.log.common.util.LocalDateTimeFormatUtil;
import org.manage.log.server.model.LogRecord;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/03/05 17:19
 */
@DisplayName("query log service test")
public class QueryLogServiceTest extends BaseTest {

    @Autowired
    private QueryLogService queryLogService;

    private LocalDateTimeFormatUtil localDateTimeFormatUtil = LocalDateTimeFormatUtil.getInstance();

    @DisplayName("get log by index")
    @Test
    public void getLogByIndex(){
        List<LogRecord> logs = queryLogService.getLogs("111");
        Assertions.assertNotNull(logs);
        Assertions.assertEquals(1, logs.size());
    }

    @DisplayName("get log between time")
    @Test
    public void getLogBetweenTime(){
        List<LogRecord> logs = queryLogService.getLogs(localDateTimeFormatUtil.format("2021-10-26 00:00:00"), localDateTimeFormatUtil.format("2021-10-27 00:00:00"));
        Assertions.assertNotNull(logs);
        Assertions.assertEquals(1, logs.size());
    }

    @DisplayName("get log by index between time")
    @Test
    public void getLogByIndexBetweenTime(){
        List<LogRecord> logs = queryLogService.getLogs(
                "111",
                localDateTimeFormatUtil.format("2021-10-26 00:00:00"),
                localDateTimeFormatUtil.format("2021-10-27 00:00:00"));
        Assertions.assertNotNull(logs);
        Assertions.assertEquals(1, logs.size());
    }

    @DisplayName("get log between time without time")
    @Test
    public void getLogBetweenTimeWithoutTime(){
        List<LogRecord> logs = queryLogService.getLogs(null, null);
        Assertions.assertNotNull(logs);
        Assertions.assertEquals(0, logs.size());
    }

    @DisplayName("get log by index between time without time and index")
    @Test
    public void getLogByIndexBetweenTimeWithoutTimeAndIndex(){
        List<LogRecord> logs = queryLogService.getLogs(
                null,
                null,
                null);
        Assertions.assertNotNull(logs);
        Assertions.assertEquals(0, logs.size());
    }
}
