package org.manageyourlog.facade.service;

import org.manageyourlog.common.constants.Error;
import org.manageyourlog.facade.UploadLog;
import org.manageyourlog.facade.config.ApplicationConfig;
import org.manageyourlog.facade.config.ApplicationConfigKey;
import org.manageyourlog.facade.http.HttpService;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.stereotype.Component;
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

    private String uploadSingleLogInterface = "/uploadSingleLog";

    private String uploadLogListInterface = "/uploadLogList";

    @Override
    public UploadLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        Optional<String> baseUrl = applicationConfig.get(ApplicationConfigKey.uploadLogServerUrl);
        if(baseUrl.isPresent()){
            String url = String.format("%s%s", baseUrl, uploadSingleLogInterface);
            return httpService.post(url, uploadLogRecordReq, UploadLogResp.class);
        }
        return new UploadLogResp<>(Error.uploadUrlMiss);
    }

    @Override
    public UploadLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        Optional<String> baseUrl = applicationConfig.get(ApplicationConfigKey.uploadLogServerUrl);
        if(baseUrl.isPresent()){
            String url = String.format("%s%s", baseUrl, uploadLogListInterface);
            return httpService.post(url, uploadLogRecordReqs, UploadLogResp.class);
        }
        return new UploadLogResp<>(Error.uploadUrlMiss);
    }

}
