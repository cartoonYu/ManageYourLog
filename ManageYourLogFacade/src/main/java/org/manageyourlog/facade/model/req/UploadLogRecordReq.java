package org.manageyourlog.facade.model.req;

import org.manageyourlog.common.constants.LogRecordSort;

import java.util.List;

/**
 * @author cartoon
 * @date 2021/10/30 17:28
 */
public class UploadLogRecordReq {

    private String content;

    private String operatorSort;

    private String operator;

    private LogRecordSort logRecordSort;

    private List<UploadLogRecordIndexReq> indexList;

    public String getContent() {
        return content;
    }

    public UploadLogRecordReq setContent(String content) {
        this.content = content;
        return this;
    }

    public String getOperatorSort() {
        return operatorSort;
    }

    public UploadLogRecordReq setOperatorSort(String operatorSort) {
        this.operatorSort = operatorSort;
        return this;
    }

    public String getOperator() {
        return operator;
    }

    public UploadLogRecordReq setOperator(String operator) {
        this.operator = operator;
        return this;
    }

    public LogRecordSort getLogRecordSort() {
        return logRecordSort;
    }

    public UploadLogRecordReq setLogRecordSort(LogRecordSort logRecordSort) {
        this.logRecordSort = logRecordSort;
        return this;
    }

    public List<UploadLogRecordIndexReq> getIndexList() {
        return indexList;
    }

    public UploadLogRecordReq setIndexList(List<UploadLogRecordIndexReq> indexList) {
        this.indexList = indexList;
        return this;
    }
}
