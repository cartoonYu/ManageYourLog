package org.manageyourlogserver.controller;

import org.manageyourlogfacade.UploadLog;
import org.manageyourlogfacade.model.req.UploadLogRecordReq;
import org.manageyourlogfacade.model.resp.UploadLogResp;
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
    @Qualifier("syncUploadLog")
    private UploadLog uploadLog;

    @PostMapping("/uploadSingleLog")
    public UploadLogResp<Boolean> uploadSingleLog(@RequestBody UploadLogRecordReq req){
        return uploadLog.upload(req);
    }

    @PostMapping("uploadLogList")
    public UploadLogResp<Boolean> uploadLogList(@RequestBody List<UploadLogRecordReq> req){
        return uploadLog.upload(req);
    }
}
