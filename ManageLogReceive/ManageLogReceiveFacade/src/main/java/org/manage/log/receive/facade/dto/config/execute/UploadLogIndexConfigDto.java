package org.manage.log.receive.facade.dto.config.execute;

/**
 * @author cartoon
 * @since 2022/11/15 21:30
 */
public class UploadLogIndexConfigDto {

    private String ruleName;

    private String logRecordIndexSort;

    private String valueIndexKey;

    private String description;

    public String getRuleName() {
        return ruleName;
    }

    public UploadLogIndexConfigDto setRuleName(String ruleName) {
        this.ruleName = ruleName;
        return this;
    }

    public String getLogRecordIndexSort() {
        return logRecordIndexSort;
    }

    public UploadLogIndexConfigDto setLogRecordIndexSort(String logRecordIndexSort) {
        this.logRecordIndexSort = logRecordIndexSort;
        return this;
    }

    public String getValueIndexKey() {
        return valueIndexKey;
    }

    public UploadLogIndexConfigDto setValueIndexKey(String valueIndexKey) {
        this.valueIndexKey = valueIndexKey;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UploadLogIndexConfigDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
