package org.manage.log.query.access.layer;

import org.manage.log.common.util.loadCondition.BaseLoadMode;

/**
 * @author cartoon
 * @date 2022/1/11 16:44
 */
public enum QueryLogMode implements BaseLoadMode {

    http("http");

    private String mode;

    QueryLogMode(String mode) {
        this.mode = mode;
    }

    @Override
    public String getMode() {
        return mode;
    }
}
