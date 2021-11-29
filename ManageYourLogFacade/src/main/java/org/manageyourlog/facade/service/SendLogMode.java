package org.manageyourlog.facade.service;

import org.manageyourlog.facade.config.ApplicationConfigKey;

import java.util.Arrays;
import java.util.Optional;

public enum SendLogMode {

    http("http", ApplicationConfigKey.uploadLogServerUrl, SendLogByHttp.class),
    defaultMode("default", null, SendLog.class);

    private String mode;

    private ApplicationConfigKey baseUrl;

    private Class<?> classType;

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

    public static Optional<SendLogMode> parse(String mode){
        return Arrays.stream(SendLogMode.values())
                .filter(sendLogMode -> sendLogMode.getMode().equals(mode))
                .findAny();
    }
}
