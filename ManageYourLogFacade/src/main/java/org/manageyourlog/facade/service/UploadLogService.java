package org.manageyourlog.facade.service;

import org.manageyourlog.facade.UploadLog;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/03 08:57
 */
@Primary
@Service
public class UploadLogService implements UploadLog{

    @Autowired
    private UploadLogFactory uploadLogFactory;

    @Override
    public UploadLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        UploadLog uploadLog = uploadLogFactory.getActualUploadService();
        return uploadLog.upload(uploadLogRecordReq);
    }

    @Override
    public UploadLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        UploadLog uploadLog = uploadLogFactory.getActualUploadService();
        return uploadLog.upload(uploadLogRecordReqs);
    }
}
