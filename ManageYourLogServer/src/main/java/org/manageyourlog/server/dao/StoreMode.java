package org.manageyourlog.server.dao;

/**
 * @author cartoon
 * @date 2022/1/3 00:28
 */
public enum StoreMode {

    Mysql("mysql");

    private String info;

    StoreMode(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
