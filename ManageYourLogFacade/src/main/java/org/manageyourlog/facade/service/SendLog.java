package org.manageyourlog.facade.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.manageyourlog.facade.TransferLog;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/29 22:58
 */
@Service
public class SendLog implements TransferLog {

    protected final Logger log = LoggerFactory.getLogger(SendLog.class);

    @Override
    public UploadLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        log.debug("actual upload log by default, upload single log, data: {}", JSONObject.toJSONString(uploadLogRecordReq));
        return new UploadLogResp<>(true);
    }

    @Override
    public UploadLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        log.debug("actual upload log by default, upload log list, data: {}", JSONArray.toJSONString(uploadLogRecordReqs));
        return new UploadLogResp<>(true);
    }
}
