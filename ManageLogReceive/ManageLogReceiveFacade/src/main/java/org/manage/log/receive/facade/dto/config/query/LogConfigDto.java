package org.manage.log.receive.facade.dto.config.query;


import java.time.LocalDateTime;
import java.util.List;

/**
 * @author cartoon
 * @since 2022/11/15 21:30
 */
public class LogConfigDto {

    private String ruleName;

    private String logRecordSort;

    private String operatorSort;

    private List<LogIndexConfigDto> indexConfigList;

    private List<LogContentFormatConfigDto> contentFormatConfigList;

    private String description;

    private Long version;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public String getRuleName() {
        return ruleName;
    }

    public LogConfigDto setRuleName(String ruleName) {
        this.ruleName = ruleName;
        return this;
    }

    public String getLogRecordSort() {
        return logRecordSort;
    }

    public LogConfigDto setLogRecordSort(String logRecordSort) {
        this.logRecordSort = logRecordSort;
        return this;
    }

    public String getOperatorSort() {
        return operatorSort;
    }

    public LogConfigDto setOperatorSort(String operatorSort) {
        this.operatorSort = operatorSort;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public LogConfigDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public LogConfigDto setVersion(Long version) {
        this.version = version;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LogConfigDto setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public LogConfigDto setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    public List<LogIndexConfigDto> getIndexConfigList() {
        return indexConfigList;
    }

    public LogConfigDto setIndexConfigList(List<LogIndexConfigDto> indexConfigList) {
        this.indexConfigList = indexConfigList;
        return this;
    }

    public List<LogContentFormatConfigDto> getContentFormatConfigList() {
        return contentFormatConfigList;
    }

    public LogConfigDto setContentFormatConfigList(List<LogContentFormatConfigDto> contentFormatConfigList) {
        this.contentFormatConfigList = contentFormatConfigList;
        return this;
    }
}
