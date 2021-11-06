package org.manageyourlog.facade.service;

import java.util.Arrays;

public enum UploadLogMode {

    http("http", UploadLogByHttp.class),
    defaultMode("http", UploadLogByHttp.class);

    private String mode;

    private Class<?> classType;

    UploadLogMode(String mode, Class<?> classType) {
        this.mode = mode;
        this.classType = classType;
    }

    public String getMode() {
        return mode;
    }

    public Class<?> getClassType() {
        return classType;
    }

    public static UploadLogMode parse(String mode){
        return Arrays.stream(UploadLogMode.values()).filter(uploadLogMode -> uploadLogMode.getMode().equals(mode)).findAny().orElse(defaultMode);
    }
}
