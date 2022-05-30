package org.manage.log.query.repository.factory;

import org.manage.log.common.util.loadCondition.BaseLoadMode;
import org.manage.log.query.repository.LogRecordMysqlRepository;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author cartoon
 * @date 2022/1/3 00:28
 */
public enum QueryRepositoryMode implements BaseLoadMode {

    Mysql("mysql", LogRecordMysqlRepository.class);

    private final String mode;

    private final Class<?> classType;

    QueryRepositoryMode(String mode, Class<?> classType) {
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

    public static Optional<QueryRepositoryMode> parse(String mode){
        return Arrays.stream(QueryRepositoryMode.values()).filter(storeMode -> storeMode.getMode().equals(mode)).findAny();
    }
}
