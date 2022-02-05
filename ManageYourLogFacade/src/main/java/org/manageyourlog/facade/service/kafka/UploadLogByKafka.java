package org.manageyourlog.facade.service.kafka;

import org.manageyourlog.facade.UploadLog;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.OperateLogResp;
import org.manageyourlog.facade.service.factory.UploadLogLoadCondition;
import org.manageyourlog.facade.service.factory.UploadLogMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author cartoon
 * @date 2022/1/9 15:23
 */
@Service
@UploadLogLoadCondition(mode = UploadLogMode.kafka)
public class UploadLogByKafka implements UploadLog {

    @Autowired
    private UploadLogByKafkaConfig uploadLogByKafkaConfig;

    @Override
    public OperateLogResp<Boolean> upload(UploadLogRecordReq uploadLogRecordReq) {
        uploadLogRecordReq.setUploadTime(LocalDateTime.now());
        uploadLogByKafkaConfig.sendMessage(uploadLogRecordReq, log);
        return new OperateLogResp<>(true);
    }

    @Override
    public OperateLogResp<Boolean> upload(List<UploadLogRecordReq> uploadLogRecordReqs) {
        uploadLogRecordReqs.forEach(this::upload);
        return new OperateLogResp<>(true);
    }
}
