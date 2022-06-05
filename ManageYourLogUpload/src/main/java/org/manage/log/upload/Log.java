package org.manage.log.upload;

import org.manage.log.common.constants.LogRecordIndexSort;
import org.manage.log.common.constants.LogRecordSort;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * @author cartoon
 * @date 2022/6/1 10:26
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface Log {

    String content();

    String operatorSort();

    String operator();

    LogRecordSort recordSort();

    LogRecordIndexSort indexSort();

    String indexValue();
}
