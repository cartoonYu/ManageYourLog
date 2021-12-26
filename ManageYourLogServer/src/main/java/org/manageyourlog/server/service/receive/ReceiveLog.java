package org.manageyourlog.server.service.receive;

import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author cartoon
 * @date 2021/12/3 20:15
 */
public interface ReceiveLog {

    Logger log = LoggerFactory.getLogger(ReceiveLog.class);

    UploadLogResp<Boolean> receive(UploadLogRecordReq uploadLogRecordReq);

    UploadLogResp<Boolean> receive(List<UploadLogRecordReq> uploadLogRecordReqs);
}
