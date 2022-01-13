package org.manageyourlog.server.dao.mysql;

import org.manageyourlog.common.util.loadCondition.AbstractLoadCondition;
import org.manageyourlog.server.config.ApplicationConfigKey;
import org.manageyourlog.server.dao.StoreMode;

/**
 * @author cartoon
 * @date 2021/12/31 20:16
 */
public class MysqlLoadCondition extends AbstractLoadCondition {

    @Override
    protected String configKey() {
        return ApplicationConfigKey.storeLoadMode.getKey();
    }

    @Override
    protected String matchSpecifyCondition() {
        return StoreMode.Mysql.getInfo();
    }
}
