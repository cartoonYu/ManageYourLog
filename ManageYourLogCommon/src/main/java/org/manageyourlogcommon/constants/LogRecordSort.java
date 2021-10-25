package org.manageyourlogcommon.constants;

import java.util.Arrays;

public enum LogRecordSort {

    Operate(1L, "operate");

    private Long sortId;

    private String sortDescription;

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

    public static LogRecordSort parse(Long sortId){
        return Arrays.stream(LogRecordSort.values())
                .filter(value -> value.getSortId().equals(sortId))
                .findAny()
                .orElse(null);
    }

    public static LogRecordSort parse(String sortId){
        Long integerValue = Long.valueOf(sortId);
        return parse(integerValue);
    }
}
