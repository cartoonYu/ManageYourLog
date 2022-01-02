package org.manageyourlog.server.dao.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.manageyourlog.server.dao.DatasourceEnum;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.function.Function;

/**
 * @author cartoon
 * @date 2021/12/30 21:06
 */
@Configuration
@Conditional({MysqlLoadDaoCondition.class})
@MapperScan(basePackages = MysqlDatasourceConfig.packageName,
                sqlSessionTemplateRef = "mysqlTemplate")
public class MysqlDatasourceConfig {

    public static final String packageName = "org/manageyourlog/server/dao/mysql/mapper";

    public static final String mapperLocation = "mapper/*.xml";

    public SqlSessionFactory sqlSessionFactory;

    public <T, R> R executeSql(Class<T> classType, Function<T, R> executeFunction){
        try (SqlSession sqlSession = sqlSessionFactory.openSession()){
            T mapper = sqlSession.getMapper(classType);
            return executeFunction.apply(mapper);
        }
    }


    @Bean(name = DatasourceEnum.mysql)
    public HikariDataSource getDataSource(@Qualifier("mysqlProperties") Properties properties){
        HikariConfig hikariConfig = new HikariConfig(properties);
        return new HikariDataSource(hikariConfig);
    }

    @Bean("mysqlSqlSessionFactory")
    public SqlSessionFactory createSqlSessionFactory(@Qualifier(DatasourceEnum.mysql) DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperLocation));
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(packageName);
        sqlSessionFactory = sqlSessionFactoryBean.getObject();
        return sqlSessionFactory;
    }

    @Bean(name = "mysqlTemplate")
    public SqlSessionTemplate getTemplate(@Qualifier("mysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean("mysqlProperties")
    @ConfigurationProperties(prefix = "store.mysql")
    public Properties properties(){
        return new Properties();
    }
}
