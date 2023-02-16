package org.manage.log.query.facade.model;


/**
 * @author cartoon
 * @version 1.0
 * @since 2022/02/06 15:59
 */
public class QueryLogIndexResp {

    private String logRecordIndexSort;

    private String indexValue;

    private Integer version;

    private String createTime;

    private String modifyTime;

    public String getLogRecordIndexSort() {
        return logRecordIndexSort;
    }

    public String getIndexValue() {
        return indexValue;
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

    public QueryLogIndexResp(String logRecordIndexSort, String indexValue,
                             Integer version, String createTime, String modifyTime) {
        this.logRecordIndexSort = logRecordIndexSort;
        this.indexValue = indexValue;
        this.version = version;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public QueryLogIndexResp() {
    }
}
