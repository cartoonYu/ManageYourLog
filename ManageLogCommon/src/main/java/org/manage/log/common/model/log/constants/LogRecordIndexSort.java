package org.manage.log.common.model.log.constants;

import java.io.Serializable;
import java.util.Arrays;

/**
 * log index sort
 * @author cartoon
 * @date 2022/1/27 16:29
 * @version v1.0
 */
public enum LogRecordIndexSort implements Serializable {

    DATE(1L, "date"),
    ID(2L, "id");

    private final Long sortId;

    private final String sortDescription;

    LogRecordIndexSort(Long sortId, String sortDescription) {
        this.sortId = sortId;
        this.sortDescription = sortDescription;
    }

    public Long getSortId() {
        return sortId;
    }

    public String getSortDescription() {
        return sortDescription;
    }

    public static LogRecordIndexSort parse(String description){
        return Arrays.stream(LogRecordIndexSort.values())
                .filter(value -> value.getSortDescription().equals(description))
                .findAny()
                .orElse(null);
    }
}
