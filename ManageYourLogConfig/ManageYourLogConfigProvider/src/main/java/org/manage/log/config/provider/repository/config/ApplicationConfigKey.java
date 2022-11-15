package org.manage.log.config.provider.repository.config;

/**
 * @author cartoon
 * @date 2022/3/27 22:12
 */
public enum ApplicationConfigKey {

    CACHE_EXPIRE("CACHE_EXPIRE", "cache expire, unit: second");

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
