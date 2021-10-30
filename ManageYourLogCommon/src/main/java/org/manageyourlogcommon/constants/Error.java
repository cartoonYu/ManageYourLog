package org.manageyourlogcommon.constants;

/**
 * @author cartoon
 * @date 2021/10/30 17:55
 */
public enum Error {

    paramIllegal(100, "param is illegal, param is miss or param is null"),
    paramMiss(101, "param is miss, param property is miss");

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
