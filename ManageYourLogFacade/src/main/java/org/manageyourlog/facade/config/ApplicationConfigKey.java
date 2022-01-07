package org.manageyourlog.facade.config;

/**
 * @author cartoon
 * @date 2022/1/2 18:31
 */
public enum ApplicationConfigKey {

    uploadLogMode("upload.log.mode"),
    uploadLogServerUrl("upload.log.server.url");

    private String key;

    ApplicationConfigKey(String key) {
        this.key = key;
    }

    public final String getKey() {
        return key;
    }
}
