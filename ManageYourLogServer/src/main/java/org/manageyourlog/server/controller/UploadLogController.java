package org.manageyourlog.server.controller;

import org.manageyourlog.facade.TransferLog;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class UploadLogController {

    @Autowired
    @Qualifier("syncReceiveLog")
    private TransferLog syncReceiveLog;

    @PostMapping("/uploadSingleLog")
    public UploadLogResp<Boolean> uploadSingleLog(@RequestBody UploadLogRecordReq req){
        return syncReceiveLog.upload(req);
    }

    @PostMapping("/uploadLogList")
    public UploadLogResp<Boolean> uploadLogList(@RequestBody List<UploadLogRecordReq> req){
        return syncReceiveLog.upload(req);
    }
}
