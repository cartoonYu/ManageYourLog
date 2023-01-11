package org.manage.log.receive.provider.repository.mysql;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.manage.log.common.util.factory.LoadBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.function.Supplier;

/**
 * mysql operate layer
 * @author cartoon
 * @date 2022/1/4 23:23
 */
@Component
@LoadBean(loadConfigKey = "store.load.mode", mode = "mysql", needPrimary = false)
public class MysqlDatasourceOperate {

    private static final Logger log = LoggerFactory.getLogger(MysqlDatasourceOperate.class);

    private final PlatformTransactionManager platformTransactionManager;

    public boolean executeDML(List<ImmutablePair<Supplier<Integer>, Integer>> executeFunctionToExpectExecuteRowList){
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            for(ImmutablePair<Supplier<Integer>, Integer> executeFunctionToExpectExecuteRow : executeFunctionToExpectExecuteRowList){
                Integer executeRes = executeFunctionToExpectExecuteRow.getLeft().get();
                //execute result not equals expect result, rollback current transaction
                if(!executeRes.equals(executeFunctionToExpectExecuteRow.getRight())){
                    platformTransactionManager.rollback(transactionStatus);
                    return false;
                }
            }
            platformTransactionManager.commit(transactionStatus);
            return true;
        } catch (Exception e){
            log.error("mysql datasource operate, execute error", e);
            platformTransactionManager.rollback(transactionStatus);
            return false;
        }
    }

    public MysqlDatasourceOperate(@Qualifier("mysqlPlatformTransactionManager") PlatformTransactionManager platformTransactionManager) {
        this.platformTransactionManager = platformTransactionManager;
    }
}
