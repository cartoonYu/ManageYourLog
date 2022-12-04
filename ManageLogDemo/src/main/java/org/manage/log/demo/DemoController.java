package org.manage.log.demo;

import org.manage.log.common.util.GsonUtil;
import org.manage.log.receive.facade.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cartoon
 * @date 2021/11/6 17:46
 */
@RestController
public class DemoController {

    private static final Logger log = LoggerFactory.getLogger(DemoController.class);

    @PostMapping("/query")
    @Log(ruleName = "orderOperate", paramIsObject = true)
    public String query(@RequestBody QueryReq queryReq){
        log.info("call query interface, req: {}", GsonUtil.getInstance().writeJson(queryReq));
        return "mock call query interface";
    }
}
