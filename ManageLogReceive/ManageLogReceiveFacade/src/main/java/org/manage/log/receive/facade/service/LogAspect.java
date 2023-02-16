package org.manage.log.receive.facade.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
import java.lang.reflect.Parameter;
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
        Log logAnnotation = annotationMethod.getAnnotation(Log.class);
        Parameter[] parameterList = annotationMethod.getParameters();
        Object[] paramDataList = point.getArgs();
        //get config name from annotation
        String configName = getConfigName(logAnnotation);
        //get method param
        JsonObject methodParam = getArgument(parameterList, paramDataList);
        //get operator by operator key
        String operator = getOperator(logAnnotation.operatorParamKey(), methodParam, "", 0);
        //upload log
        uploadLog(configName, operator, methodParam);
    }

    /**
     * get operator though specify param key
     * @param operatorParamKey specify key
     * @param methodParam method param data
     * @param currentParamKey current param key, use to append all traverse param key
     * @param traverseDepth record traverse depth, use to judge format param key
     * @return
     */
    private String getOperator(String operatorParamKey, JsonObject methodParam, String currentParamKey, int traverseDepth){
        for(String key : methodParam.keySet()){
            JsonElement jsonElement = methodParam.get(key);
            String currentKey = traverseDepth == 0 ? key : String.format("%s.%s", currentParamKey, key);
            if(currentKey.equals(operatorParamKey) && jsonElement.isJsonPrimitive()){
                //only upper layer assign key and current key match and current json element is json primitive can return
                return jsonElement.getAsJsonPrimitive().getAsString();
            }
            if (jsonElement.isJsonObject()) {
                return getOperator(operatorParamKey, jsonElement.getAsJsonObject(), currentKey, traverseDepth + 1);
            } else if(jsonElement.isJsonArray()){
                for(JsonElement jsonObject : jsonElement.getAsJsonArray().asList()){
                    return getOperator(operatorParamKey, jsonObject.getAsJsonObject(), currentParamKey, traverseDepth + 1);
                }
            }
        }
        return "";
    }

    private JsonObject getArgument(Parameter[] parameterList, Object[] paramDataList){
        JsonObject argumentData = new JsonObject();
        for(int index = 0; index < parameterList.length; index++){
            Parameter parameter = parameterList[index];
            Object parameterData = paramDataList[index];
            JsonElement parameterDataJson = GsonUtil.getInstance().getJson(GsonUtil.getInstance().writeJson(parameterData));
            argumentData.add(parameter.getName(), parameterDataJson);
        }
        return argumentData;
    }

    private String getConfigName(Log logAnnotation){
        return logAnnotation.ruleName();
    }

    private Boolean uploadLog(String configName, String operator, JsonObject valueData){
        UploadLogRecordReq uploadLogRecordReq = packageUploadReq(configName, operator, valueData.toString());
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
