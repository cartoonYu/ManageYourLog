package org.manageyourlog.server.config;

/**
 * @author cartoon
 * @date 2022/1/2 18:28
 */
public enum ApplicationConfigKey {

    storeMode("store.mode", "store log method"),
    storeLoadMode("store.load.mode", "load store mode"),
    receiveLogLoadMode("receive.log.load.mode", "load receive log mode"),
    uploadLogKafkaUrl("receive.log.kafka.url", "receive log from mq server"),
    receiveLogKafkaTopic("receive.log.kafka.topic", "receive log from mq topic");

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
