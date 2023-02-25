package org.manage.log.common.model.log.builder;

import org.manage.log.common.model.log.LogRecordIndex;
import org.manage.log.common.model.log.constants.LogRecordIndexSort;
import org.manage.log.common.model.log.constants.LogRecordSort;
import org.manage.log.common.model.log.constants.OperatorSort;
import org.manage.log.common.model.log.LogRecord;
import org.manage.log.common.util.IdGenerateUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/12/20 23:20
 */
@Component
public class LogRecordFactory {

    private final LogIndexFactory indexFactory;

    public String generateRecordId(){
        return IdGenerateUtil.getInstance().generate(13);
    }

    public LogRecord build(String content, OperatorSort operatorSort, String operator,
                           LogRecordSort logRecordSort, List<LogRecordIndex> indexList,
                           LocalDateTime createTime){
        return LogRecordBuilder.getInstance(this)
                .setContent(content)
                .setOperatorSort(operatorSort)
                .setOperator(operator)
                .setLogRecordSort(logRecordSort)
                .setIndexList(indexList)
                .setCreateTime(createTime)
                .build();
    }

    public void check(LogRecord logRecord){
        Assert.notNull(logRecord.recordId(), "record id must not be null");
        Assert.notNull(logRecord.content(), "content must not be null");
        Assert.notNull(logRecord.operatorSort(), "operator sort must not be null");
        Assert.notNull(logRecord.operator(), "operator must not be null");
        Assert.notNull(logRecord.logRecordSort(), "record sort must not be null");
        Assert.notNull(logRecord.version(), "version must not be null");
        Assert.notNull(logRecord.createTime(), "create time must not be null");
        Assert.notNull(logRecord.modifyTime(), "modify time must not be null");
        indexFactory.check(logRecord.indexList());
    }

    public LogRecordFactory(LogIndexFactory indexFactory) {
        this.indexFactory = indexFactory;
    }
}
