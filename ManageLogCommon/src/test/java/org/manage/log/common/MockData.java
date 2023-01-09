package org.manage.log.common;

import com.google.common.collect.ImmutableList;
import org.manage.log.common.model.config.LogContentFormatConfig;
import org.manage.log.common.model.config.constants.LogContentFormatType;
import org.manage.log.common.model.log.constants.LogRecordIndexSort;
import org.manage.log.common.model.log.constants.LogRecordSort;
import org.manage.log.common.model.log.constants.OperatorSort;
import org.manage.log.common.model.config.LogConfig;
import org.manage.log.common.model.config.LogIndexConfig;
import org.manage.log.common.model.log.LogRecord;
import org.manage.log.common.model.log.LogRecordIndex;
import org.manage.log.common.util.IdGenerateUtil;
import org.manage.log.common.util.LocalDateTimeFormatUtil;

import java.time.LocalDateTime;


/**
 * @author cartoon
 * @since 2022/10/23 18:52
 */
public class MockData {

    public static LogConfig initLogConfig(){
        LogConfig logConfig = new LogConfig(
                "111",
                "test",
                LogRecordSort.DEFAULT,
                OperatorSort.DEFAULT,
                "test",
                ImmutableList.of(defineLogIndexConfig()),
                ImmutableList.of(defineContentFormatConfig()),
                "test",
                1L,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        return logConfig;
    }

    private static LogContentFormatConfig defineContentFormatConfig(){
        return new LogContentFormatConfig(
                IdGenerateUtil.getInstance().generate(13),
                "test rule" + IdGenerateUtil.getInstance().generate(100),
                LogContentFormatType.REGULAR_EXPRESSION_MATCH,
                "test",
                0L,
                1L,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    private static LogIndexConfig defineLogIndexConfig(){
        return new LogIndexConfig(
                IdGenerateUtil.getInstance().generate(13),
                "test rule" + IdGenerateUtil.getInstance().generate(100),
                LogRecordIndexSort.ID,
                "test",
                "test",
                1L,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public static LogRecord initLogRecord(){
        LogRecord logRecord = new LogRecord(
                "111",
                "test",
                OperatorSort.DEFAULT,
                "test",
                LogRecordSort.DEFAULT,
                ImmutableList.of(initLogRecordIndex()),
                1,
                LocalDateTimeFormatUtil.getInstance().format("2022-10-23 00:00:00"),
                LocalDateTimeFormatUtil.getInstance().format("2022-10-23 00:00:00")
        );
        return logRecord;
    }

    public static LogRecordIndex initLogRecordIndex(){
        return new LogRecordIndex(
                "111",
                "111",
                LogRecordIndexSort.ID,
                "111",
                1,
                LocalDateTimeFormatUtil.getInstance().format("2022-10-23 00:00:00"),
                LocalDateTimeFormatUtil.getInstance().format("2022-10-23 00:00:00")
        );
    }
}
