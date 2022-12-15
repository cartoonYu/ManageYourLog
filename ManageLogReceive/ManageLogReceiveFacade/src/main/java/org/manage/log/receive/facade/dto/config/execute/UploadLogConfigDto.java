package org.manage.log.receive.facade.dto.config.execute;


import java.util.List;

/**
 * @author cartoon
 * @since 2022/11/15 21:30
 */
public class UploadLogConfigDto {

    private String ruleName;

    private String logRecordSort;

    private String operatorSort;

    private List<UploadLogIndexConfigDto> indexConfigList;

    private String description;

    public String getRuleName() {
        return ruleName;
    }

    public UploadLogConfigDto setRuleName(String ruleName) {
        this.ruleName = ruleName;
        return this;
    }

    public String getLogRecordSort() {
        return logRecordSort;
    }

    public UploadLogConfigDto setLogRecordSort(String logRecordSort) {
        this.logRecordSort = logRecordSort;
        return this;
    }

    public String getOperatorSort() {
        return operatorSort;
    }

    public UploadLogConfigDto setOperatorSort(String operatorSort) {
        this.operatorSort = operatorSort;
        return this;
    }

    public List<UploadLogIndexConfigDto> getIndexConfigList() {
        return indexConfigList;
    }

    public UploadLogConfigDto setIndexConfigList(List<UploadLogIndexConfigDto> indexConfigList) {
        this.indexConfigList = indexConfigList;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UploadLogConfigDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
