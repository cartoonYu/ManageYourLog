package org.manage.log.common.constants;

import java.io.Serializable;

/**
 * error code enum, use to indicate reason of handle fail to client or server
 * @author cartoon
 * @date 2021/10/30 17:55
 */
public enum HandleError implements Serializable {

    PARAM_MISS("101", "param is miss, param property is miss"),
    UPLOAD_LOG_FAIL("301", "upload log fail");

    private final String code;

    private final String msg;

    HandleError(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
