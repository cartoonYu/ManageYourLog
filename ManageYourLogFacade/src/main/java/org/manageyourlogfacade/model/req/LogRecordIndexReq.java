package org.manageyourlogfacade.model.req;

import org.manageyourlogcommon.constants.LogRecordIndexSort;

/**
 * @author cartoon
 * @date 2021/10/30 17:27
 */
public class LogRecordIndexReq {

    private LogRecordIndexSort logRecordIndexSort;

    private String indexValue;

    public LogRecordIndexSort getLogRecordIndexSort() {
        return logRecordIndexSort;
    }

    public LogRecordIndexReq setLogRecordIndexSort(LogRecordIndexSort logRecordIndexSort) {
        this.logRecordIndexSort = logRecordIndexSort;
        return this;
    }

    public String getIndexValue() {
        return indexValue;
    }

    public LogRecordIndexReq setIndexValue(String indexValue) {
        this.indexValue = indexValue;
        return this;
    }
}
