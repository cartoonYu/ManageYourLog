package org.manage.log.receive.facade.config;

/**
 * list all config key that facade package use independently
 * @author cartoon
 * @date 2022/1/2 18:31
 */
public enum ApplicationConfigKey {

    uploadLogUrl("upload.log.url", "upload log url"),

    uploadLogPort("upload.log.port", "upload log port"),

    uploadLogThreadPoolCoreSize("upload.log.thread.pool.core.pool.size", "upload log pool core pool size"),

    uploadLogThreadPoolMaxSize("upload.log.thread.pool.max.pool.size", "upload log pool max pool size"),

    uploadLogThreadPoolQueueSize("upload.log.thread.pool.queue.size", "upload log thread pool queue size")
    ;

    private String key;

    private String description;

    ApplicationConfigKey(String key, String description) {
        this.key = key;
    }

    public final String getKey() {
        return key;
    }
}
