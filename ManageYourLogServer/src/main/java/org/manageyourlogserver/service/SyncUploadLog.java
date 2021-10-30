package org.manageyourlogserver.service;

import org.manageyourlogfacade.UploadLog;
import org.manageyourlogfacade.model.req.LogRecordIndexReq;
import org.manageyourlogfacade.model.req.LogRecordReq;
import org.manageyourlogfacade.model.resp.UploadLogResp;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cartoon
 * @date 2021/10/30 17:38
 */
@Service
public class SyncUploadLog implements UploadLog {

    @Override
    public UploadLogResp<Boolean> upload(LogRecordReq logRecordReq) {
        return null;
    }

    @Override
    public UploadLogResp<Boolean> upload(List<LogRecordIndexReq> logRecordIndexReqs) {
        return null;
    }
}
