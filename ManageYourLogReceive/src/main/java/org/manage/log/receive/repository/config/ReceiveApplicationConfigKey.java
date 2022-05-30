package org.manage.log.receive.repository.config;

/**
 * @author cartoon
 * @date 2022/3/27 22:12
 */
public enum ReceiveApplicationConfigKey {

    storeMode("store.mode", "store log method"),
    storeLoadMode("store.load.mode", "load store mode");

    private String key;

    private String description;

    ReceiveApplicationConfigKey(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }
}
