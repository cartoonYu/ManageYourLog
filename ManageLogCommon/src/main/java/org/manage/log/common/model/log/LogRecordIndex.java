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
        LocalDateTime modifyTime) {
}
