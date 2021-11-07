package org.manageyourlog.common.constants;

/**
 * @author cartoon
 * @date 2021/10/30 17:55
 */
public enum Error {

    paramMiss(101, "param is miss, param property is miss"),
    uploadUrlMiss(200, "upload url is miss, please check config");

    private Integer code;

    private String msg;

    Error(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
