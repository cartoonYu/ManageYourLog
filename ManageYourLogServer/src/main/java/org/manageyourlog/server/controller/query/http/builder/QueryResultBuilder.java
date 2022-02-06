package org.manageyourlog.server.controller.query.http.builder;

import org.manageyourlog.common.util.LocalDateTimeFormatUtil;
import org.manageyourlog.server.controller.query.http.model.QueryLogIndexResp;
import org.manageyourlog.server.controller.query.http.model.QueryLogResp;
import org.manageyourlog.server.model.LogRecord;
import org.manageyourlog.server.model.LogRecordIndex;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/02/06 16:06
 */
public class QueryResultBuilder {

    private static final QueryResultBuilder INSTANCE = new QueryResultBuilder();

    private LocalDateTimeFormatUtil timeFormatUtil;

    public static QueryResultBuilder getInstance(){
        return INSTANCE;
    }

    public List<QueryLogResp> build(List<LogRecord> sourceLog){
        return sourceLog.stream().map(source -> new QueryLogResp(source.getContent(), source.getOperatorSort(), source.getOperator(),
                                source.getLogRecordSort().getSortDescription(), buildIndex(source.getIndexList()),
                                source.getVersion(), timeFormatUtil.format(source.getCreateTime()), timeFormatUtil.format(source.getModifyTime()))).collect(Collectors.toList());
    }

    private List<QueryLogIndexResp> buildIndex(List<LogRecordIndex> sourceLogIndex){
        return sourceLogIndex.stream()
                .map(source -> new QueryLogIndexResp(
                        source.getLogRecordIndexSort().getSortDescription(), source.getIndexValue(),
                        source.getVersion(), timeFormatUtil.format(source.getCreateTime()), timeFormatUtil.format(source.getModifyTime())))
                .collect(Collectors.toList());
    }

    private QueryResultBuilder(){
        timeFormatUtil = LocalDateTimeFormatUtil.getInstance();
    }
}
