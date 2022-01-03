package org.manageyourlog.server.dao.mysql;

import org.manageyourlog.server.dao.StoreConditionImpl;
import org.manageyourlog.server.dao.StoreLoadSortEnum;

/**
 * @author cartoon
 * @date 2021/12/31 20:16
 */
public class MysqlLoadCondition extends StoreConditionImpl {

    @Override
    public StoreLoadSortEnum loadSort() {
        return StoreLoadSortEnum.Mysql;
    }
}
