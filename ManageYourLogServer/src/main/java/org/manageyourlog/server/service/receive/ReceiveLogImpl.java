package org.manageyourlog.server.service.receive;

import org.manageyourlog.common.constants.Error;
import org.manageyourlog.common.util.CollectionUtil;
import org.manageyourlog.common.util.IdGenerateUtil;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.manageyourlog.server.model.LogRecord;
import org.manageyourlog.server.model.LogRecordIndex;
import org.manageyourlog.server.repository.LogRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * @author cartoon
 * @date 2021/11/17 23:14
 */
@Service
public abstract class ReceiveLogImpl implements ReceiveLog {

    protected final Logger log = LoggerFactory.getLogger(ReceiveLogImpl.class);

    @Autowired
    private LogRecordRepository logRecordRepository;

    @Override
    public UploadLogResp<Boolean> receive(UploadLogRecordReq uploadLogRecordReq) {
        if(!judgeParamIllegal(uploadLogRecordReq)){
            return new UploadLogResp<>(Error.paramMiss);
        }
        LogRecord logRecord = packLogRecord(uploadLogRecordReq);
        boolean saveRes = ofNullable(logRecord)
                                .map(record -> logRecordRepository.save(initLogRecord(record)))
                                .orElse(false);
        return new UploadLogResp<>(saveRes);
    }

    @Override
    public UploadLogResp<Boolean> receive(List<UploadLogRecordReq> uploadLogRecordReqs) {
        if(!judgeParamIllegal(uploadLogRecordReqs)){
            return new UploadLogResp<>(Error.paramMiss);
        }
        List<LogRecord> logRecords = packLogRecord(uploadLogRecordReqs);
        if(CollectionUtil.judgeIsEmpty(logRecords)){
            return new UploadLogResp<>(false);
        }
        logRecords.forEach(record -> record = initLogRecord(record));
        boolean saveRes = logRecordRepository.save(logRecords);
        return new UploadLogResp<>(saveRes);
    }

    protected abstract boolean judgeParamIllegal(UploadLogRecordReq uploadLogRecordReq);

    protected abstract LogRecord packLogRecord(UploadLogRecordReq logRecord);

    private List<LogRecord> packLogRecord(List<UploadLogRecordReq> logRecords){
        return logRecords.stream().map(this::packLogRecord).collect(Collectors.toList());
    }

    private boolean judgeParamIllegal(List<UploadLogRecordReq> uploadLogRecordReqs){
        if(CollectionUtil.judgeIsEmpty(uploadLogRecordReqs)){
            return false;
        }
        for(UploadLogRecordReq req : uploadLogRecordReqs){
            if(!judgeParamIllegal(req)){
                return false;
            }
        }
        return true;
    }

    private LogRecord initLogRecord(LogRecord logRecord){
        String recordId = IdGenerateUtil.generate(13);
        return ofNullable(logRecord).map(record -> {
            record.setRecordId(recordId)
                    .setVersion(1);
            List<LogRecordIndex> logRecordIndices = record.getIndexList();
            ofNullable(logRecordIndices)
                    .ifPresent(indexList ->
                            indexList.forEach(index ->
                                    index.setIndexId(IdGenerateUtil.generate(13))
                                            .setVersion(1)
                                            .setLogRecordId(recordId)
                            )
                    );
            return record;
        }).orElse(null);
    }
}
