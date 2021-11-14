package org.manageyourlog.facade.service;

import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;

import java.util.List;

/**
 * @author cartoon
 * @date 2021/10/30 17:25
 */
public interface ActualUploadLog {

    UploadLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq);

    UploadLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs);

    boolean enable();
}
