package org.manage.log.common.model.log.constants;

import java.util.Arrays;

/**
 * @author cartoon
 * @date 2022/6/4 16:50
 */
public enum OperatorSort {

    DEFAULT(0L, "default"),
    USER(1L, "USER");

    private final Long sortId;

    private final String sortDescription;

    OperatorSort(Long sortId, String sortDescription) {
        this.sortId = sortId;
        this.sortDescription = sortDescription;
    }

    public Long getSortId() {
        return sortId;
    }

    public String getSortDescription() {
        return sortDescription;
    }

    public static OperatorSort parse(String sortDescription){
        return Arrays.stream(OperatorSort.values())
                .filter(value -> value.getSortDescription().equals(sortDescription))
                .findAny()
                .orElse(null);
    }
}
