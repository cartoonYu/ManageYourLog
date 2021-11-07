package org.manageyourlog.facade.annotation;

import org.manageyourlog.common.constants.LogRecordIndexSort;
import org.manageyourlog.common.constants.LogRecordSort;

/**
 * @author cartoon
 * @date 2021/11/6 17:59
 */
public @interface UploadLogAnnotation {

    String operatorSort();

    String operator();

    LogRecordSort logRecordSort();

    LogRecordIndexSort[] logRecordIndexSort();

    String[] logRecordIndexSortValue();
}
