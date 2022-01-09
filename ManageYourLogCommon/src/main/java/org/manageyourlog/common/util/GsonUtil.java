package org.manageyourlog.common.util;

import com.google.gson.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/12/24 01:26
 */
public class GsonUtil {

    public static <T> String writeJson(List<T> data){
        return formGsonObject().toJson(data);
    }

    public static <T> String writeJson(T data){
        return formGsonObject().toJson(data);
    }

    public static <T> T readJsonObject(String sourceStr, Class<T> classType){
        return formGsonObject().fromJson(sourceStr, classType);
    }

    public static <T> List<T> readJson(String sourceStr, Class<T> classType){
        Type type = new ParameterizedTypeImpl(classType);
        return formGsonObject().fromJson(sourceStr, type);
    }

    private static Gson formGsonObject(){
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (localDateTime, type, jsonSerializationContext) -> new JsonPrimitive(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) -> LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .serializeNulls()
                .create();
    }

    private static class ParameterizedTypeImpl implements ParameterizedType {

        Class clazz;

        public ParameterizedTypeImpl(Class clz) {
            clazz = clz;
        }
        @Override
        public Type[] getActualTypeArguments() {
            //返回实际类型组成的数据
            return new Type[]{clazz};
        }
        @Override
        public Type getRawType() {
            //返回原生类型，即HashMap
            return List.class;
        }
        @Override
        public Type getOwnerType() {
            //返回Type对象
            return null;
        }
    }
}
