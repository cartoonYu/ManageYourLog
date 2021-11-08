package org.manageyourlog.demo;

import com.alibaba.fastjson.JSONObject;
import org.manageyourlog.common.constants.LogRecordIndexSort;
import org.manageyourlog.common.constants.LogRecordSort;
import org.manageyourlog.facade.annotation.UploadLogAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cartoon
 * @date 2021/11/6 17:46
 */
@RestController
public class DemoController {

    private static final Logger log = LoggerFactory.getLogger(DemoController.class);

    @PostMapping("/query")
    @UploadLogAnnotation(operatorSort = "user",
            operator = "#queryReq.userId",
            logRecordSort = LogRecordSort.Operate,
            logRecordIndexSort = {LogRecordIndexSort.Id},
            logRecordIndexSortValue = "#queryReq.orderId")
    public String query(QueryReq queryReq){
        log.info("call query interface, req: {}", JSONObject.toJSONString(queryReq));
        return "mock call query interface";
    }
}
