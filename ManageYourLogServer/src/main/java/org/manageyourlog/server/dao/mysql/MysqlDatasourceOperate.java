package org.manageyourlog.server.dao.mysql;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * @author cartoon
 * @date 2022/1/4 23:23
 */
@Component
@Conditional({MysqlLoadCondition.class})
public class MysqlDatasourceOperate {

    private ThreadLocal<ImmutablePair<SqlSession, TransactionTemplate>> sessionToTransaction;

    @Autowired
    @Qualifier("mysqlPlatformTransactionManager")
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    @Qualifier("mysqlSqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    public <T, R> R executeSql(Class<T> classType, Function<T, R> executeFunction, boolean isEndCall){
        try {
            ImmutablePair<SqlSession, TransactionTemplate> operateTool = sessionToTransaction.get();
            if(Objects.isNull(operateTool)){
                operateTool = new ImmutablePair<>(sqlSessionFactory.openSession(), new TransactionTemplate(platformTransactionManager));
                sessionToTransaction.set(operateTool);
            }
            final SqlSession sqlSession = operateTool.getLeft();
            return operateTool.getRight().execute(txStatus -> {
                T mapper = sqlSession.getMapper(classType);
                return executeFunction.apply(mapper);
            });
        } finally {
            if(isEndCall && Objects.nonNull(sessionToTransaction.get())){
                sessionToTransaction.remove();
            }
        }
    }

    public <T, R> R executeSql(Class<T> classType, Function<T, R> executeFunction){
        try (SqlSession sqlSession = sqlSessionFactory.openSession()){
            T mapper = sqlSession.getMapper(classType);
            return executeFunction.apply(mapper);
        }
    }

    @PostConstruct
    public void init(){
        sessionToTransaction = new ThreadLocal<>();
    }
}
