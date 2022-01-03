package org.manageyourlog.server.dao;

import org.springframework.context.annotation.Condition;

/**
 * @author cartoon
 * @date 2022/1/3 00:08
 */
public interface StoreLoadCondition extends Condition {

    StoreLoadSortEnum loadSort();

}
