package org.manage.log.receive.facade.service.rpc;

import org.apache.dubbo.config.annotation.DubboReference;
import org.manage.log.common.util.factory.LoadBean;
import org.manage.log.receive.facade.UploadLog;
import org.manage.log.receive.facade.dto.OperateLogResp;
import org.manage.log.receive.facade.dto.UploadLogRecordReq;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cartoon
 * @date 2022/1/19 21:36
 */
@Service
@LoadBean(primaryConfigKey = "upload.log.mode", loadConfigKey = "upload.log.mode", mode = "rpc")
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
