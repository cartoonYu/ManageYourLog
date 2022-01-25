package org.manageyourlog.server.model.builder;

import org.manageyourlog.common.util.IdGenerateUtil;
import org.manageyourlog.facade.model.req.UploadLogRecordIndexReq;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.server.model.LogRecord;
import org.manageyourlog.server.model.LogRecordIndex;

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
        String recordId = IdGenerateUtil.getInstance().generate(13);
        LogRecord logRecord = new LogRecord();
        logRecord.setRecordId(recordId)
                .setContent(uploadLogRecordReq.getContent())
                .setOperatorSort(uploadLogRecordReq.getOperatorSort())
                .setOperator(uploadLogRecordReq.getOperator())
                .setLogRecordSort(uploadLogRecordReq.getLogRecordSort())
                .setIndexList(convertIndex(recordId, uploadTime, uploadLogRecordReq.getIndexList()))
                .setVersion(1)
                .setCreateTime(uploadTime)
                .setModifyTime(uploadTime);
        return logRecord;
    }

    private List<LogRecordIndex> convertIndex(String recordId, LocalDateTime logTime, List<UploadLogRecordIndexReq> indexReqList){
        return indexReqList.stream().map(index -> {
            LogRecordIndex res = new LogRecordIndex();
            res.setIndexId(IdGenerateUtil.getInstance().generate(13))
                    .setLogRecordId(recordId)
                    .setLogRecordIndexSort(index.getLogRecordIndexSort())
                    .setIndexValue(index.getIndexValue())
                    .setVersion(1)
                    .setCreateTime(logTime)
                    .setModifyTime(logTime);
            return res;
        }).collect(Collectors.toList());
    }
}
