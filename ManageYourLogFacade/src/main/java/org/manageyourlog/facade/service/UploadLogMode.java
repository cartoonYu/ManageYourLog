package org.manageyourlog.facade.service;

import org.manageyourlog.facade.config.ApplicationConfigKey;

import java.util.Arrays;

public enum UploadLogMode {

    http("http", ApplicationConfigKey.uploadLogServerUrl, ActualUploadLogByHttp.class),
    defaultMode("http", ApplicationConfigKey.uploadLogServerUrl, ActualUploadLogByHttp.class);

    private String mode;

    private ApplicationConfigKey baseUrl;

    private Class<?> classType;

    UploadLogMode(String mode, ApplicationConfigKey baseUrl, Class<?> classType) {
        this.mode = mode;
        this.baseUrl = baseUrl;
        this.classType = classType;
    }

    public String getMode() {
        return mode;
    }

    public Class<?> getClassType() {
        return classType;
    }

    public ApplicationConfigKey getBaseUrl() {
        return baseUrl;
    }

    public static UploadLogMode parse(String mode){
        return Arrays.stream(UploadLogMode.values()).filter(uploadLogMode -> uploadLogMode.getMode().equals(mode)).findAny().orElse(defaultMode);
    }
}
