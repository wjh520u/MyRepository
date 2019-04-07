package com.wjh.mysql.mybatis_plus_xml.config.read_write_config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.wjh.mysql.read_write_mybatis_plus_xml.config.interceptor.SqlPrintInterceptor;

//@Configuration
//@AutoConfigureAfter({DataSource1Config.class,DataSource2Config.class})
//@MapperScan(basePackages="com.wjh.mysql.read_write_mybatis_plus_xml.dao")
public class MybatisConfiguration {

	private static Logger log = LoggerFactory.getLogger(MybatisConfiguration.class);
	
	@Value("${spring.datasource.readSize}")
    private String readDataSourceSize;

	//XxxMapper.xml文件所在路径
	@Value("${spring.datasource.mapperLocations}")
	private String mapperLocations;
	
	 //  加载全局的配置文件
	//@Value("${spring.datasource.configLocation}")
	private String configLocation;
      
	@Autowired
	@Qualifier("oneDataSource")
	private DataSource writeDataSource;
	//@Autowired
	//@Qualifier("twoDataSource")
	//private DataSource readDataSource02;
	

    @Bean("GlobalConfiguration1")
    @ConfigurationProperties(prefix = "mybatis-plus.global-config")
    public GlobalConfig globalConfiguration( ) {
        return new GlobalConfig();
    }
	
    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactorys(@Qualifier("GlobalConfiguration1")GlobalConfig configuration
    		,@Qualifier("roundRobinDataSouceProxy")AbstractRoutingDataSource dataSource) throws Exception  {
        log.info("--------------------  sqlSessionFactory init ---------------------");
            MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(writeDataSource);
            
            //设置属性
            sessionFactoryBean.setGlobalConfig(configuration);
            //读取配置 
            sessionFactoryBean.setTypeAliasesPackage("com.wjh.mysql.read_write_mybatis_plus_xml.model");
            
            //设置mapper.xml文件所在位置 
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(mapperLocations);
            sessionFactoryBean.setMapperLocations(resources);
            //添加分页插件、打印sql插件
            Interceptor[] plugins = new Interceptor[]{new SqlPrintInterceptor()};
            sessionFactoryBean.setPlugins(plugins);
            
            return sessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory")SqlSessionFactory sqlSessionFactory) throws Exception {
    	return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    //事务管理
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(writeDataSource);
    }
    
}
