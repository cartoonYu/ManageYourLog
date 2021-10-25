package org.manageyourlogcommon.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static java.util.Optional.ofNullable;

/**
 * @author cartoon
 * @date 2021/10/24 23:45
 */
public class ReadJsonUtil {

    private static final Logger log = LoggerFactory.getLogger(ReadJsonUtil.class);

    public static JSONArray readJsonArray(String fileName){
        String fileData = readFile(fileName);
        System.out.println(fileData);
        return ofNullable(fileData)
                .map(JSONArray::parseArray)
                .orElse(new JSONArray());
    }

    public static JSONObject readJsonObject(String fileName){
        String fileData = readFile(fileName);
        return ofNullable(fileData)
                .map(JSONObject::parseObject)
                .orElse(new JSONObject());
    }

    private static String readFile(String fileName){
        String res = null;
        Resource resource = new ClassPathResource(fileName);
        try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)){
            int ch;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            res = sb.toString();
        } catch (Exception e) {
            log.error("read file, read file error", e);
        }
        return res;
    }
}
