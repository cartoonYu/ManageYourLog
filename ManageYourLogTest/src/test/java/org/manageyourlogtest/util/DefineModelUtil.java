package org.manageyourlogtest.util;

import com.google.common.collect.ImmutableList;
import org.manageyourlogcommon.constants.LogRecordIndexSort;
import org.manageyourlogcommon.constants.LogRecordSort;
import org.manageyourlogserver.model.LogRecord;
import org.manageyourlogserver.model.LogRecordIndex;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/07 19:43
 */
public class DefineModelUtil {


    public static LogRecord defineLogRecord(){
        LogRecord record = new LogRecord();
        record.setRecordId("111")
                .setContent("111")
                .setOperatorSort("user")
                .setOperator("cartoon")
                .setLogRecordSort(LogRecordSort.Operate)
                .setIndexList(ImmutableList.of(defineLogRecordIndex()))
                .setVersion(1)
                .setCreateTime(LocalDateTime.now())
                .setModifyTime(LocalDateTime.now());
        return record;
    }

    public static LogRecordIndex defineLogRecordIndex(){
        LogRecordIndex logRecordIndex = new LogRecordIndex();
        logRecordIndex.setIndexId("111")
                .setLogRecordId("111")
                .setLogRecordIndexSort(LogRecordIndexSort.Id)
                .setIndexValue("111")
                .setVersion(1)
                .setCreateTime(LocalDateTime.now())
                .setModifyTime(LocalDateTime.now());
        return logRecordIndex;
    }
}
