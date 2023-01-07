package org.manage.log.common.model.config.constants;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author cartoon
 * @version 1.0
 * @since 2023/1/6 22:45
 */
public enum LogContentFormatType{

    REGULAR_EXPRESSION_MATCH("REGULAR_EXPRESSION_MATCH", "regular expression match"),
    CODE_EXECUTE("CODE_EXECUTE", "execute code");

    private String type;

    private String description;

    private static final Map<String, LogContentFormatType> TYPE_TO_VALUE_MAP = Arrays.stream(LogContentFormatType.values()).collect(Collectors.toMap(LogContentFormatType::getType, Function.identity()));

    LogContentFormatType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public static LogContentFormatType parse(String type){
        return TYPE_TO_VALUE_MAP.get(type);
    }
}
