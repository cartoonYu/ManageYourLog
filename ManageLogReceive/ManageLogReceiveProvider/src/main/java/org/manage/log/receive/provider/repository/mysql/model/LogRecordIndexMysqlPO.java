package org.manage.log.receive.provider.repository.mysql.model;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/21 00:04
 */
public record LogRecordIndexMysqlPO(
        String indexId, String logRecordId,
        String sort, String indexValue,
        Integer version, LocalDateTime createTime, LocalDateTime modifyTime) {

    @Override
    public String indexId() {
        return indexId;
    }

    @Override
    public String logRecordId() {
        return logRecordId;
    }

    @Override
    public String sort() {
        return sort;
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
