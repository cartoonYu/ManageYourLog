package org.manage.log.receive.provider.access.layer.rpc;

import org.apache.dubbo.config.annotation.DubboService;
import org.manage.log.common.util.factory.LoadBean;
import org.manage.log.receive.facade.UploadLog;
import org.manage.log.receive.facade.dto.OperateLogResp;
import org.manage.log.receive.facade.dto.UploadLogRecordReq;
import org.manage.log.receive.provider.service.ReceiveLog;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * receive log by rpc
 * @author cartoon
 * @date 2022/1/16 17:07
 */
@DubboService
@Service
@LoadBean(loadConfigKey = "receive.log.load.mode", mode = "rpc")
public class ReceiveLogByRpc implements UploadLog {

    private final ReceiveLog syncReceiveLog;

    @Override
    public OperateLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        return syncReceiveLog.receive(uploadLogRecordReq);
    }

    @Override
    public OperateLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        return syncReceiveLog.receive(uploadLogRecordReqs);
    }

    public ReceiveLogByRpc(ReceiveLog syncReceiveLog) {
        this.syncReceiveLog = syncReceiveLog;
    }
}
