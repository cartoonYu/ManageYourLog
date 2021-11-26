package org.manageyourlog.server.repository;

import java.util.Arrays;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/15 23:48
 */
public enum RepositoryMode {

    defaultMode("default", DefaultLogRecordRepository.class),
    mysql("mysql", LogRecordMysqlRepository.class);

    private String mode;

    private Class<?> classType;

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

    public static RepositoryMode parse(String mode){
        return Arrays.stream(RepositoryMode.values())
                .filter(repositoryMode -> repositoryMode.getMode().equals(mode))
                .findAny()
                .orElse(RepositoryMode.defaultMode);
    }
}
