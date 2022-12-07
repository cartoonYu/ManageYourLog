package org.manage.log.receive.provider.util;

import com.google.common.collect.ImmutableList;
import org.manage.log.common.constants.LogRecordIndexSort;
import org.manage.log.common.constants.LogRecordSort;
import org.manage.log.common.constants.OperatorSort;
import org.manage.log.common.model.log.LogRecord;
import org.manage.log.common.model.log.LogRecordIndex;
import org.manage.log.common.util.IdGenerateUtil;
import org.manage.log.receive.facade.dto.UploadLogRecordReq;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/12/6 20:31
 */
public class DefineModelUtil {

    public static UploadLogRecordReq defineLogRecordReq(){
        UploadLogRecordReq uploadLogRecordReq = new UploadLogRecordReq();
        uploadLogRecordReq.setUploadTime(LocalDateTime.now());
        uploadLogRecordReq.setConfigName("test");
        //todo 具体值确定
        uploadLogRecordReq.setValueList(ImmutableList.of());
        return uploadLogRecordReq;
    }

    public static LogRecord defineLogRecord(){
        LogRecord record = new LogRecord();
        String recordId = IdGenerateUtil.getInstance().generate(13);
        record.setRecordId(recordId)
                .setContent("111")
                .setOperatorSort(OperatorSort.USER)
                .setOperator("cartoon")
                .setLogRecordSort(LogRecordSort.OPERATE)
                .addIndexList(ImmutableList.of(defineLogRecordIndex(recordId)))
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
