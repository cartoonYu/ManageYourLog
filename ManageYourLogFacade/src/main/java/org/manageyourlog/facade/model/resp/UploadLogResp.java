package org.manageyourlog.facade.model.resp;

import org.manageyourlog.common.constants.HandleError;

/**
 * @author cartoon
 * @date 2022/2/2 22:01
 */
public record UploadLogResp<T>(Boolean hasAbnormal, T successResult, HandleError failResult) {

    public UploadLogResp(T successResult) {
        this(false, successResult, null);
    }

    public UploadLogResp(HandleError failResult) {
        this(true, null, failResult);
    }

    @Override
    public Boolean hasAbnormal() {
        return hasAbnormal;
    }

    @Override
    public T successResult() {
        return successResult;
    }

    @Override
    public HandleError failResult() {
        return failResult;
    }
}
