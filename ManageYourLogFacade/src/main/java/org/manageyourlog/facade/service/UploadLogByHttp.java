package org.manageyourlog.facade.service;

import org.manageyourlog.common.config.ApplicationConfig;
import org.manageyourlog.common.constants.Error;
import org.manageyourlog.facade.UploadLog;
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
public class UploadLogByHttp implements UploadLog {

    @Autowired
    private HttpService httpService;

    @Autowired
    private ApplicationConfig applicationConfig;

    private String uploadSingleLogInterface = "/receiveSingleLog";

    private String uploadLogListInterface = "/receiveLogList";

    @Override
    public UploadLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        return upload(uploadSingleLogInterface, uploadLogRecordReq);
    }

    @Override
    public UploadLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        return upload(uploadLogListInterface, uploadLogRecordReqs);
    }

    private <T> UploadLogResp<Boolean> upload(String interfaceName, T data){
        Optional<String> baseUrl = applicationConfig.get(UploadLogMode.http.getBaseUrl());
        if(baseUrl.isPresent()){
            String url = String.format("%s%s", baseUrl.get(), interfaceName);
            return httpService.post(url, data, UploadLogResp.class);
        }
        return new UploadLogResp<>(Error.uploadUrlMiss);
    }
}
