package org.manageyourlog.demo;

import com.alibaba.fastjson.JSONObject;
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
    public String query(@RequestBody QueryReq queryReq){
        log.info("call query interface, req: {}", JSONObject.toJSONString(queryReq));
        return "mock call query interface";
    }
}
