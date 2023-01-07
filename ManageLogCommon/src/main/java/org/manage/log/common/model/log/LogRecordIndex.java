package org.manage.log.common.model.log;

import org.manage.log.common.model.log.constants.LogRecordIndexSort;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/05 23:56
 */
public record LogRecordIndex(
        String indexId,
        String logRecordId,
        LogRecordIndexSort logRecordIndexSort,
        String indexValue,
        Integer version,
        LocalDateTime createTime,
        LocalDateTime modifyTime
) {

    @Override
    public String indexId() {
        return indexId;
    }

    @Override
    public String logRecordId() {
        return logRecordId;
    }

    @Override
    public LogRecordIndexSort logRecordIndexSort() {
        return logRecordIndexSort;
    }

    @Override
    public String indexValue() {
        return indexValue;
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
