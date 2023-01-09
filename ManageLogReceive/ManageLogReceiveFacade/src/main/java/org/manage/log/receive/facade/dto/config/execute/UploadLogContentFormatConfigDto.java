package org.manage.log.receive.facade.dto.config.execute;

/**
 * @author cartoon
 * @version 1.0
 * @since 2023/1/8 20:12
 */
public class UploadLogContentFormatConfigDto {

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

    public String getRuleName() {
        return ruleName;
    }

    public UploadLogContentFormatConfigDto setRuleName(String ruleName) {
        this.ruleName = ruleName;
        return this;
    }

    public String getType() {
        return type;
    }

    public UploadLogContentFormatConfigDto setType(String type) {
        this.type = type;
        return this;
    }

    public String getValue() {
        return value;
    }

    public UploadLogContentFormatConfigDto setValue(String value) {
        this.value = value;
        return this;
    }

    public Long getExecuteSequence() {
        return executeSequence;
    }

    public UploadLogContentFormatConfigDto setExecuteSequence(Long executeSequence) {
        this.executeSequence = executeSequence;
        return this;
    }
}
