package org.manageyourlog.facade.config;

import java.util.stream.BaseStream;

/**
 * @author cartoon.yu
 * @date 2021/11/6 16:58
 */
public enum ApplicationConfigKey{

    uploadLogMode("upload.log.mode"),
    uploadLogServerUrl("upload.log.server.url"),
    checkUploadLogMode("check.upload.log.mode"),
    storeMethod("store.method");

    private String key;

    ApplicationConfigKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
