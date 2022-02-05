package org.manageyourlog.facade.service.rpc;

import org.apache.dubbo.config.annotation.DubboReference;
import org.manageyourlog.facade.UploadLog;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.OperateLogResp;
import org.manageyourlog.facade.service.factory.UploadLogLoadCondition;
import org.manageyourlog.facade.service.factory.UploadLogMode;
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
