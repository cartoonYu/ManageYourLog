package org.manage.log.receive.provider.access.layer.builder;


import org.manage.log.common.model.log.LogRecord;
import org.manage.log.common.model.log.LogRecordIndex;
import org.manage.log.common.util.IdGenerateUtil;
import org.manage.log.receive.facade.dto.UploadLogRecordReq;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cartoon
 * @date 2021/10/30 17:42
 */
public class LogRecordBuilder {

    private static final LogRecordBuilder INSTANCE = new LogRecordBuilder();

    public static LogRecordBuilder getInstance(){
        return INSTANCE;
    }

    public LogRecord build(UploadLogRecordReq uploadLogRecordReq, LocalDateTime uploadTime){
        /*String recordId = IdGenerateUtil.getInstance().generate(13);
        LogRecord logRecord = new LogRecord();
        logRecord.setRecordId(recordId)
                .setContent(uploadLogRecordReq.getContent())
                .setOperatorSort(OperatorSort.parse(uploadLogRecordReq.getOperatorSort()))
                .setOperator(uploadLogRecordReq.getOperator())
                .setLogRecordSort(uploadLogRecordReq.getLogRecordSort())
                .addIndexList(convertIndex(recordId, uploadTime, uploadLogRecordReq.getIndexList()))
                .setVersion(1)
                .setCreateTime(uploadTime)
                .setModifyTime(uploadTime);
        return logRecord;*/
        return new LogRecord();
    }


}
