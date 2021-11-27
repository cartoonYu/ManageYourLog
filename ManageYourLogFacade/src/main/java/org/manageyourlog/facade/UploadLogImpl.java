package org.manageyourlog.facade;

import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.manageyourlog.facade.service.ActualUploadLog;
import org.manageyourlog.facade.service.UploadLogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author cartoon
 * @date 2021/11/14 23:27
 */
@Component
public class UploadLogImpl implements UploadLog{

    @Autowired
    private ActualUploadLog actualUploadLog;

    @Override
    public UploadLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        return actualUploadLog.upload(uploadLogRecordReq);
    }

    @Override
    public UploadLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        return actualUploadLog.upload(uploadLogRecordReqs);
    }
}
