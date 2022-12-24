package org.manage.log.receive.facade.dto;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author cartoon
 * @date 2021/10/30 17:28
 */
public class UploadLogRecordReq implements Serializable {

    private String configName;

    private String operator;

    private List<String> valueList;

    private LocalDateTime uploadTime;

    public String getConfigName() {
        return configName;
    }

    public UploadLogRecordReq setConfigName(String configName) {
        this.configName = configName;
        return this;
    }

    public List<String> getValueList() {
        return valueList;
    }

    public UploadLogRecordReq setValueList(List<String> valueList) {
        this.valueList = valueList;
        return this;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public UploadLogRecordReq setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
        return this;
    }

    public String getOperator() {
        return operator;
    }

    public UploadLogRecordReq setOperator(String operator) {
        this.operator = operator;
        return this;
    }
}
