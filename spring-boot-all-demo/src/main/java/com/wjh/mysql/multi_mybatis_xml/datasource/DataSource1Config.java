package com.wjh.mysql.multi_mybatis_xml.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
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


@Configuration
@ConfigurationProperties(prefix = "spring.datasource.one")
@MapperScan(basePackages = "com.wjh.mysql.multi_mybatis_xml.mapper.one", sqlSessionTemplateRef  = "oneSqlSessionTemplate")
public class DataSource1Config {
	

    @Bean(name = "oneDataSource")
    @Primary
    public DataSource testDataSource( ) throws Exception {
    	DruidDataSource druidDataSource = new DruidDataSource();
    	druidDataSource.setUrl(url);
    	druidDataSource.setUsername(username);
    	druidDataSource.setFilters(filters);
     	druidDataSource.setDriverClassName(driverClassName);
    	druidDataSource.setInitialSize(initialSize);
    	druidDataSource.setMinIdle(minIdle);
    	druidDataSource.setMaxActive(maxActive);
    	druidDataSource.setMaxWait(maxWait);
    	druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
    	druidDataSource.setValidationQuery(validationQuery);
    	druidDataSource.setTestOnBorrow(testOnBorrow);
    	druidDataSource.setTestOnReturn(testOnReturn);
    	druidDataSource.setPoolPreparedStatements(poolPreparedStatements);
    	druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        return druidDataSource;
    }

    @Bean(name = "oneSqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("oneDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:resources/mysql/multi_mybatis_xml/mybatis/mybatis_config.xml"));
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:resources/mysql/multi_mybatis_xml/mybatis/mapper/one/*.xml"));
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
    
    
    	public String url;
    	public String username;
    	public String password;
    	public String filters;
    	public String driverClassName;
    	public Integer initialSize;
    	public Integer minIdle;
    	public Integer maxActive;
    	public Integer maxWait;
    	public Long timeBetweenEvictionRunsMillis;
    	public Long minEvictableIdleTimeMillis;
    	public String validationQuery;
    	public String testWhileIdle;
    	public Boolean testOnBorrow;
    	public Boolean testOnReturn;
        boolean poolPreparedStatements;
        public Integer maxPoolPreparedStatementPerConnectionSize;
		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getFilters() {
			return filters;
		}

		public void setFilters(String filters) {
			this.filters = filters;
		}

		public String getDriverClassName() {
			return driverClassName;
		}

		public void setDriverClassName(String driverClassName) {
			this.driverClassName = driverClassName;
		}

		public Integer getInitialSize() {
			return initialSize;
		}

		public void setInitialSize(Integer initialSize) {
			this.initialSize = initialSize;
		}

		public Integer getMinIdle() {
			return minIdle;
		}

		public void setMinIdle(Integer minIdle) {
			this.minIdle = minIdle;
		}

		public Integer getMaxActive() {
			return maxActive;
		}

		public void setMaxActive(Integer maxActive) {
			this.maxActive = maxActive;
		}

		public Integer getMaxWait() {
			return maxWait;
		}

		public void setMaxWait(Integer maxWait) {
			this.maxWait = maxWait;
		}

		public Long getTimeBetweenEvictionRunsMillis() {
			return timeBetweenEvictionRunsMillis;
		}

		public void setTimeBetweenEvictionRunsMillis(Long timeBetweenEvictionRunsMillis) {
			this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
		}

		public Long getMinEvictableIdleTimeMillis() {
			return minEvictableIdleTimeMillis;
		}

		public void setMinEvictableIdleTimeMillis(Long minEvictableIdleTimeMillis) {
			this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
		}

		public String getValidationQuery() {
			return validationQuery;
		}

		public void setValidationQuery(String validationQuery) {
			this.validationQuery = validationQuery;
		}

		public String getTestWhileIdle() {
			return testWhileIdle;
		}

		public void setTestWhileIdle(String testWhileIdle) {
			this.testWhileIdle = testWhileIdle;
		}

		public Boolean getTestOnBorrow() {
			return testOnBorrow;
		}

		public void setTestOnBorrow(Boolean testOnBorrow) {
			this.testOnBorrow = testOnBorrow;
		}

		public Boolean getTestOnReturn() {
			return testOnReturn;
		}

		public void setTestOnReturn(Boolean testOnReturn) {
			this.testOnReturn = testOnReturn;
		}

		public boolean isPoolPreparedStatements() {
			return poolPreparedStatements;
		}

		public void setPoolPreparedStatements(boolean poolPreparedStatements) {
			this.poolPreparedStatements = poolPreparedStatements;
		}

		public Integer getMaxPoolPreparedStatementPerConnectionSize() {
			return maxPoolPreparedStatementPerConnectionSize;
		}

		public void setMaxPoolPreparedStatementPerConnectionSize(Integer maxPoolPreparedStatementPerConnectionSize) {
			this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
		}

}
