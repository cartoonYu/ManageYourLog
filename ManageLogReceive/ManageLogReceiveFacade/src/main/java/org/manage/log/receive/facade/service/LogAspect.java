package org.manage.log.receive.facade.service;

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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        Map<String, String> valuePropertyToValueMap = getArgument(point);
        //upload log
        uploadLog(configName, "", valuePropertyToValueMap);
    }

    private Map<String, String> getArgument(JoinPoint point){
        Object[] params = point.getArgs();
        for(Object param : params){
            log.info("upload log, data: {}", GsonUtil.getInstance().writeJson(param));
        }
        return new HashMap<>();
    }

    private String getConfigName(Method annotationMethod){
        Log logAnnotation = annotationMethod.getAnnotation(Log.class);
        return logAnnotation.ruleName();
    }

    private Boolean uploadLog(String configName, String operator, Map<String, String> valuePropertyToValueMap){
        UploadLogRecordReq uploadLogRecordReq = packageUploadReq(configName, operator, valuePropertyToValueMap);
        OperateLogResp<Boolean> uploadResultResponse = uploadLog.upload(uploadLogRecordReq);
        boolean uploadResult = !uploadResultResponse.isHasAbnormal() && uploadResultResponse.getSuccessResult();
        log.info("upload log, data: {}, result: {}", GsonUtil.getInstance().writeJson(uploadLogRecordReq), uploadResult);
        return uploadResult;
    }

    private UploadLogRecordReq packageUploadReq(String configName, String operator, Map<String, String> valuePropertyToValueMap){
        UploadLogRecordReq uploadLogRecordReq = new UploadLogRecordReq();
        uploadLogRecordReq.setUploadTime(LocalDateTime.now());
        uploadLogRecordReq.setConfigName(configName);
        uploadLogRecordReq.setOperator(operator);
        uploadLogRecordReq.setValuePropertyToValueMap(valuePropertyToValueMap);
        return uploadLogRecordReq;
    }
}
