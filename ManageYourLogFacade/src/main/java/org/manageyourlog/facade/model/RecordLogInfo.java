package org.manageyourlog.facade.model;

import org.manageyourlog.facade.annotation.UploadLogAnnotation;

import java.lang.reflect.Method;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/07 22:08
 */
public class RecordLogInfo {

    private Method method;

    private Class<?> classType;

    private UploadLogAnnotation uploadLogAnnotation;

    private Class<?>[] paramData;

    private Class<?> responseData;

    public Method getMethod() {
        return method;
    }

    public RecordLogInfo setMethod(Method method) {
        this.method = method;
        return this;
    }

    public Class<?> getClassType() {
        return classType;
    }

    public RecordLogInfo setClassType(Class<?> classType) {
        this.classType = classType;
        return this;
    }

    public UploadLogAnnotation getUploadLogAnnotation() {
        return uploadLogAnnotation;
    }

    public RecordLogInfo setUploadLogAnnotation(UploadLogAnnotation uploadLogAnnotation) {
        this.uploadLogAnnotation = uploadLogAnnotation;
        return this;
    }

    public Class<?>[] getParamData() {
        return paramData;
    }

    public RecordLogInfo setParamData(Class<?>[] paramData) {
        this.paramData = paramData;
        return this;
    }

    public Class<?> getResponseData() {
        return responseData;
    }

    public RecordLogInfo setResponseData(Class<?> responseData) {
        this.responseData = responseData;
        return this;
    }
}
