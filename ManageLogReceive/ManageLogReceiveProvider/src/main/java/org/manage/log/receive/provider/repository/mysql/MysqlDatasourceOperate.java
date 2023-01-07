package org.manage.log.receive.provider.repository.mysql;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.manage.log.common.util.factory.LoadBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import java.util.Objects;
import java.util.function.Function;

/**
 * mysql operate layer
 * @author cartoon
 * @date 2022/1/4 23:23
 */
@Component
@LoadBean(loadConfigKey = "store.load.mode", mode = "mysql", needPrimary = false)
public class MysqlDatasourceOperate {

    private final PlatformTransactionManager platformTransactionManager;

    private final SqlSessionFactory sqlSessionFactory;

    private final ThreadLocal<ImmutablePair<SqlSession, TransactionStatus>> executeInfos;

    public <T, R> R executeDML(Class<T> classType, Function<T, R> executeFunction){
        return executeDML(classType, executeFunction, false);
    }

    public <T, R> R executeDML(Class<T> classType, Function<T, R> executeFunction, boolean isEndCall){
        ImmutablePair<SqlSession, TransactionStatus> executeInfo = executeInfos.get();
        if(Objects.isNull(executeInfo)){
            executeInfo = ImmutablePair.of(sqlSessionFactory.openSession(), platformTransactionManager.getTransaction(new DefaultTransactionDefinition()));
            executeInfos.set(executeInfo);
        }
        SqlSession sqlSession = executeInfo.getLeft();
        TransactionStatus transactionStatus = executeInfo.getRight();
        R executeRes;
        try {
            T mapper = sqlSession.getMapper(classType);
            executeRes =  executeFunction.apply(mapper);
        } catch (Exception e) {
            platformTransactionManager.rollback(transactionStatus);
            throw e;
        } finally {
            if(isEndCall){
                sqlSession.close();
                executeInfos.remove();
            }
        }
        return executeRes;
    }

    public MysqlDatasourceOperate(@Qualifier("mysqlPlatformTransactionManager") PlatformTransactionManager platformTransactionManager,
                                  @Qualifier("mysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        this.platformTransactionManager = platformTransactionManager;
        this.sqlSessionFactory = sqlSessionFactory;
        executeInfos = new ThreadLocal<>();
    }
}
