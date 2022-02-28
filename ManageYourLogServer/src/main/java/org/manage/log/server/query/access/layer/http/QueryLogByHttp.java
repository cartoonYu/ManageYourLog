package org.manage.log.server.query.access.layer.http;

import com.google.common.collect.ImmutableList;
import org.manage.log.server.query.access.layer.QueryLogMode;
import org.manage.log.server.query.access.layer.http.builder.QueryResultBuilder;
import org.manage.log.server.query.access.layer.http.model.QueryLogResp;
import org.manage.log.common.util.LocalDateTimeFormatUtil;
import org.manage.log.server.query.access.layer.QueryLogLoadCondition;
import org.manage.log.server.model.LogRecord;
import org.manage.log.server.query.service.QueryLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/01 23:14
 */
@RestController
@RequestMapping("/query")
@QueryLogLoadCondition(mode = QueryLogMode.http)
public class QueryLogByHttp {

    private static final Logger log = LoggerFactory.getLogger(QueryLogByHttp.class);

    @Autowired
    private QueryLogService queryLogService;

    private QueryResultBuilder queryResultBuilder;

    private LocalDateTimeFormatUtil localDateTimeFormatUtil;

    @GetMapping(value = "/queryByIndex")
    public List<QueryLogResp> queryByIndex(@RequestParam("index") String index){
        return Optional.ofNullable(index).map(value -> {
            List<LogRecord> res = queryLogService.getLogs(value);
            return queryResultBuilder.build(res);
        }).orElse(ImmutableList.of());
    }

    @GetMapping("/queryBetweenTime")
    public List<QueryLogResp> queryBetweenTime(@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime){
        if(Objects.isNull(startTime) || Objects.isNull(endTime)){
            log.warn("query log, query between time, param is illegal, startTime: {}, endTime: {}", startTime, endTime);
            return ImmutableList.of();
        }
        List<LogRecord> res = queryLogService.getLogs(localDateTimeFormatUtil.format(startTime), localDateTimeFormatUtil.format(endTime));
        return queryResultBuilder.build(res);
    }

    @GetMapping("/queryByIndexBetweenTime")
    public List<QueryLogResp> queryByIndexBetweenTime(@RequestParam("index") String index, @RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime){
        if(Objects.isNull(startTime) || Objects.isNull(endTime) || Objects.isNull(index)){
            log.warn("query log, query between time, param is illegal, startTime: {}, endTime: {}", startTime, endTime);
            return ImmutableList.of();
        }
        List<LogRecord> res = queryLogService.getLogs(index, localDateTimeFormatUtil.format(startTime), localDateTimeFormatUtil.format(endTime));
        return queryResultBuilder.build(res);
    }

    @PostConstruct
    private void init(){
        queryResultBuilder = QueryResultBuilder.getInstance();
        localDateTimeFormatUtil = LocalDateTimeFormatUtil.getInstance();
    }
}
