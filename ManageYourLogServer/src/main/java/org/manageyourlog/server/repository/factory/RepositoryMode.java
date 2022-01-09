package org.manageyourlog.server.repository.factory;

import org.manageyourlog.server.repository.DefaultLogRecordRepository;
import org.manageyourlog.server.repository.LogRecordMysqlRepository;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/15 23:48
 */
public enum RepositoryMode {

    defaultMode("default", DefaultLogRecordRepository.class),
    mysql("mysql", LogRecordMysqlRepository.class);

    private final String mode;

    private final Class<?> classType;

    RepositoryMode(String mode, Class<?> classType) {
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
