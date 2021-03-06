package org.manage.log.receive.access.layer;

import org.manage.log.common.util.loadCondition.BaseLoadMode;

/**
 * @author cartoon
 * @date 2022/1/11 16:44
 */
public enum ReceiveLogMode implements BaseLoadMode {

    http("http"),
    kafka("kafka"),
    mq("mq"),
    rpc("rpc");

    private String mode;

    ReceiveLogMode(String mode) {
        this.mode = mode;
    }

    @Override
    public String getMode() {
        return mode;
    }
}
