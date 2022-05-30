package org.manage.log.receive.util;

import com.google.common.collect.ImmutableList;
import org.manage.log.common.constants.LogRecordIndexSort;
import org.manage.log.common.constants.LogRecordSort;
import org.manage.log.common.model.LogRecord;
import org.manage.log.common.model.LogRecordIndex;
import org.manage.log.common.util.IdGenerateUtil;
import org.manage.log.upload.model.req.UploadLogRecordIndexReq;
import org.manage.log.upload.model.req.UploadLogRecordReq;

import java.time.LocalDateTime;

/**
 * define model util
 * @author cartoon
 * @version 1.0
 * @since 2021/10/07 19:43
 */
public class DefineModelUtil {

    public static UploadLogRecordReq defineLogRecordReq(){
        UploadLogRecordIndexReq uploadLogRecordIndexReq = new UploadLogRecordIndexReq();
        uploadLogRecordIndexReq.setLogRecordIndexSort(LogRecordIndexSort.ID)
                .setIndexValue("111");
        UploadLogRecordReq uploadLogRecordReq = new UploadLogRecordReq();
        uploadLogRecordReq.setContent("111")
                .setOperatorSort("user")
                .setOperator("cartoon")
                .setLogRecordSort(LogRecordSort.OPERATE)
                .setIndexList(ImmutableList.of(uploadLogRecordIndexReq));
        return uploadLogRecordReq;
    }

    public static LogRecord defineLogRecord(){
        LogRecord record = new LogRecord();
        String recordId = IdGenerateUtil.getInstance().generate(13);
        record.setRecordId(recordId)
                .setContent("111")
                .setOperatorSort("user")
                .setOperator("cartoon")
                .setLogRecordSort(LogRecordSort.OPERATE)
                .setIndexList(ImmutableList.of(defineLogRecordIndex(recordId)))
                .setVersion(1)
                .setCreateTime(LocalDateTime.now())
                .setModifyTime(LocalDateTime.now());
        return record;
    }

    private static LogRecordIndex defineLogRecordIndex(String recordId){
        LogRecordIndex logRecordIndex = new LogRecordIndex();
        logRecordIndex.setIndexId(IdGenerateUtil.getInstance().generate(13))
                .setLogRecordId(recordId)
                .setLogRecordIndexSort(LogRecordIndexSort.ID)
                .setIndexValue("111")
                .setVersion(1)
                .setCreateTime(LocalDateTime.now())
                .setModifyTime(LocalDateTime.now());
        return logRecordIndex;
    }
}
