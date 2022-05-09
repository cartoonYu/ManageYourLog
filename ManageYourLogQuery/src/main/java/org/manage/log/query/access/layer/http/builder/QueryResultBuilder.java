package org.manage.log.query.access.layer.http.builder;

import org.manage.log.query.access.layer.http.model.QueryLogResp;
import org.manage.log.common.util.LocalDateTimeFormatUtil;
import org.manage.log.query.access.layer.http.model.QueryLogIndexResp;
import org.manage.log.repository.model.LogRecord;
import org.manage.log.repository.model.LogRecordIndex;

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
