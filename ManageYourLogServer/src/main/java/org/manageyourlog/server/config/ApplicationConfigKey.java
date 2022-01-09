package org.manageyourlog.server.config;

/**
 * @author cartoon
 * @date 2022/1/2 18:28
 */
public enum ApplicationConfigKey {

    storeMethod("store.method", "存储方式"),
    storeLoadDao("store.load.dao", "加载存储方式种类");

    private String key;

    private String description;

    ApplicationConfigKey(String key, String description) {
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
