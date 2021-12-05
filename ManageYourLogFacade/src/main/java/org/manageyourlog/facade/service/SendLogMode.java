package org.manageyourlog.facade.service;


import org.manageyourlog.common.config.ApplicationConfigKey;

public enum SendLogMode {

    http("http", ApplicationConfigKey.uploadLogServerUrl, SendLogByHttp.class),
    defaultMode("default", null, SendLogByDefault.class);

    private final String mode;

    private final ApplicationConfigKey baseUrl;

    private final Class<?> classType;

    SendLogMode(String mode, ApplicationConfigKey baseUrl, Class<?> classType) {
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
