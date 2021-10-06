package org.manageyourlogcommon.constants;

import java.util.Arrays;

public enum LogRecordIndexSort {

    Date(1L, "date"),
    Id(2L, "id");

    private Long sortId;

    private String sortDescription;

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

    public static LogRecordIndexSort parse(Long sortId){
        return Arrays.stream(LogRecordIndexSort.values())
                .filter(value -> value.getSortId().equals(sortId))
                .findAny()
                .orElse(null);
    }
}
