package org.manageyourlog.facade.model.resp;

import org.manageyourlog.common.constants.HandleError;

import java.io.Serializable;

/**
 * @author cartoon
 * @date 2021/10/30 17:54
 */
public class UploadLogResp<T> implements Serializable {

    private Boolean hasAbnormal;

    private HandleError failResult;

    private T successResult;

    public UploadLogResp() {
    }

    public UploadLogResp(T successResult) {
        hasAbnormal = false;
        this.successResult = successResult;
    }

    public UploadLogResp(HandleError failResult) {
        hasAbnormal = true;
        this.failResult = failResult;
    }

    public Boolean isHasAbnormal() {
        return hasAbnormal;
    }

    public HandleError getFailResult() {
        return failResult;
    }

    public T getSuccessResult() {
        return successResult;
    }
}
