package org.manage.log.receive.service;

import org.manage.log.upload.model.req.UploadLogRecordReq;
import org.manage.log.upload.model.resp.OperateLogResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author cartoon
 * @date 2021/12/3 20:15
 */
public interface ReceiveLog {

    Logger log = LoggerFactory.getLogger(ReceiveLog.class);

    OperateLogResp<Boolean> receive(UploadLogRecordReq uploadLogRecordReq);

    OperateLogResp<Boolean> receive(List<UploadLogRecordReq> uploadLogRecordReqs);
}
