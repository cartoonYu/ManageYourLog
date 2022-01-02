package org.manageyourlog.server.dao.mysql;

import org.manageyourlog.server.dao.StoreDaoConditionImpl;
import org.manageyourlog.server.dao.StoreLoadDaoEnum;

/**
 * @author cartoon
 * @date 2021/12/31 20:16
 */
public class MysqlLoadDaoCondition extends StoreDaoConditionImpl {

    @Override
    public StoreLoadDaoEnum datasourceType() {
        return StoreLoadDaoEnum.Mysql;
    }
}
