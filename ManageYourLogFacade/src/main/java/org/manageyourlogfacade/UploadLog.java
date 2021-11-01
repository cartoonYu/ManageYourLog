package org.manageyourlogfacade;

import org.manageyourlogfacade.model.req.UploadLogRecordReq;
import org.manageyourlogfacade.model.resp.UploadLogResp;

import java.util.List;

/**
 * @author cartoon
 * @date 2021/10/30 17:25
 */
public interface UploadLog {

    UploadLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq);

    UploadLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs);
}
