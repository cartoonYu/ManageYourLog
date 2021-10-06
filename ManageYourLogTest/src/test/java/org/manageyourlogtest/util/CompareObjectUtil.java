package org.manageyourlogtest.util;

import com.alibaba.fastjson.JSONObject;
import org.manageyourlogcommon.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/06 17:10
 */
public class CompareObjectUtil {

    private static final Logger log = LoggerFactory.getLogger(CompareObjectUtil.class);

    public static boolean compare(Class<?> classType, Object sourceObj, Object compareObj){
        if(Objects.isNull(compareObj) && Objects.isNull(sourceObj)){
            return true;
        }
        if(Objects.isNull(compareObj) || Objects.isNull(sourceObj)){
            return false;
        }
        boolean compareRes = true;
        Field[] fields = classType.getDeclaredFields();
        for(Field field : fields){
            PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(classType, field.getName());
            Method getterMethod = propertyDescriptor.getReadMethod();
            try {
                Object sourceData = getterMethod.invoke(sourceObj);
                Object compareData = getterMethod.invoke(compareObj);
                if(Objects.isNull(sourceData) && Objects.isNull(compareData)){
                    continue;
                }
                if(Objects.isNull(sourceData) || Objects.isNull(compareData)){
                    compareRes = false;
                    break;
                }
                if (String.class.isAssignableFrom(getterMethod.getReturnType())) {
                    String sourceStr = sourceData.toString();
                    String compareStr = compareData.toString();
                    if(!sourceStr.equals(compareStr)){
                        compareRes = false;
                        break;
                    }
                } else if(Number.class.isAssignableFrom(getterMethod.getReturnType())){
                    Long sourceNumber = ((Number) sourceData).longValue();
                    Long compareNumber = ((Number) compareData).longValue();
                    if (!sourceNumber.equals(compareNumber)) {
                        compareRes = false;
                        break;
                    }
                } else if(LocalDateTime.class.isAssignableFrom(getterMethod.getReturnType())){
                    String sourceStr = DateUtil.convertToString((LocalDateTime) sourceData);
                    String compareStr = DateUtil.convertToString((LocalDateTime) compareData);
                    if(!sourceStr.equals(compareStr)){
                        compareRes = false;
                        break;
                    }
                } else if(List.class.isAssignableFrom(getterMethod.getReturnType())){
                    Type genericType = field.getGenericType();
                    if (genericType instanceof ParameterizedType) {
                        ParameterizedType pt = (ParameterizedType) genericType;
                        // 得到泛型里的class类型对象
                        Class<?> actualTypeArgument = (Class<?>)pt.getActualTypeArguments()[0];
                        if(!compare(actualTypeArgument, sourceData, compareData)){
                            compareRes = false;
                            break;
                        }
                    }
                } else {
                    String sourceStr = JSONObject.toJSONString(sourceData);
                    String compareStr = JSONObject.toJSONString(compareData);
                    if(!sourceStr.equals(compareStr)){
                        compareRes = false;
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                compareRes = false;
                break;
            }
        }
        return compareRes;
    }

}
