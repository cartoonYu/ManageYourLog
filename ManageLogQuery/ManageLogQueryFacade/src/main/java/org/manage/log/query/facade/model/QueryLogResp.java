package org.manage.log.query.facade.model;

import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/02/06 15:59
 */
public class QueryLogResp {

    private String content;

    private String operatorSort;

    private String operator;

    private String logRecordSort;

    private List<QueryLogIndexResp> indexList;

    private Integer version;

    private String createTime;

    private String modifyTime;

    public String getContent() {
        return content;
    }

    public String getOperatorSort() {
        return operatorSort;
    }

    public String getOperator() {
        return operator;
    }

    public String getLogRecordSort() {
        return logRecordSort;
    }

    public List<QueryLogIndexResp> getIndexList() {
        return indexList;
    }

    public Integer getVersion() {
        return version;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public QueryLogResp(String content, String operatorSort, String operator,
                        String logRecordSort, List<QueryLogIndexResp> indexList,
                        Integer version, String createTime, String modifyTime) {
        this.content = content;
        this.operatorSort = operatorSort;
        this.operator = operator;
        this.logRecordSort = logRecordSort;
        this.indexList = indexList;
        this.version = version;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public QueryLogResp() {
    }
}
