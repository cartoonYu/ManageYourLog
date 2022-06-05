package org.manage.log.upload.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.manage.log.upload.Log;
import org.springframework.stereotype.Component;

/**
 * @author cartoon
 * @date 2022/6/1 10:32
 */
@Aspect
@Component
public class UploadLogAspect {

    @Pointcut(value = "@annotation(org.manage.log.upload.Log)")
    public void uploadLogAspect(){
    }

    @After("uploadLogAspect()")
    public void uploadLog(JoinPoint point){
        Log log = ((MethodSignature)point.getSignature()).getMethod().getAnnotation(Log.class);
        System.out.println("111" + log.content());
    }
}
