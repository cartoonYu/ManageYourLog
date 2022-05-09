package org.manage.log.query.config;

/**
 * @author cartoon
 * @date 2022/1/2 18:28
 */
public enum ApplicationConfigKey {

    queryLogLoadMode("query.log.load.mode", "load query log mode");

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
