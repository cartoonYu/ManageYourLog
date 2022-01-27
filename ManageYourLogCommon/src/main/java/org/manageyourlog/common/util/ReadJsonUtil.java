package org.manageyourlog.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author cartoon
 * @date 2021/10/24 23:45
 */
public class ReadJsonUtil {

    private static final Logger log = LoggerFactory.getLogger(ReadJsonUtil.class);

    public static <T> List<T> readArray(String fileName, Class<T> classType){
        String fileData = readFile(fileName);
        return GsonUtil.getInstance().readJson(fileData, classType);
    }

    private static String readFile(String fileName){
        String res = null;
        Resource resource = new ClassPathResource(fileName);
        try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)){
            int ch;
            StringBuilder stringBuilder = new StringBuilder();
            while ((ch = reader.read()) != -1) {
                stringBuilder.append((char) ch);
            }
            res = stringBuilder.toString();
        } catch (Exception e) {
            log.error("read file, read file error", e);
        }
        return res;
    }

    private ReadJsonUtil(){}
}
