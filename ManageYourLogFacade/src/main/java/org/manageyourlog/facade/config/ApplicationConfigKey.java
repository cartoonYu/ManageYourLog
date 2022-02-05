package org.manageyourlog.facade.config;

/**
 * list all config key that facade package use independently
 * @author cartoon
 * @date 2022/1/2 18:31
 */
public enum ApplicationConfigKey {

    uploadLogMode("upload.log.mode", "upload log mode"),
    uploadLogHttpUrl("upload.log.http.url", "upload log to http server"),
    uploadLogKafkaTopic("upload.log.kafka.topic", "upload log to kafka topic"),
    uploadLogRpcIp("upload.log.rpc.ip", "upload log rpc ip address"),
    uploadLogRpcPort("upload.log.rpc.port", "upload log rpc port"),
    uploadLogRpcApplicationName("upload.log.rpc.application.name", "upload log rpc application name"),
    uploadLogRpcProtocolName("upload.log.rpc.protocol.name", "upload log rpc protocol name"),
    uploadLogRpcProtocolPort("upload.log.rpc.protocol.port", "upload log rpc protocol port"),
    uploadLogRpcSerialization("upload.log.rpc.protocol.serialization", "upload log rpc protocol serialization");

    private String key;

    private String description;

    ApplicationConfigKey(String key, String description) {
        this.key = key;
    }

    public final String getKey() {
        return key;
    }
}
