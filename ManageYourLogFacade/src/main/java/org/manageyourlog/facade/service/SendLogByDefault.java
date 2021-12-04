package org.manageyourlog.facade.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.manageyourlog.facade.UploadLog;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cartoon
 * @date 2021/12/3 20:04
 */
@Service
public class SendLogByDefault implements UploadLog {

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
