package com.wjh.mysql.multi_mybatis_plus_xml.config.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;


@Configuration
@MapperScan(basePackages = {"com.wjh.mysql.multi_mybatis_plus_xml.dao.test1dao"}, sqlSessionTemplateRef  = "oneSqlSessionTemplate")
public class DataSource1Config {

    @Bean(name = "oneDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.one")
    @Primary
    public DataSource testDataSource( ) {
    	DruidDataSource druidDataSource = new DruidDataSource();
       return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "oneSqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("oneDataSource") DataSource dataSource
    		,@Qualifier("GlobalConfiguration1")GlobalConfig configuration) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setGlobalConfig(configuration);
        bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:resources/mysql/multi_mybatis_plus_xml/mybatis/mybatis_config.xml"));
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:resources/mysql/multi_mybatis_plus_xml/mybatis/mapper/one/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "oneTransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("oneDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "oneSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("oneSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean("GlobalConfiguration1")
    @ConfigurationProperties(prefix = "mybatis-plus.global-config")
    public GlobalConfig globalConfiguration() {
        return new GlobalConfig();
    }
}
