package org.manageyourlog.server.repository.factory;

import org.manageyourlog.server.repository.LogRecordMysqlRepository;

/**
 * @author cartoon
 * @date 2022/1/3 00:28
 */
public enum StoreMode {

    Mysql("mysql", LogRecordMysqlRepository.class);

    private final String mode;

    private final Class<?> classType;

    StoreMode(String mode, Class<?> classType) {
        this.mode = mode;
        this.classType = classType;
    }

    public String getMode() {
        return mode;
    }

    public Class<?> getClassType() {
        return classType;
    }
}
