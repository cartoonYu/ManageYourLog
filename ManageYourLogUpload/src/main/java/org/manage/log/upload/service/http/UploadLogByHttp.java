package org.manage.log.upload.service.http;

import org.manage.log.common.constants.HandleError;
import org.manage.log.common.util.http.HttpRegisterUtil;
import org.manage.log.upload.UploadLog;
import org.manage.log.upload.model.req.UploadLogRecordReq;
import org.manage.log.upload.model.resp.OperateLogResp;
import org.manage.log.upload.service.factory.UploadLogLoadCondition;
import org.manage.log.upload.service.factory.UploadLogMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/03 09:02
 */
@Service
@UploadLogLoadCondition(mode = UploadLogMode.http)
public class UploadLogByHttp implements UploadLog {

    @Autowired
    private UploadLogByHttpConfig uploadLogByHttpConfig;

    private UploadLogInterface uploadLogInterface;

    @Override
    public OperateLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        Call<OperateLogResp<Boolean>> res = uploadLogInterface.uploadSingleLog(uploadLogRecordReq);
        try {
            return res.execute().body();
        } catch (Exception e){
            log.error("upload log, upload log error", e);
            return new OperateLogResp<>(HandleError.UPLOAD_LOG_FAIL);
        }
    }

    @Override
    public OperateLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        Call<OperateLogResp<Boolean>> res = uploadLogInterface.uploadLogList(uploadLogRecordReqs);
        try {
            return res.execute().body();
        } catch (Exception e){
            log.error("upload log, upload log error", e);
            return new OperateLogResp<>(HandleError.UPLOAD_LOG_FAIL);
        }
    }

    @PostConstruct
    private void init(){
        String baseUrl = uploadLogByHttpConfig.getBaseUrl();
        uploadLogInterface = HttpRegisterUtil.getInstance().register(UploadLogInterface.class, baseUrl);
    }
}
