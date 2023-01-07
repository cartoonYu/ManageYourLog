package org.manage.log.query.facade.model;


import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/02/06 15:59
 */
public record QueryLogResp(String content, String operatorSort, String operator,
                            String logRecordSort, List<QueryLogIndexResp> indexList,
                           Integer version, String createTime, String modifyTime) {
}
