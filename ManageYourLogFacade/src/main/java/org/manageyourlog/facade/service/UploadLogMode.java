package org.manageyourlog.facade.service;


import org.manageyourlog.facade.config.ApplicationConfigKey;

public enum UploadLogMode {

    http("http", ApplicationConfigKey.uploadLogServerUrl, UploadLogByHttp.class),
    defaultMode("default", null, UploadLogByDefault.class);

    private final String mode;

    private final ApplicationConfigKey baseUrl;

    private final Class<?> classType;

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
}
