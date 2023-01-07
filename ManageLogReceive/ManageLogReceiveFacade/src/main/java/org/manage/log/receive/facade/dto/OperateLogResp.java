package org.manage.log.receive.facade.dto;


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
 * 3. if step 1 is false, call method getAbnormalCode to get fail operate code or getAbnormalMessage to get abnormal message</p>
 * <p>
 * you can package operate result as follows rule<br/>
 * 1. only can passing correct/wrong result though constructor
 * </p>
 * @author cartoon
 * @date 2021/10/30 17:54
 */
public class OperateLogResp<T> implements Serializable {

    private Boolean hasAbnormal;

    private String abnormalCode;

    private String abnormalMessage;

    private T successResult;

    public OperateLogResp(T successResult) {
        hasAbnormal = false;
        this.successResult = successResult;
    }

    public OperateLogResp(String abnormalCode, String abnormalMessage){
        hasAbnormal = true;
        this.abnormalCode = abnormalCode;
        this.abnormalMessage = abnormalMessage;
    }

    public Boolean isHasAbnormal() {
        return hasAbnormal;
    }

    public T getSuccessResult() {
        return successResult;
    }

    public String getAbnormalCode() {
        return abnormalCode;
    }

    public String getAbnormalMessage() {
        return abnormalMessage;
    }
}