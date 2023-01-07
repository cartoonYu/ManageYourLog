package org.manage.log.common.model.log;


import org.manage.log.common.model.log.constants.LogRecordSort;
import org.manage.log.common.model.log.constants.OperatorSort;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/05 23:48
 */
public record LogRecord(
        //record id, identify record single
        String recordId,
        //record source content
        String content,
        //operate record sort
        OperatorSort operatorSort,
        //operate current record handles
        String operator,
        //record sort
        LogRecordSort logRecordSort,
        //record index list
        List<LogRecordIndex> indexList,
        //version
        Integer version,
        //record create time
        LocalDateTime createTime,
        //record last modify time
        LocalDateTime modifyTime
) {

    @Override
    public String recordId() {
        return recordId;
    }

    @Override
    public String content() {
        return content;
    }

    @Override
    public OperatorSort operatorSort() {
        return operatorSort;
    }

    @Override
    public String operator() {
        return operator;
    }

    @Override
    public LogRecordSort logRecordSort() {
        return logRecordSort;
    }

    @Override
    public List<LogRecordIndex> indexList() {
        return indexList;
    }

    @Override
    public Integer version() {
        return version;
    }

    @Override
    public LocalDateTime createTime() {
        return createTime;
    }

    @Override
    public LocalDateTime modifyTime() {
        return modifyTime;
    }
}
