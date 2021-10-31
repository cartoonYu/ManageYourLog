package org.manageyourlogserver.converter.service;

import org.manageyourlogfacade.model.req.LogRecordIndexReq;
import org.manageyourlogfacade.model.req.LogRecordReq;
import org.manageyourlogserver.model.LogRecord;
import org.manageyourlogserver.model.LogRecordIndex;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * @author cartoon
 * @date 2021/10/30 17:42
 */
public class LogRecordConverter {

    public static List<LogRecord> convert(List<LogRecordReq> logRecordReqs){
        return ofNullable(logRecordReqs).map(reqList -> reqList.stream().map(LogRecordConverter::convert).collect(Collectors.toList())).orElse(null);
    }

    public static LogRecord convert(LogRecordReq logRecordReq){
        return ofNullable(logRecordReq).map(req -> {
            LogRecord logRecord = new LogRecord();
            logRecord.setContent(req.getContent())
                    .setOperatorSort(req.getOperatorSort())
                    .setOperator(req.getOperator())
                    .setLogRecordSort(req.getLogRecordSort())
                    .setIndexList(convertIndex(req.getIndexList()));
            return logRecord;
        }).orElse(null);
    }

    private static List<LogRecordIndex> convertIndex(List<LogRecordIndexReq> indexReqList){
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
