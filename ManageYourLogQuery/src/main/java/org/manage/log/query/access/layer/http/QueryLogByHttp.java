package org.manage.log.query.access.layer.http;

import org.manage.log.common.util.factory.LoadBean;
import org.manage.log.query.access.layer.http.builder.QueryResultBuilder;
import org.manage.log.query.access.layer.http.model.QueryLogResp;
import org.manage.log.common.util.LocalDateTimeFormatUtil;
import org.manage.log.query.service.QueryLogService;
import org.manage.log.common.model.LogRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
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

    @Autowired
    private QueryLogService queryLogService;

    private QueryResultBuilder queryResultBuilder;

    private LocalDateTimeFormatUtil localDateTimeFormatUtil;

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

    @PostConstruct
    private void init(){
        queryResultBuilder = QueryResultBuilder.getInstance();
        localDateTimeFormatUtil = LocalDateTimeFormatUtil.getInstance();
    }
}
