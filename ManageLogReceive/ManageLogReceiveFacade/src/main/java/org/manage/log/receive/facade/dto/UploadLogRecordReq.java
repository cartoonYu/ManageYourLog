package org.manage.log.receive.facade.dto;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author cartoon
 * @date 2021/10/30 17:28
 */
public class UploadLogRecordReq implements Serializable {

    private String configName;

    private String operator;

    private String valueData;

    private LocalDateTime uploadTime;

    public String getConfigName() {
        return configName;
    }

    public UploadLogRecordReq setConfigName(String configName) {
        this.configName = configName;
        return this;
    }


    public String getValueData() {
        return valueData;
    }

    public UploadLogRecordReq setValueData(String valueData) {
        this.valueData = valueData;
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
