package org.manage.log.upload.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.manage.log.common.util.GsonUtil;
import org.manage.log.config.facade.LogConfigFacade;
import org.manage.log.config.facade.dto.LogConfigDto;
import org.manage.log.upload.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author cartoon
 * @date 2022/6/1 10:32
 */
@Aspect
@Component
public class UploadLogAspect {

    private static final Logger log = LoggerFactory.getLogger(UploadLogAspect.class);

    @DubboReference
    private LogConfigFacade logConfigFacade;

    @Pointcut(value = "@annotation(org.manage.log.upload.Log)")
    public void uploadLogAspect(){
    }

    @After("uploadLogAspect()")
    public void uploadLog(JoinPoint point){
        Method annotationMethod = ((MethodSignature)point.getSignature()).getMethod();
        Log logAnnotation = annotationMethod.getAnnotation(Log.class);
        LogConfigDto logConfigDto = logConfigFacade.getByName(logAnnotation.ruleName());
        log.info(GsonUtil.getInstance().writeJson(logAnnotation));
    }
}
