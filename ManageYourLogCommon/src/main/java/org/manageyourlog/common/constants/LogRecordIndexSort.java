package org.manageyourlog.common.constants;

import java.util.Arrays;

public enum LogRecordIndexSort {

    Date(1L, "date"),
    Id(2L, "id");

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
