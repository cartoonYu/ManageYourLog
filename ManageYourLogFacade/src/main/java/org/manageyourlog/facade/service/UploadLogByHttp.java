package org.manageyourlog.facade.service;

import org.manageyourlog.common.config.ApplicationConfig;
import org.manageyourlog.common.constants.Error;
import org.manageyourlog.common.util.http.HttpRegister;
import org.manageyourlog.facade.UploadLog;
import org.manageyourlog.facade.http.UploadLogInterface;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/03 09:02
 */
@Service
public class UploadLogByHttp implements UploadLog {

    @Autowired
    private ApplicationConfig applicationConfig;

    @Autowired
    private HttpRegister httpRegister;

    private UploadLogInterface uploadLogInterface;

    @Override
    public UploadLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        Call<UploadLogResp<Boolean>> res = uploadLogInterface.uploadSingleLog(uploadLogRecordReq);
        try {
            return res.execute().body();
        } catch (Exception e){
            log.error("upload log, upload log error", e);
            return new UploadLogResp<>(Error.uploadLogFail);
        }
    }

    @Override
    public UploadLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        Call<UploadLogResp<Boolean>> res = uploadLogInterface.uploadLogList(uploadLogRecordReqs);
        try {
            return res.execute().body();
        } catch (Exception e){
            log.error("upload log, upload log error", e);
            return new UploadLogResp<>(Error.uploadLogFail);
        }
    }

    @PostConstruct
    private void init(){
        Optional<String> baseUrl = applicationConfig.get(UploadLogMode.http.getBaseUrl());
        baseUrl.ifPresent(s -> uploadLogInterface = httpRegister.register(UploadLogInterface.class, s));
    }
}
