package org.manage.log.receive.facade.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.manage.log.common.util.GsonUtil;
import org.manage.log.receive.facade.Log;
import org.manage.log.receive.facade.UploadLog;
import org.manage.log.receive.facade.dto.OperateLogResp;
import org.manage.log.receive.facade.dto.UploadLogRecordReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @author cartoon
 * @date 2022/6/1 10:32
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Resource
    private UploadLog uploadLog;

    @Pointcut(value = "@annotation(org.manage.log.receive.facade.Log)")
    public void uploadLogAspect(){
    }

    @After("uploadLogAspect()")
    public void uploadLog(JoinPoint point){
        Method annotationMethod = ((MethodSignature)point.getSignature()).getMethod();
        //get config name from annotation
        String configName = getConfigName(annotationMethod);
        //get method param
        String methodParam = getArgument(point);
        //upload log
        uploadLog(configName, "", methodParam);
    }

    private String getArgument(JoinPoint point){
        Object[] params = point.getArgs();
        Method annotationMethod = ((MethodSignature)point.getSignature()).getMethod();
        String data = GsonUtil.getInstance().writeJson(params);
        if(log.isDebugEnabled()){
            log.info("upload log, data: {}", data);
        }
        return data;

    }

    private String getConfigName(Method annotationMethod){
        Log logAnnotation = annotationMethod.getAnnotation(Log.class);
        return logAnnotation.ruleName();
    }

    private Boolean uploadLog(String configName, String operator, String valueData){
        UploadLogRecordReq uploadLogRecordReq = packageUploadReq(configName, operator, valueData);
        OperateLogResp<Boolean> uploadResultResponse = uploadLog.upload(uploadLogRecordReq);
        boolean uploadResult = !uploadResultResponse.isHasAbnormal() && uploadResultResponse.getSuccessResult();
        log.info("upload log, data: {}, result: {}", GsonUtil.getInstance().writeJson(uploadLogRecordReq), uploadResult);
        return uploadResult;
    }

    private UploadLogRecordReq packageUploadReq(String configName, String operator, String valueData){
        UploadLogRecordReq uploadLogRecordReq = new UploadLogRecordReq();
        uploadLogRecordReq.setUploadTime(LocalDateTime.now());
        uploadLogRecordReq.setConfigName(configName);
        uploadLogRecordReq.setOperator(operator);
        uploadLogRecordReq.setValueData(valueData);
        return uploadLogRecordReq;
    }
}
