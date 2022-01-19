package org.manageyourlog.facade.service.factory;

import org.manageyourlog.facade.service.UploadLogByDefault;
import org.manageyourlog.facade.service.http.UploadLogByHttp;
import org.manageyourlog.facade.service.kafka.UploadLogByKafka;
import org.manageyourlog.facade.service.http.UploadLogByHttpConfig;
import org.manageyourlog.facade.service.kafka.UploadLogByKafkaConfig;
import org.manageyourlog.facade.service.rpc.UploadLogByRpc;

import java.util.Arrays;

/**
 * @author cartoon
 * @date 2022/1/9 15:43
 */
public enum UploadLogMode {

    http("http", UploadLogByHttp.class),
    kafka("kafka", UploadLogByKafka.class),
    rpc("rpc", UploadLogByRpc.class),
    defaultMode("default", UploadLogByDefault.class);

    private final String mode;

    private final Class<?> handleClass;

    UploadLogMode(String mode, Class<?> handleClass) {
        this.mode = mode;
        this.handleClass = handleClass;
    }

    public String getMode() {
        return mode;
    }

    public Class<?> getHandleClass() {
        return handleClass;
    }

    public static UploadLogMode parse(String mode){
        return Arrays.stream(UploadLogMode.values()).filter(data -> data.getMode().equals(mode)).findFirst().orElse(UploadLogMode.defaultMode);
    }
}
