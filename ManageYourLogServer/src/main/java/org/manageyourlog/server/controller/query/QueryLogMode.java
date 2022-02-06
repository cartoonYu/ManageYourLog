package org.manageyourlog.server.controller.query;

/**
 * @author cartoon
 * @date 2022/1/11 16:44
 */
public enum QueryLogMode {

    http("http"),
    rpc("rpc");

    private String mode;

    QueryLogMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
