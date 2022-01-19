package org.manageyourlog.server.controller.receive.rpc;

import org.apache.dubbo.config.annotation.DubboService;
import org.manageyourlog.facade.UploadLog;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.manageyourlog.server.service.receive.ReceiveLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cartoon
 * @date 2022/1/16 17:07
 */
@Conditional(ReceiveLogByRpcCondition.class)
@DubboService
@Service
public class ReceiveLogByRpc implements UploadLog {

    @Autowired
    @Qualifier("syncReceiveLog")
    private ReceiveLog syncReceiveLog;

    @Override
    public UploadLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        return syncReceiveLog.receive(uploadLogRecordReq);
    }

    @Override
    public UploadLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        return syncReceiveLog.receive(uploadLogRecordReqs);
    }
}
