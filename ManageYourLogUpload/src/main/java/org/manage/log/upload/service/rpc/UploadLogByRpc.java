package org.manage.log.upload.service.rpc;

import org.apache.dubbo.config.annotation.DubboReference;
import org.manage.log.upload.UploadLog;
import org.manage.log.upload.model.req.UploadLogRecordReq;
import org.manage.log.upload.model.resp.OperateLogResp;
import org.manage.log.upload.service.factory.UploadLogLoadCondition;
import org.manage.log.upload.service.factory.UploadLogMode;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cartoon
 * @date 2022/1/19 21:36
 */
@Service
@UploadLogLoadCondition(mode = UploadLogMode.rpc)
public class UploadLogByRpc implements UploadLog {

    @DubboReference
    private UploadLog uploadLog;

    @Override
    public OperateLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        return uploadLog.upload(uploadLogRecordReq);
    }

    @Override
    public OperateLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        return uploadLog.upload(uploadLogRecordReqs);
    }
}
