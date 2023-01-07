package org.manage.log.receive.facade;

import org.manage.log.receive.facade.dto.OperateLogResp;
import org.manage.log.receive.facade.dto.UploadLogRecordReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * upload log interface, current support:
 * <p>1. upload single log</p>
 * <p>2. upload batch log</p>
 * @author cartoon
 * @date 2021/11/14 23:26
 */
public interface UploadLog {

    Logger log = LoggerFactory.getLogger(UploadLog.class);

    OperateLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq);

    OperateLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs);
}
