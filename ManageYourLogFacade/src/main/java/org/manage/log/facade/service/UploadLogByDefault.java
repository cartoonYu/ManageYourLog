package org.manage.log.facade.service;

import org.manage.log.common.util.GsonUtil;
import org.manage.log.facade.UploadLog;
import org.manage.log.facade.model.req.UploadLogRecordReq;
import org.manage.log.facade.model.resp.OperateLogResp;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cartoon
 * @date 2021/12/3 20:04
 */
@Service
public class UploadLogByDefault implements UploadLog {

    @Override
    public OperateLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        log.info("actual upload log by default, upload single log, data: {}", GsonUtil.getInstance().writeJson(uploadLogRecordReq));
        return new OperateLogResp<>(true);
    }

    @Override
    public OperateLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        log.info("actual upload log by default, upload log list, data: {}", GsonUtil.getInstance().writeJson(uploadLogRecordReqs));
        return new OperateLogResp<>(true);
    }
}
