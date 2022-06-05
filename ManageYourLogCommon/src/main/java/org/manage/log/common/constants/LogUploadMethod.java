package org.manage.log.common.constants;

/**
 * @author cartoon
 * @date 2022/6/4 16:53
 */
public enum LogUploadMethod {

    KAFKA(1L, "kafka"),
    RPC(2L, "rpc");

    private Long methodId;

    private String method;

    LogUploadMethod(Long methodId, String method) {
        this.methodId = methodId;
        this.method = method;
    }

    public Long getMethodId() {
        return methodId;
    }

    public String getMethod() {
        return method;
    }
}
