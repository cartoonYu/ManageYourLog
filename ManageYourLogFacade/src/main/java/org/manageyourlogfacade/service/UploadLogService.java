package org.manageyourlogfacade.service;

import org.manageyourlogfacade.UploadLog;
import org.manageyourlogfacade.model.req.UploadLogRecordReq;
import org.manageyourlogfacade.model.resp.UploadLogResp;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/03 08:57
 */
@Service("uploadLogServiceImpl")
public class UploadLogService implements UploadLog, ApplicationContextAware {

    @Autowired
    private Environment environment;

    private ApplicationContext applicationContext;

    @Override
    public UploadLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        UploadLog uploadLog = getSpecificService();
        return uploadLog.upload(uploadLogRecordReq);
    }

    @Override
    public UploadLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        UploadLog uploadLog = getSpecificService();
        return uploadLog.upload(uploadLogRecordReqs);
    }

    private UploadLog getSpecificService(){
        String uploadMode = environment.getProperty("upload.log.mode");
        UploadLogMode uploadLogMode = UploadLogMode.parse(uploadMode);
        return (UploadLog) applicationContext.getBean(uploadLogMode.getClassType());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
