package org.manage.log.demo;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/07 15:50
 */
public class QueryReq {

    private String userId;

    private String orderId;

    public String getUserId() {
        return userId;
    }

    public QueryReq setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public QueryReq setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }
}
