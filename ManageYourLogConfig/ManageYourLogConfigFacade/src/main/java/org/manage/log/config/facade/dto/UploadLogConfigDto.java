package org.manage.log.config.facade.dto;


/**
 * @author cartoon
 * @since 2022/11/15 21:30
 */
public class UploadLogConfigDto {

    private String ruleName;

    private String logRecordSort;

    private String operatorSort;

    private String indexSort;

    private String description;

    public String ruleName() {
        return ruleName;
    }

    public UploadLogConfigDto setRuleName(String ruleName) {
        this.ruleName = ruleName;
        return this;
    }

    public String logRecordSort() {
        return logRecordSort;
    }

    public UploadLogConfigDto setLogRecordSort(String logRecordSort) {
        this.logRecordSort = logRecordSort;
        return this;
    }

    public String operatorSort() {
        return operatorSort;
    }

    public UploadLogConfigDto setOperatorSort(String operatorSort) {
        this.operatorSort = operatorSort;
        return this;
    }

    public String indexSort() {
        return indexSort;
    }

    public UploadLogConfigDto setIndexSort(String indexSort) {
        this.indexSort = indexSort;
        return this;
    }

    public String description() {
        return description;
    }

    public UploadLogConfigDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
