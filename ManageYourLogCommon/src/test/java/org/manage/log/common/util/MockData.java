package org.manage.log.common.util;

import com.google.common.collect.ImmutableList;
import org.apache.commons.logging.Log;
import org.manage.log.common.constants.LogRecordIndexSort;
import org.manage.log.common.constants.LogRecordSort;
import org.manage.log.common.constants.OperatorSort;
import org.manage.log.common.model.LogRecord;
import org.manage.log.common.model.LogRecordIndex;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @since 2022/10/23 18:52
 */
public class MockData {

    public static LogRecord initLogRecord(){
        LogRecord logRecord = new LogRecord();
        logRecord.setRecordId("111")
                .setContent("test")
                .setOperatorSort(OperatorSort.DEFAULT)
                .setOperator("test")
                .setLogRecordSort(LogRecordSort.DEFAULT)
                .setIndexList(ImmutableList.of(initLogRecordIndex()))
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
