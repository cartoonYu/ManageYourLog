package org.manageyourlog.server.dao;

/**
 * @author cartoon
 * @date 2022/1/3 00:28
 */
public enum StoreMode {

    Mysql("mysql");

    private String mode;

    StoreMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
