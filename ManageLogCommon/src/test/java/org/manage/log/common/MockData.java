package org.manage.log.common;

import com.google.common.collect.ImmutableList;
import org.manage.log.common.constants.LogRecordIndexSort;
import org.manage.log.common.constants.LogRecordSort;
import org.manage.log.common.constants.OperatorSort;
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
        LogConfig logConfig = new LogConfig();
        logConfig.setRuleId("111")
                .setRuleName("test")
                .setLogRecordSort(LogRecordSort.DEFAULT)
                .setOperatorSort(OperatorSort.DEFAULT)
                .setContentTemplate("test")
                .addIndexConfig(defineLogIndexConfig())
                .setDescription("test")
                .setVersion(1L)
                .setCreateTime(LocalDateTime.now())
                .setModifyTime(LocalDateTime.now());
        return logConfig;
    }

    private static LogIndexConfig defineLogIndexConfig(){
        LogIndexConfig logIndexConfig = new LogIndexConfig();
        logIndexConfig.setRuleId(IdGenerateUtil.getInstance().generate(13))
                .setRuleName("test rule" + IdGenerateUtil.getInstance().generate(100))
                .setLogRecordIndexSort(LogRecordIndexSort.ID)
                .setValueIndexKey("test")
                .setDescription("test")
                .setVersion(1L)
                .setCreateTime(LocalDateTime.now())
                .setModifyTime(LocalDateTime.now());
        return logIndexConfig;
    }

    public static LogRecord initLogRecord(){
        LogRecord logRecord = new LogRecord();
        logRecord.setRecordId("111")
                .setContent("test")
                .setOperatorSort(OperatorSort.DEFAULT)
                .setOperator("test")
                .setLogRecordSort(LogRecordSort.DEFAULT)
                .addIndexList(ImmutableList.of(initLogRecordIndex()))
                .setVersion(1)
                .setCreateTime(LocalDateTimeFormatUtil.getInstance().format("2022-10-23 00:00:00"))
                .setModifyTime(LocalDateTimeFormatUtil.getInstance().format("2022-10-23 00:00:00"));
        return logRecord;
    }

    public static LogRecordIndex initLogRecordIndex(){
        LogRecordIndex logRecordIndex = new LogRecordIndex();
        logRecordIndex.setIndexId("111")
                .setLogRecordId("111")
                .setLogRecordIndexSort(LogRecordIndexSort.ID)
                .setIndexValue("111")
                .setVersion(1)
                .setCreateTime(LocalDateTimeFormatUtil.getInstance().format("2022-10-23 00:00:00"))
                .setModifyTime(LocalDateTimeFormatUtil.getInstance().format("2022-10-23 00:00:00"));
        return logRecordIndex;
    }
}
