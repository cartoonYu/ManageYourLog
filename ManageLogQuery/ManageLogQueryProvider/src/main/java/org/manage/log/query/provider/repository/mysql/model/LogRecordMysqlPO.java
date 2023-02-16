package org.manage.log.query.provider.repository.mysql.model;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/20 23:54
 */
public record LogRecordMysqlPO(
        String recordId,
        String content,
        String operatorSort,
        String operator,
        String logRecordSort,
        Integer version,
        LocalDateTime createTime,
        LocalDateTime modifyTime) {

    @Override
    public String recordId() {
        return recordId;
    }

    @Override
    public String content() {
        return content;
    }

    @Override
    public String operatorSort() {
        return operatorSort;
    }

    @Override
    public String operator() {
        return operator;
    }

    @Override
    public String logRecordSort() {
        return logRecordSort;
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
