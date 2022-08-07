package org.manage.log.receive.access.layer.rpc;

import org.apache.dubbo.config.annotation.DubboService;
import org.manage.log.common.util.factory.LoadBean;
import org.manage.log.receive.service.ReceiveLog;
import org.manage.log.upload.UploadLog;
import org.manage.log.upload.model.req.UploadLogRecordReq;
import org.manage.log.upload.model.resp.OperateLogResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * receive log by rpc
 * @author cartoon
 * @date 2022/1/16 17:07
 */
@DubboService
@Service
@LoadBean(loadConfigKey = "receive.log.load.mode", mode = "rpc", defaultClass = ReceiveLogByRpc.class, implementClass = ReceiveLogByRpc.class, needPrimary = false)
public class ReceiveLogByRpc implements UploadLog {

    @Autowired
    @Qualifier("syncReceiveLog")
    private ReceiveLog syncReceiveLog;

    @Override
    public OperateLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        return syncReceiveLog.receive(uploadLogRecordReq);
    }

    @Override
    public OperateLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        return syncReceiveLog.receive(uploadLogRecordReqs);
    }
}
