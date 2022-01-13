package org.manageyourlog.server.controller.receive;

/**
 * @author cartoon
 * @date 2022/1/11 16:44
 */
public enum ReceiveLogMode {

    http("http"),
    mq("mq");

    private String mode;

    ReceiveLogMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
