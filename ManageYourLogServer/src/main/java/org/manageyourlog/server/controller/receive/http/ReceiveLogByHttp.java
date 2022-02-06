package org.manageyourlog.server.controller.receive.http;

import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.OperateLogResp;
import org.manageyourlog.server.controller.receive.ReceiveLogLoadCondition;
import org.manageyourlog.server.controller.receive.ReceiveLogMode;
import org.manageyourlog.server.service.receive.ReceiveLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * receive log by sync http call
 * @author cartoon
 * @date 2021/10/31 17:06
 */
@RestController
@RequestMapping("/receive")
@ReceiveLogLoadCondition(mode = ReceiveLogMode.http)
public class ReceiveLogByHttp {

    @Autowired
    @Qualifier("syncReceiveLog")
    private ReceiveLog syncReceiveLog;

    @PostMapping("/singleLog")
    public OperateLogResp<Boolean> uploadSingleLog(@RequestBody UploadLogRecordReq req){
        return syncReceiveLog.receive(req);
    }

    @PostMapping("/logList")
    public OperateLogResp<Boolean> uploadLogList(@RequestBody List<UploadLogRecordReq> req){
        return syncReceiveLog.receive(req);
    }
}
