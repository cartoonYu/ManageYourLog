package org.manageyourlog.facade.model.req;

import org.manageyourlog.common.constants.LogRecordIndexSort;

/**
 * @author cartoon
 * @date 2021/10/30 17:27
 */
public class UploadLogRecordIndexReq {

    private LogRecordIndexSort logRecordIndexSort;

    private String indexValue;

    public LogRecordIndexSort getLogRecordIndexSort() {
        return logRecordIndexSort;
    }

    public UploadLogRecordIndexReq setLogRecordIndexSort(LogRecordIndexSort logRecordIndexSort) {
        this.logRecordIndexSort = logRecordIndexSort;
        return this;
    }

    public String getIndexValue() {
        return indexValue;
    }

    public UploadLogRecordIndexReq setIndexValue(String indexValue) {
        this.indexValue = indexValue;
        return this;
    }
}
