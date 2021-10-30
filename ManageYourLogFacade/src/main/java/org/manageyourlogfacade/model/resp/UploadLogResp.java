package org.manageyourlogfacade.model.resp;

import org.manageyourlogcommon.constants.Error;

/**
 * @author cartoon
 * @date 2021/10/30 17:54
 */
public class UploadLogResp<T> {

    private boolean isSuccessUpload;

    private Error failResult;

    private T successResult;

    public UploadLogResp(T successResult) {
        isSuccessUpload = true;
        this.successResult = successResult;
    }

    public UploadLogResp(Error failResult) {
        isSuccessUpload = false;
        this.failResult = failResult;
    }

    public boolean isSuccessUpload() {
        return isSuccessUpload;
    }

    public UploadLogResp<T> setSuccessUpload(boolean successUpload) {
        isSuccessUpload = successUpload;
        return this;
    }

    public Error getFailResult() {
        return failResult;
    }

    public UploadLogResp<T> setFailResult(Error failResult) {
        this.failResult = failResult;
        return this;
    }

    public T getSuccessResult() {
        return successResult;
    }

    public UploadLogResp<T> setSuccessResult(T successResult) {
        this.successResult = successResult;
        return this;
    }
}
