package com.example.core.config.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = { "com.example.**.mapper" }, sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {

    @Value("${mybatis.mapper-locations}")
    String mapperPath;

    @Value("${mybatis.config-location}")
    String mybatisConfigPath;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setPlugins(new MyBatisInterceptor());
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(mapperPath));
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource(mybatisConfigPath));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public DataSourceTransactionManager transactionManager1() {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource());
        return manager;
    }

}
