package org.manage.log.receive.access.layer.http;

import org.manage.log.common.util.factory.LoadBean;
import org.manage.log.receive.service.ReceiveLog;
import org.manage.log.upload.model.req.UploadLogRecordReq;
import org.manage.log.upload.model.resp.OperateLogResp;
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
@LoadBean(loadConfigKey = "receive.log.load.mode", mode = "http",  needPrimary = false)
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
