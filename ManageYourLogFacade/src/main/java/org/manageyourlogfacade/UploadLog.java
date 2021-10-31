package org.manageyourlogfacade;

import org.manageyourlogfacade.model.req.LogRecordIndexReq;
import org.manageyourlogfacade.model.req.LogRecordReq;
import org.manageyourlogfacade.model.resp.UploadLogResp;

import java.util.List;

/**
 * @author cartoon
 * @date 2021/10/30 17:25
 */
public interface UploadLog {

    UploadLogResp<Boolean> upload(LogRecordReq logRecordReq);

    UploadLogResp<Boolean> upload(List<LogRecordReq> logRecordReqs);
}
