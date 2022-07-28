package org.manage.log.upload.service.factory;

import org.manage.log.common.util.loadCondition.BaseLoadMode;
import org.manage.log.upload.UploadLog;
import org.manage.log.upload.service.UploadLogByDefault;
import org.manage.log.upload.service.kafka.UploadLogByKafka;
import org.manage.log.upload.service.rpc.UploadLogByRpc;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author cartoon
 * @date 2022/1/9 15:43
 */
public enum UploadLogMode implements BaseLoadMode<UploadLog> {

    defaultMode("default", UploadLogByDefault.class),
    kafka("kafka", UploadLogByKafka.class),
    rpc("rpc", UploadLogByRpc.class);

    private final String mode;

    private final Class<? extends UploadLog> handleClass;

    UploadLogMode(String mode, Class<? extends UploadLog> handleClass) {
        this.mode = mode;
        this.handleClass = handleClass;
    }

    @Override
    public String getMode() {
        return mode;
    }

    @Override
    public Class<? extends UploadLog> classType() {
        return handleClass;
    }


    public static Optional<UploadLogMode> parse(String mode){
        return Arrays.stream(UploadLogMode.values()).filter(data -> data.getMode().equals(mode)).findFirst();
    }
}
