package org.manage.log.query.repository.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.manage.log.common.util.factory.LoadBean;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author cartoon
 * @date 2021/12/30 21:06
 */
@Configuration
@LoadBean(loadConfigKey = "store.load.mode", mode = "mysql", needPrimary = false)
@MapperScan(basePackages = MysqlDatasourceConfig.PACKAGE_NAME,
                sqlSessionTemplateRef = "mysqlTemplate")
public class MysqlDatasourceConfig {

    private final String mysqlDatasource = "mysqlDatasource";

    public static final String PACKAGE_NAME = "org/manage/log/query/repository/mysql/mapper";

    public static final String MAPPER_LOCATION = "mapper/*.xml";

    public SqlSessionFactory sqlSessionFactory;

    @Bean(name = mysqlDatasource)
    public HikariDataSource getDataSource(@Qualifier("mysqlProperties") Properties properties){
        HikariConfig hikariConfig = new HikariConfig(properties);
        return new HikariDataSource(hikariConfig);
    }

    @Bean("mysqlSqlSessionFactory")
    public SqlSessionFactory createSqlSessionFactory(@Qualifier(mysqlDatasource) DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + MAPPER_LOCATION));
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(PACKAGE_NAME);
        sqlSessionFactory = sqlSessionFactoryBean.getObject();
        return sqlSessionFactory;
    }

    @Bean(name = "mysqlTemplate")
    public SqlSessionTemplate getTemplate(@Qualifier("mysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean("mysqlPlatformTransactionManager")
    public PlatformTransactionManager platformTransactionManager(@Qualifier(mysqlDatasource) DataSource mysqlDataSource){
        return new DataSourceTransactionManager(mysqlDataSource);
    }

    @Bean("mysqlProperties")
    @ConfigurationProperties(prefix = "store.mysql")
    public Properties properties(){
        return new Properties();
    }

}
