package org.manageyourlog.facade.service.http;

import org.manageyourlog.common.constants.HandleError;
import org.manageyourlog.common.util.http.HttpRegisterUtil;
import org.manageyourlog.facade.UploadLog;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.manageyourlog.facade.service.factory.UploadLogLoadCondition;
import org.manageyourlog.facade.service.factory.UploadLogMode;
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
    public UploadLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        Call<UploadLogResp<Boolean>> res = uploadLogInterface.uploadSingleLog(uploadLogRecordReq);
        try {
            return res.execute().body();
        } catch (Exception e){
            log.error("upload log, upload log error", e);
            return new UploadLogResp<>(HandleError.UPLOAD_LOG_FAIL);
        }
    }

    @Override
    public UploadLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        Call<UploadLogResp<Boolean>> res = uploadLogInterface.uploadLogList(uploadLogRecordReqs);
        try {
            return res.execute().body();
        } catch (Exception e){
            log.error("upload log, upload log error", e);
            return new UploadLogResp<>(HandleError.UPLOAD_LOG_FAIL);
        }
    }

    @PostConstruct
    private void init(){
        String baseUrl = uploadLogByHttpConfig.getBaseUrl();
        uploadLogInterface = HttpRegisterUtil.getInstance().register(UploadLogInterface.class, baseUrl);
    }
}
