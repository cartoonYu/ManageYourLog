package org.manageyourlog.server.dao;

/**
 * @author cartoon
 * @date 2022/1/3 00:28
 */
public enum StoreLoadDaoEnum {

    All("all"),
    Mysql("mysql");

    private String info;

    StoreLoadDaoEnum(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
