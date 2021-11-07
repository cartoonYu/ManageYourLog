package org.manageyourlog.facade;

import com.alibaba.fastjson.JSONArray;
import org.manageyourlog.facade.annotation.UploadLogAnnotation;
import org.manageyourlog.facade.model.RecordLogInfo;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/07 22:05
 */
@Component
public class ScanRecordLogAnnotation {

    private static final Logger log = LoggerFactory.getLogger(ScanRecordLogAnnotation.class);

    private List<RecordLogInfo> interfaceInfos;

    public void scan(String scanPackageName){
        try {
            Reflections reflections = new Reflections(scanPackageName, Scanners.values());
            Set<Method> methods = reflections.getMethodsAnnotatedWith(UploadLogAnnotation.class);
            methods.forEach(method -> {
                RecordLogInfo recordLogInfo = new RecordLogInfo();
                recordLogInfo.setMethod(method)
                        .setClassType(method.getDeclaringClass())
                        .setUploadLogAnnotation(method.getAnnotation(UploadLogAnnotation.class))
                        .setParamData(method.getParameterTypes())
                        .setResponseData(method.getReturnType());
                interfaceInfos.add(recordLogInfo);
            });
            log.info("{}", JSONArray.toJSONString(interfaceInfos));
        } catch (Exception e){
            log.error("scan reflect error", e);
        }
    }

    public List<RecordLogInfo> getInterfaceInfos() {
        return interfaceInfos;
    }

    public ScanRecordLogAnnotation() {
        this.interfaceInfos = new LinkedList<>();
    }
}
