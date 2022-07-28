package org.manage.log.config.repository.factory;

import org.manage.log.common.util.loadCondition.BaseLoadMode;
import org.manage.log.config.repository.LogConfigRepository;
import org.manage.log.config.repository.mysql.LogConfigMysqlRepository;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author cartoon
 * @date 2022/1/3 00:28
 */
public enum StoreRepositoryMode implements BaseLoadMode<LogConfigRepository> {

    Mysql("mysql", LogConfigMysqlRepository.class);

    private final String mode;

    private final Class<? extends LogConfigRepository> classType;

    StoreRepositoryMode(String mode, Class<? extends LogConfigRepository> classType) {
        this.mode = mode;
        this.classType = classType;
    }

    @Override
    public String getMode() {
        return mode;
    }

    @Override
    public Class<? extends LogConfigRepository> classType() {
        return classType;
    }


    public static Optional<StoreRepositoryMode> parse(String mode){
        return Arrays.stream(StoreRepositoryMode.values()).filter(storeMode -> storeMode.getMode().equals(mode)).findAny();
    }
}
