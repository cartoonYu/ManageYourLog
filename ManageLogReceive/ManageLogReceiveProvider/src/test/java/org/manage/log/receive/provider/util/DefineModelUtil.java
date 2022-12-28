package org.manage.log.receive.provider.util;

import com.google.common.collect.ImmutableList;
import org.manage.log.common.constants.LogRecordIndexSort;
import org.manage.log.common.constants.LogRecordSort;
import org.manage.log.common.constants.OperatorSort;
import org.manage.log.common.model.config.LogConfig;
import org.manage.log.common.model.config.LogIndexConfig;
import org.manage.log.common.model.log.LogRecord;
import org.manage.log.common.model.log.LogRecordIndex;
import org.manage.log.common.util.IdGenerateUtil;
import org.manage.log.receive.facade.dto.UploadLogRecordReq;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/12/6 20:31
 */
public class DefineModelUtil {

    public static UploadLogRecordReq defineLogRecordReq(){
        UploadLogRecordReq uploadLogRecordReq = new UploadLogRecordReq();
        uploadLogRecordReq.setUploadTime(LocalDateTime.now());
        uploadLogRecordReq.setConfigName("orderOperate");
        uploadLogRecordReq.setOperator("cartoon");
        uploadLogRecordReq.setValueList(ImmutableList.of("cartoon"));
        return uploadLogRecordReq;
    }

    public static LogRecord defineLogRecord(){
        LogRecord record = new LogRecord();
        String recordId = IdGenerateUtil.getInstance().generate(13);
        record.setRecordId(recordId)
                .setContent("111")
                .setOperatorSort(OperatorSort.USER)
                .setOperator("cartoon")
                .setLogRecordSort(LogRecordSort.OPERATE)
                .addIndexList(ImmutableList.of(defineLogRecordIndex(recordId)))
                .setVersion(1)
                .setCreateTime(LocalDateTime.now())
                .setModifyTime(LocalDateTime.now());
        return record;
    }

    private static LogRecordIndex defineLogRecordIndex(String recordId){
        LogRecordIndex logRecordIndex = new LogRecordIndex();
        logRecordIndex.setIndexId(IdGenerateUtil.getInstance().generate(13))
                .setLogRecordId(recordId)
                .setLogRecordIndexSort(LogRecordIndexSort.ID)
                .setIndexValue("111")
                .setVersion(1)
                .setCreateTime(LocalDateTime.now())
                .setModifyTime(LocalDateTime.now());
        return logRecordIndex;
    }

    public static LogConfig defineLogConfig(){
        LogConfig logConfig = new LogConfig();
        logConfig.setRuleId(IdGenerateUtil.getInstance().generate(13))
                .setRuleName("test rule" + IdGenerateUtil.getInstance().generate(100))
                .setLogRecordSort(LogRecordSort.DEFAULT)
                .setOperatorSort(OperatorSort.DEFAULT)
                .setContentTemplate("test content template")
                .addIndexConfig(defineLogIndexConfig())
                .setDescription("test")
                .setVersion(1L)
                .setCreateTime(LocalDateTime.now())
                .setModifyTime(LocalDateTime.now());
        return logConfig;
    }

    private static LogIndexConfig defineLogIndexConfig(){
        LogIndexConfig logIndexConfig = new LogIndexConfig();
        logIndexConfig.setRuleId(IdGenerateUtil.getInstance().generate(13))
                .setRuleName("test rule" + IdGenerateUtil.getInstance().generate(100))
                .setLogRecordIndexSort(LogRecordIndexSort.ID)
                .setValueIndex(0L)
                .setDescription("test")
                .setVersion(1L)
                .setCreateTime(LocalDateTime.now())
                .setModifyTime(LocalDateTime.now());
        return logIndexConfig;
    }
}
