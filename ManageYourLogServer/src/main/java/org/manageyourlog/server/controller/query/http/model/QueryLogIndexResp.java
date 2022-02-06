package org.manageyourlog.server.controller.query.http.model;


/**
 * @author cartoon
 * @version 1.0
 * @since 2022/02/06 15:59
 */
public record QueryLogIndexResp(String logRecordIndexSort, String indexValue,
                                Integer version, String createTime, String modifyTime) {
}
