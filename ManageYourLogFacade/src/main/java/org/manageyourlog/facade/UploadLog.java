package org.manageyourlog.facade;

import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author cartoon
 * @date 2021/11/14 23:26
 */
public interface UploadLog {

    Logger log = LoggerFactory.getLogger(UploadLog.class);

    UploadLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq);

    UploadLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs);
}
