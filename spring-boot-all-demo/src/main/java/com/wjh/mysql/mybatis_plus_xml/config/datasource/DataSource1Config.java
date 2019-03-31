package com.wjh.mysql.mybatis_plus_xml.config.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;


//@Configuration
public class DataSource1Config {
	
	@Value("${spring.datasource.type}")
	private Class<? extends DataSource> dataSourceType;

    @Bean(name = "oneDataSource")
    //@ConfigurationProperties(prefix = "spring.datasource.one")
    @Primary
    public DataSource testDataSource() {
        DruidDataSource build = DruidDataSourceBuilder.create().build();
        return build;
    }
    
}
