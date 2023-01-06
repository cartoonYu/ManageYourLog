package org.manage.log.query.provider.access.layer.http;

import org.manage.log.common.model.log.LogRecord;
import org.manage.log.common.util.LocalDateTimeFormatUtil;
import org.manage.log.common.util.factory.LoadBean;
import org.manage.log.query.facade.model.QueryLogResp;
import org.manage.log.query.provider.access.layer.builder.QueryResultBuilder;
import org.manage.log.query.provider.service.QueryLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/01 23:14
 */
@RestController
@RequestMapping("/query")
@LoadBean(primaryConfigKey = "query.log.load.mode", loadConfigKey = "query.log.load.mode", mode = "http")
public class QueryLogByHttp {

    private final QueryLogService queryLogService;

    private final QueryResultBuilder queryResultBuilder;

    private final LocalDateTimeFormatUtil localDateTimeFormatUtil;

    @GetMapping(value = "/queryByIndex")
    public List<QueryLogResp> queryByIndex(@RequestParam("index") String index){
        List<LogRecord> res = queryLogService.getLogs(index);
        return queryResultBuilder.build(res);
    }

    @GetMapping("/queryBetweenTime")
    public List<QueryLogResp> queryBetweenTime(@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime){
        List<LogRecord> res = queryLogService.getLogs(localDateTimeFormatUtil.format(startTime), localDateTimeFormatUtil.format(endTime));
        return queryResultBuilder.build(res);
    }

    @GetMapping("/queryByIndexBetweenTime")
    public List<QueryLogResp> queryByIndexBetweenTime(@RequestParam("index") String index, @RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime){
        List<LogRecord> res = queryLogService.getLogs(index, localDateTimeFormatUtil.format(startTime), localDateTimeFormatUtil.format(endTime));
        return queryResultBuilder.build(res);
    }

    public QueryLogByHttp(QueryLogService queryLogService) {
        this.queryLogService = queryLogService;
        this.queryResultBuilder = QueryResultBuilder.getInstance();
        this.localDateTimeFormatUtil = LocalDateTimeFormatUtil.getInstance();
    }
}
