package org.manageyourlog.common.util;

import com.google.gson.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * gson util, package gson operation
 * @author cartoon
 * @version 1.0
 * @since 2021/12/24 01:26
 */
public class GsonUtil {

    private static final GsonUtil INSTANCE = new GsonUtil();

    private static final String TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";

    public static GsonUtil getInstance(){
        return INSTANCE;
    }

    public <T> String writeJson(List<T> data){
        return formGsonObject().toJson(data);
    }

    public <T> String writeJson(T data){
        return formGsonObject().toJson(data);
    }

    public <T> T readJsonObject(String sourceStr, Class<T> classType){
        return formGsonObject().fromJson(sourceStr, classType);
    }

    public <T> List<T> readJson(String sourceStr, Class<T> classType){
        Type type = new ParameterizedTypeImpl<>(classType);
        return formGsonObject().fromJson(sourceStr, type);
    }

    public Gson formGsonObject(){
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (localDateTime, type, jsonSerializationContext) -> new JsonPrimitive(localDateTime.format(DateTimeFormatter.ofPattern(TIME_FORMATTER))))
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) -> LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern(TIME_FORMATTER)))
                .serializeNulls()
                .create();
    }

    private static class ParameterizedTypeImpl<T> implements ParameterizedType {

        Class<T> clazz;

        public ParameterizedTypeImpl(Class<T> clz) {
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

    private GsonUtil() {
    }
}
