package org.manage.log.repository.mysql;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.manage.log.repository.factory.StoreRepositoryLoadCondition;
import org.manage.log.repository.factory.StoreRepositoryMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.function.Function;

/**
 * mysql operate layer
 * @author cartoon
 * @date 2022/1/4 23:23
 */
@Component
@StoreRepositoryLoadCondition(mode = StoreRepositoryMode.Mysql)
public class MysqlDatasourceOperate {

    @Autowired
    @Qualifier("mysqlPlatformTransactionManager")
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    @Qualifier("mysqlSqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    private ThreadLocal<ImmutablePair<SqlSession, TransactionStatus>> executeInfos;

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

    public <T, R> R executeDQL(Class<T> classType, Function<T, R> executeFunction){
        try (SqlSession sqlSession = sqlSessionFactory.openSession()){
            T mapper = sqlSession.getMapper(classType);
            return executeFunction.apply(mapper);
        }
    }

    @PostConstruct
    public void init(){
        executeInfos = new ThreadLocal<>();
    }

}
