package org.manage.log.receive.provider.config;

/**
 * @author cartoon
 * @date 2022/1/2 18:28
 */
public enum ApplicationConfigKey {

    receiveLogLoadMode("receive.log.load.mode", "load receive log mode"),
    uploadLogKafkaUrl("receive.log.kafka.url", "receive log from mq server"),
    receiveLogKafkaTopic("receive.log.kafka.topic", "receive log from mq topic"),
    receiveLogKafkaRate("receive.log.kafka.rate", "receive log from mq rate"),
    receiveLogRpcIp("receive.log.rpc.ip", "receive log rpc ip address"),
    receiveLogRpcPort("receive.log.rpc.port", "receive log rpc port"),
    receiveLogRpcApplicationName("receive.log.rpc.application.name", "receive log rpc application name"),
    receiveLogRpcProtocolName("receive.log.rpc.protocol.name", "receive log rpc protocol name"),
    receiveLogRpcProtocolPort("receive.log.rpc.protocol.port", "receive log rpc protocol port"),
    receiveLogRpcSerialization("receive.log.rpc.protocol.serialization", "receive log rpc protocol serialization"),
    queryLogLoadMode("query.log.load.mode", "load query log mode"),
    receiveLogConfigCacheExpireSecond("receive.log.config.cache.expire.second", "cache expire, unit: second");

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
