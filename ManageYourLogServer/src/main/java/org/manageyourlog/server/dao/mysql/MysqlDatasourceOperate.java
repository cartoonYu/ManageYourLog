package org.manageyourlog.server.dao.mysql;

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

    private ConcurrentHashMap<Long, SqlSession> threadToSqlSessionMap;

    private ConcurrentHashMap<Long, TransactionTemplate> threadToTransactionMap;

    @Autowired
    @Qualifier("mysqlPlatformTransactionManager")
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    @Qualifier("mysqlSqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    public <T, R> R executeSql(Class<T> classType, Function<T, R> executeFunction, boolean isEndCall){
        Long threadId = Thread.currentThread().getId();
        try {
            TransactionTemplate transactionTemplate = threadToTransactionMap.get(threadId);
            if(Objects.isNull(transactionTemplate)){
                transactionTemplate = new TransactionTemplate(platformTransactionManager);
                threadToTransactionMap.put(threadId, transactionTemplate);
            }
            return transactionTemplate.execute(txStatus -> {
                SqlSession sqlSession = threadToSqlSessionMap.get(threadId);
                if(Objects.isNull(sqlSession)){
                    sqlSession = sqlSessionFactory.openSession();
                    threadToSqlSessionMap.put(threadId, sqlSession);
                }
                T mapper = sqlSession.getMapper(classType);
                return executeFunction.apply(mapper);
            });
        } finally {
            if(isEndCall && threadToSqlSessionMap.containsKey(threadId)){
                threadToSqlSessionMap.get(threadId).close();
                threadToSqlSessionMap.remove(threadId);
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
        threadToSqlSessionMap = new ConcurrentHashMap<>(32);
        threadToTransactionMap = new ConcurrentHashMap<>(32);
    }
}
