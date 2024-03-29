package org.manage.log.common.model.log.builder;

import org.manage.log.common.model.log.constants.LogRecordIndexSort;
import org.manage.log.common.model.log.LogRecordIndex;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/12/19 23:13
 */
public class LogIndexBuilder {

    private String indexId;

    private LogRecordIndexSort logRecordIndexSort;

    private String indexValue;

    private Integer version = 1;

    private LocalDateTime createTime = LocalDateTime.now();

    private LocalDateTime modifyTime = LocalDateTime.now();

    public LogIndexBuilder setLogRecordIndexSort(LogRecordIndexSort logRecordIndexSort) {
        if(Objects.isNull(logRecordIndexSort)){
            return this;
        }
        this.logRecordIndexSort = logRecordIndexSort;
        return this;
    }

    public LogIndexBuilder setIndexValue(String indexValue) {
        if(Objects.isNull(indexValue)){
            return this;
        }
        this.indexValue = indexValue;
        return this;
    }

    public static LogIndexBuilder getInstance(LogIndexFactory logIndexFactory){
        return new LogIndexBuilder(logIndexFactory);
    }

    private LogIndexFactory logIndexFactory;

    private LogIndexBuilder(LogIndexFactory logIndexFactory) {
        this.logIndexFactory = logIndexFactory;
    }

    public LogRecordIndex build(){
        indexId = logIndexFactory.generateIndexId();
        LogRecordIndex logRecordIndex = new LogRecordIndex(
                indexId,
                logRecordIndexSort,
                indexValue,
                version,
                createTime,
                modifyTime
        );
        logIndexFactory.check(logRecordIndex);
        return logRecordIndex;
    }
}
