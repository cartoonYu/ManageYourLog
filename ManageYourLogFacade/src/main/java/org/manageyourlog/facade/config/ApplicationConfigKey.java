package org.manageyourlog.facade.config;

/**
 * @author cartoon
 * @date 2022/1/2 18:31
 */
public enum ApplicationConfigKey {

    uploadLogMode("upload.log.mode", "upload log mode"),
    uploadLogServerUrl("upload.log.server.url", "upload log to http server"),
    uploadLogKafkaUrl("upload.log.kafka.url", "upload log to kafka server"),
    uploadLogKafkaTopic("upload.log.kafka.topic", "upload log to kafka topic");

    private String key;

    private String description;

    ApplicationConfigKey(String key, String description) {
        this.key = key;
    }

    public final String getKey() {
        return key;
    }
}
