package org.manageyourlog.server.controller.receive.rpc;

import org.apache.dubbo.config.annotation.DubboService;
import org.manageyourlog.facade.UploadLog;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.OperateLogResp;
import org.manageyourlog.server.controller.receive.ReceiveLogLoadCondition;
import org.manageyourlog.server.controller.receive.ReceiveLogMode;
import org.manageyourlog.server.service.receive.ReceiveLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * receive log by rpc
 * @author cartoon
 * @date 2022/1/16 17:07
 */
@ReceiveLogLoadCondition(mode = ReceiveLogMode.rpc)
@DubboService
@Service
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
