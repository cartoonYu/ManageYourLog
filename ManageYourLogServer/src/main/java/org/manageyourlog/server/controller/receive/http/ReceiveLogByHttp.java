package org.manageyourlog.server.controller.receive.http;

import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.manageyourlog.server.service.receive.ReceiveLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * upload log by sync http call
 * @author cartoon
 * @date 2021/10/31 17:06
 */
@RestController
@Conditional(ReceiveLogByHttpCondition.class)
public class ReceiveLogByHttp {

    @Autowired
    @Qualifier("syncReceiveLog")
    private ReceiveLog syncReceiveLog;

    @PostMapping("/receiveSingleLog")
    public UploadLogResp<Boolean> uploadSingleLog(@RequestBody UploadLogRecordReq req){
        return syncReceiveLog.receive(req);
    }

    @PostMapping("/receiveLogList")
    public UploadLogResp<Boolean> uploadLogList(@RequestBody List<UploadLogRecordReq> req){
        return syncReceiveLog.receive(req);
    }
}
