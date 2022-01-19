package org.manageyourlog.facade.service.rpc;

import org.apache.dubbo.config.annotation.DubboReference;
import org.manageyourlog.facade.UploadLog;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cartoon.yu
 * @date 2022/1/19 21:36
 */
@Service
public class UploadLogByRpc implements UploadLog {

    @DubboReference
    private UploadLog uploadLog;

    @Override
    public UploadLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        return uploadLog.upload(uploadLogRecordReq);
    }

    @Override
    public UploadLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        return uploadLog.upload(uploadLogRecordReqs);
    }
}
