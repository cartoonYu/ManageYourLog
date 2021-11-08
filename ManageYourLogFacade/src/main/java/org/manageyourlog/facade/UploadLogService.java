package org.manageyourlog.facade;

import org.manageyourlog.facade.annotation.UploadLogAnnotation;
import org.manageyourlog.facade.model.RecordLogInfo;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.manageyourlog.facade.service.UploadLogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/03 08:57
 */
@Primary
@Service
public abstract class UploadLogService implements UploadLog{

    @Autowired
    private UploadLogFactory uploadLogFactory;

    @Autowired
    private ScanRecordLogAnnotation scanRecordLogAnnotation;

    public <K>UploadLogResp<Boolean> uploadLog(Object[] requestData, K responseData){
        List<RecordLogInfo> recordLogInfos = scanRecordLogAnnotation.getInterfaceInfos();
        List<UploadLogRecordReq> uploadLogRecordReqs = recordLogInfos.stream()
                .map(recordLogInfo -> formatLog(recordLogInfo.getUploadLogAnnotation(), requestData, responseData))
                .collect(Collectors.toList());
        return upload(uploadLogRecordReqs);
    }

    protected abstract <K> UploadLogRecordReq formatLog(UploadLogAnnotation uploadLogAnnotation, Object[] requestData, K responseData);

    @Override
    public UploadLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        UploadLog uploadLog = uploadLogFactory.getActualUploadService();
        return uploadLog.upload(uploadLogRecordReq);
    }

    @Override
    public UploadLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        UploadLog uploadLog = uploadLogFactory.getActualUploadService();
        return uploadLog.upload(uploadLogRecordReqs);
    }
}
