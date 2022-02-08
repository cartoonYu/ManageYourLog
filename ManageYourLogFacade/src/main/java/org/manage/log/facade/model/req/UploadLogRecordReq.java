package org.manage.log.facade.model.req;

import org.manage.log.common.constants.LogRecordSort;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author cartoon
 * @date 2021/10/30 17:28
 */
public class UploadLogRecordReq implements Serializable {

    /**
     * log content
     */
    private String content;

    /**
     * log operator sort
     */
    private String operatorSort;

    /**
     * log operator
     */
    private String operator;

    /**
     * log sort
     */
    private LogRecordSort logRecordSort;

    /**
     * log index list
     */
    private List<UploadLogRecordIndexReq> indexList;

    /**
     * log upload time
     */
    private LocalDateTime uploadTime;

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

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public UploadLogRecordReq setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
        return this;
    }
}
