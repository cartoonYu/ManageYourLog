package org.manage.log.query.provider.util;

import com.google.common.collect.ImmutableList;
import org.manage.log.common.model.log.constants.LogRecordIndexSort;
import org.manage.log.common.model.log.constants.LogRecordSort;
import org.manage.log.common.model.log.constants.OperatorSort;
import org.manage.log.common.model.log.LogRecord;
import org.manage.log.common.model.log.LogRecordIndex;
import org.manage.log.common.util.IdGenerateUtil;

import java.time.LocalDateTime;

/**
 * define model util
 * @author cartoon
 * @version 1.0
 * @since 2021/10/07 19:43
 */
public class DefineModelUtil {

    public static LogRecord defineLogRecord(){
        String recordId = IdGenerateUtil.getInstance().generate(13);
        return new LogRecord(
                recordId,
                "111",
                OperatorSort.USER,
                "cartoon",
                LogRecordSort.OPERATE,
                ImmutableList.of(defineLogRecordIndex()),
                1,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    private static LogRecordIndex defineLogRecordIndex(){
        return new LogRecordIndex(
                IdGenerateUtil.getInstance().generate(13),
                LogRecordIndexSort.ID,
                "111",
                1,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
