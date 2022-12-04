package org.manage.log.receive.facade.dto;

import org.manage.log.common.constants.HandleError;

import java.io.Serializable;

/**
 * operate log response
 * <p>
 * package operate result<br/>
 * you only can passing correct/wrong result though constructor to package operate result
 * </p>
 * <p>
 * get operate result<br/>
 * you can get operate result as follows rule<br/>
 * 1. call method isHasAbnormal to judge operate result<br/>
 * 2. if step 1 is true, call method getSuccessResult to get success operate result<br/>
 * 3. if step 1 is false, call method getFailResult to get fail operate result</p>
 * <p>
 * you can package operate result as follows rule<br/>
 * 1. only can passing correct/wrong result though constructor
 * </p>
 * @author cartoon
 * @date 2021/10/30 17:54
 */
public class OperateLogResp<T> implements Serializable {

    private Boolean hasAbnormal;

    private HandleError failResult;

    private T successResult;

    public OperateLogResp(T successResult) {
        hasAbnormal = false;
        this.successResult = successResult;
    }

    public OperateLogResp(HandleError failResult) {
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