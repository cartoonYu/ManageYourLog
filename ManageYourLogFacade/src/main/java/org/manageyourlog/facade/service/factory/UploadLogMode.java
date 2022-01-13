package org.manageyourlog.facade.service.factory;

import org.manageyourlog.facade.service.UploadLogByDefault;
import org.manageyourlog.facade.service.http.UploadLogByHttp;
import org.manageyourlog.facade.service.kafka.UploadLogByKafka;
import org.manageyourlog.facade.service.http.UploadLogByHttpConfig;
import org.manageyourlog.facade.service.kafka.UploadLogByKafkaConfig;

import java.util.Arrays;

/**
 * @author cartoon
 * @date 2022/1/9 15:43
 */
public enum UploadLogMode {

    http("http", UploadLogByHttpConfig.class, UploadLogByHttp.class),
    kafka("kafka", UploadLogByKafkaConfig.class, UploadLogByKafka.class),
    defaultMode("default", null, UploadLogByDefault.class);;

    private final String mode;

    private final Class<?> configClass;

    private final Class<?> handleClass;

    UploadLogMode(String mode, Class<?> configClass, Class<?> handleClass) {
        this.mode = mode;
        this.configClass = configClass;
        this.handleClass = handleClass;
    }

    public String getMode() {
        return mode;
    }

    public Class<?> getConfigClass() {
        return configClass;
    }

    public Class<?> getHandleClass() {
        return handleClass;
    }

    public static UploadLogMode parse(String mode){
        return Arrays.stream(UploadLogMode.values()).filter(data -> data.getMode().equals(mode)).findFirst().orElse(UploadLogMode.defaultMode);
    }
}
