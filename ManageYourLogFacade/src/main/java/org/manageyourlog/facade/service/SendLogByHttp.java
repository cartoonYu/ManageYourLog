package org.manageyourlog.facade.service;

import org.manageyourlog.common.constants.Error;
import org.manageyourlog.facade.TransferLog;
import org.manageyourlog.facade.config.ApplicationConfig;
import org.manageyourlog.facade.http.HttpService;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/03 09:02
 */
@Service
public class SendLogByHttp extends SendLog {

    @Autowired
    private HttpService httpService;

    @Autowired
    private ApplicationConfig applicationConfig;

    private String uploadSingleLogInterface = "/uploadSingleLog";

    private String uploadLogListInterface = "/uploadLogList";

    @Override
    public UploadLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        return upload(uploadSingleLogInterface, uploadLogRecordReq);
    }

    @Override
    public UploadLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        return upload(uploadLogListInterface, uploadLogRecordReqs);
    }

    private <T> UploadLogResp<Boolean> upload(String interfaceName, T data){
        Optional<String> baseUrl = applicationConfig.get(SendLogMode.http.getBaseUrl());
        if(baseUrl.isPresent()){
            String url = String.format("%s%s", baseUrl.get(), interfaceName);
            return httpService.post(url, data, UploadLogResp.class);
        }
        return new UploadLogResp<>(Error.uploadUrlMiss);
    }
}
