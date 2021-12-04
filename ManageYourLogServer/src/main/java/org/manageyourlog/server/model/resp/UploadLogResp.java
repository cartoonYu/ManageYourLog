package org.manageyourlog.server.model.resp;

import org.manageyourlog.common.constants.Error;

/**
 * @author cartoon
 * @date 2021/10/30 17:54
 */
public class UploadLogResp<T> {

    private boolean hasAbnormal;

    private Error failResult;

    private T successResult;

    public UploadLogResp(T successResult) {
        hasAbnormal = false;
        this.successResult = successResult;
    }

    public UploadLogResp(Error failResult) {
        hasAbnormal = true;
        this.failResult = failResult;
    }

    public UploadLogResp() {
    }

    public boolean isHasAbnormal() {
        return hasAbnormal;
    }

    public Error getFailResult() {
        return failResult;
    }

    public T getSuccessResult() {
        return successResult;
    }
}
