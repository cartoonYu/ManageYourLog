package org.manageyourlog.common.constants;

import java.io.Serializable;
import java.util.Arrays;

public enum LogRecordSort implements Serializable {

    DEFAULT(0L, "default"),
    Operate(1L, "operate");

    private final Long sortId;

    private final String sortDescription;

    LogRecordSort(Long sortId, String sortDescription) {
        this.sortId = sortId;
        this.sortDescription = sortDescription;
    }

    public Long getSortId() {
        return sortId;
    }

    public String getSortDescription() {
        return sortDescription;
    }

    public static LogRecordSort parse(String sortDescription){
        return Arrays.stream(LogRecordSort.values())
                .filter(value -> value.getSortDescription().equals(sortDescription))
                .findAny()
                .orElse(null);
    }

}
