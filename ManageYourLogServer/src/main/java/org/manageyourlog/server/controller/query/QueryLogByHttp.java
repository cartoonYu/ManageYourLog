package org.manageyourlog.server.controller.query;

import org.manageyourlog.server.service.query.QueryLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/01 23:14
 */
@RestController
public class QueryLogByHttp {

    @Autowired
    private QueryLogService queryLogService;


}
