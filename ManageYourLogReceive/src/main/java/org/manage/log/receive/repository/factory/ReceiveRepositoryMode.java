package org.manage.log.receive.repository.factory;

import org.manage.log.common.util.loadCondition.BaseLoadMode;
import org.manage.log.receive.repository.LogRecordMysqlRepository;
import org.manage.log.receive.repository.LogRecordRepository;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author cartoon
 * @date 2022/1/3 00:28
 */
public enum ReceiveRepositoryMode implements BaseLoadMode<LogRecordRepository> {

    Mysql("mysql", LogRecordMysqlRepository.class);

    private final String mode;

    private final Class<? extends LogRecordRepository> classType;

    ReceiveRepositoryMode(String mode, Class<? extends LogRecordRepository> classType) {
        this.mode = mode;
        this.classType = classType;
    }

    @Override
    public String getMode() {
        return mode;
    }

    @Override
    public Class<? extends LogRecordRepository> classType() {
        return classType;
    }


    public static Optional<ReceiveRepositoryMode> parse(String mode){
        return Arrays.stream(ReceiveRepositoryMode.values()).filter(storeMode -> storeMode.getMode().equals(mode)).findAny();
    }
}
