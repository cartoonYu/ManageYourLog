package org.manage.log.server.receive.access.layer.rpc;

import org.apache.dubbo.config.annotation.DubboService;
import org.manage.log.server.receive.access.layer.ReceiveLogMode;
import org.manage.log.server.receive.service.ReceiveLog;
import org.manage.log.facade.UploadLog;
import org.manage.log.facade.model.req.UploadLogRecordReq;
import org.manage.log.facade.model.resp.OperateLogResp;
import org.manage.log.server.receive.access.layer.ReceiveLogLoadCondition;
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
