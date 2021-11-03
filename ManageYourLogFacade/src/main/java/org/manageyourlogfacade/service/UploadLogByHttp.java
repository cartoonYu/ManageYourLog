package org.manageyourlogfacade.service;

import org.manageyourlogfacade.UploadLog;
import org.manageyourlogfacade.model.req.UploadLogRecordReq;
import org.manageyourlogfacade.model.resp.UploadLogResp;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/03 09:02
 */
@Service
public class UploadLogByHttp implements UploadLog {


    @Override
    public UploadLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        return new UploadLogResp<>(true);
    }

    @Override
    public UploadLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        return new UploadLogResp<>(true);
    }
}
