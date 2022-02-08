package org.manage.log.server.receive.access.layer.http;

import org.manage.log.server.receive.access.layer.ReceiveLogMode;
import org.manage.log.server.receive.service.ReceiveLog;
import org.manage.log.facade.model.req.UploadLogRecordReq;
import org.manage.log.facade.model.resp.OperateLogResp;
import org.manage.log.server.receive.access.layer.ReceiveLogLoadCondition;
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
