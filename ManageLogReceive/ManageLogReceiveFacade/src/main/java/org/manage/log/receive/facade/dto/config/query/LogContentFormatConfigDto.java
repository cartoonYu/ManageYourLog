package org.manage.log.receive.facade.dto.config.query;

import java.time.LocalDateTime;

/**
 * @author cartoon.yu
 * @version 1.0
 * @since 2023/1/8 20:55
 */
public class LogContentFormatConfigDto {

    /**
     * rule name
     */
    private String ruleName;

    /**
     * choose format type mode
     */
    private String type;

    /**
     * format content basis value
     */
    private String value;

    /**
     * execute sequence
     * NOTICE: start with zero
     */
    private Long executeSequence;

    private Long version;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public String getRuleName() {
        return ruleName;
    }

    public LogContentFormatConfigDto setRuleName(String ruleName) {
        this.ruleName = ruleName;
        return this;
    }

    public String getType() {
        return type;
    }

    public LogContentFormatConfigDto setType(String type) {
        this.type = type;
        return this;
    }

    public String getValue() {
        return value;
    }

    public LogContentFormatConfigDto setValue(String value) {
        this.value = value;
        return this;
    }

    public Long getExecuteSequence() {
        return executeSequence;
    }

    public LogContentFormatConfigDto setExecuteSequence(Long executeSequence) {
        this.executeSequence = executeSequence;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public LogContentFormatConfigDto setVersion(Long version) {
        this.version = version;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LogContentFormatConfigDto setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public LogContentFormatConfigDto setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }
}
