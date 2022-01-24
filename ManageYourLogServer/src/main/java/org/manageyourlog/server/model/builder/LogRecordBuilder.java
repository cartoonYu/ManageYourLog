package org.manageyourlog.server.model.builder;

import org.manageyourlog.facade.model.req.UploadLogRecordIndexReq;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.server.model.LogRecord;
import org.manageyourlog.server.model.LogRecordIndex;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * @author cartoon
 * @date 2021/10/30 17:42
 */
public class LogRecordBuilder {

    private static final LogRecordBuilder INSTANCE = new LogRecordBuilder();

    public static LogRecordBuilder getInstance(){
        return INSTANCE;
    }

    public LogRecord convert(UploadLogRecordReq uploadLogRecordReq){
        return ofNullable(uploadLogRecordReq).map(req -> {
            LogRecord logRecord = new LogRecord();
            logRecord.setContent(req.getContent())
                    .setOperatorSort(req.getOperatorSort())
                    .setOperator(req.getOperator())
                    .setLogRecordSort(req.getLogRecordSort())
                    .setIndexList(convertIndex(req.getIndexList()));
            return logRecord;
        }).orElse(null);
    }

    private List<LogRecordIndex> convertIndex(List<UploadLogRecordIndexReq> indexReqList){
        return ofNullable(indexReqList)
                .map(indexList ->
                        indexList.stream()
                                .map(req -> {
                                    LogRecordIndex index = new LogRecordIndex();
                                    index.setLogRecordIndexSort(req.getLogRecordIndexSort())
                                            .setIndexValue(req.getIndexValue());
                                    return index;
                                })
                                .collect(Collectors.toList()))
                .orElse(null);
    }
}
