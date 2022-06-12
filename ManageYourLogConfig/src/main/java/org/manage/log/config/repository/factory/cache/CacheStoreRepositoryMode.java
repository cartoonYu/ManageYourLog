package org.manage.log.config.repository.factory.cache;

import org.manage.log.common.util.loadCondition.BaseLoadMode;
import org.manage.log.config.repository.CacheLogConfigRepository;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author cartoon
 * @date 2022/1/3 00:28
 */
public enum CacheStoreRepositoryMode implements BaseLoadMode {

    Redis("redis", CacheLogConfigRepository.class);

    private final String mode;

    private final Class<?> classType;

    CacheStoreRepositoryMode(String mode, Class<?> classType) {
        this.mode = mode;
        this.classType = classType;
    }

    @Override
    public String getMode() {
        return mode;
    }

    public Class<?> getClassType() {
        return classType;
    }

    public static Optional<CacheStoreRepositoryMode> parse(String mode){
        return Arrays.stream(CacheStoreRepositoryMode.values()).filter(storeMode -> storeMode.getMode().equals(mode)).findAny();
    }
}
