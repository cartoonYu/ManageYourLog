package org.manage.log.receive.provider.util;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
import org.manage.log.receive.facade.dto.UploadLogRecordReq;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/12/6 20:31
 */
public class DefineModelUtil {

    public static UploadLogRecordReq defineLogRecordReq(){

        UploadLogRecordReq uploadLogRecordReq = new UploadLogRecordReq();
        uploadLogRecordReq.setUploadTime(LocalDateTime.now());
        uploadLogRecordReq.setConfigName("orderOperate");
        uploadLogRecordReq.setOperator("cartoon");
        uploadLogRecordReq.setValueData("{\"userId\":\"cartoon\"}");
        return uploadLogRecordReq;
    }

    public static LogRecord defineLogRecord(){
        String recordId = IdGenerateUtil.getInstance().generate(13);
        return defineLogRecord(recordId, ImmutableList.of(defineLogRecordIndex(recordId)));
    }

    public static LogRecord defineLogRecord(String recordId, List<LogRecordIndex> indexList){
        return new LogRecord(
                recordId,
                "111",
                OperatorSort.USER,
                "cartoon",
                LogRecordSort.OPERATE,
                indexList,
                1,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public static LogRecordIndex defineLogRecordIndex(String indexId){
        return new LogRecordIndex(
                indexId,
                LogRecordIndexSort.ID,
                "111",
                1,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public static LogConfig defineLogConfig(){
        return new LogConfig(
                IdGenerateUtil.getInstance().generate(13),
                "test rule" + IdGenerateUtil.getInstance().generate(100),
                LogRecordSort.DEFAULT,
                OperatorSort.DEFAULT,
                "test content template",
                ImmutableList.of(defineLogIndexConfig()),
                ImmutableList.of(defineContentFormatConfig()),
                "test",
                1L,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
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
}
