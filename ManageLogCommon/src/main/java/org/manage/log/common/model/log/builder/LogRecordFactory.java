package org.manage.log.common.model.log.builder;

import org.manage.log.common.constants.LogRecordIndexSort;
import org.manage.log.common.constants.LogRecordSort;
import org.manage.log.common.constants.OperatorSort;
import org.manage.log.common.model.log.LogRecord;
import org.manage.log.common.util.IdGenerateUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/12/20 23:20
 */
@Component
public class LogRecordFactory {

    @Resource
    private LogIndexFactory indexFactory;

    public String generateRecordId(){
        return IdGenerateUtil.getInstance().generate(13);
    }

    public LogRecord build(String content, OperatorSort operatorSort, String operator,
                           LogRecordSort logRecordSort, Map<String, LogRecordIndexSort> indexValueToIndexSortMap,
                           LocalDateTime createTime){
        return LogRecordBuilder.getInstance(this, indexFactory)
                .setContent(content)
                .setOperatorSort(operatorSort)
                .setOperator(operator)
                .setLogRecordSort(logRecordSort)
                .setIndexValueToIndexSortMap(indexValueToIndexSortMap)
                .setCreateTime(createTime)
                .build();
    }

    public void check(LogRecord logRecord){
        Assert.notNull(logRecord.getRecordId(), "record id must not be null");
        Assert.notNull(logRecord.getContent(), "content must not be null");
        Assert.notNull(logRecord.getOperatorSort(), "operator sort must not be null");
        Assert.notNull(logRecord.getOperator(), "operator must not be null");
        Assert.notNull(logRecord.getLogRecordSort(), "record sort must not be null");
        Assert.notNull(logRecord.getVersion(), "version must not be null");
        Assert.notNull(logRecord.getCreateTime(), "create time must not be null");
        Assert.notNull(logRecord.getModifyTime(), "modify time must not be null");

    }
}
