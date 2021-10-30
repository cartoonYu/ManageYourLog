package org.manageyourlogfacade.model.req;

import org.manageyourlogcommon.constants.LogRecordSort;

import java.util.List;

/**
 * @author cartoon
 * @date 2021/10/30 17:28
 */
public class LogRecordReq {

    private String content;

    private String operatorSort;

    private String operator;

    private LogRecordSort logRecordSort;

    private List<LogRecordIndexReq> indexList;

    public String getContent() {
        return content;
    }

    public LogRecordReq setContent(String content) {
        this.content = content;
        return this;
    }

    public String getOperatorSort() {
        return operatorSort;
    }

    public LogRecordReq setOperatorSort(String operatorSort) {
        this.operatorSort = operatorSort;
        return this;
    }

    public String getOperator() {
        return operator;
    }

    public LogRecordReq setOperator(String operator) {
        this.operator = operator;
        return this;
    }

    public LogRecordSort getLogRecordSort() {
        return logRecordSort;
    }

    public LogRecordReq setLogRecordSort(LogRecordSort logRecordSort) {
        this.logRecordSort = logRecordSort;
        return this;
    }

    public List<LogRecordIndexReq> getIndexList() {
        return indexList;
    }

    public LogRecordReq setIndexList(List<LogRecordIndexReq> indexList) {
        this.indexList = indexList;
        return this;
    }
}
