package com.wjh.mysql.read_write_mybatis_plus_xml.config.read_write_config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.wjh.mysql.multi_mybatis_plus_xml.config.datasource.DataSource1Config;
import com.wjh.mysql.multi_mybatis_plus_xml.config.datasource.DataSource2Config;
import com.wjh.mysql.read_write_mybatis_plus_xml.config.interceptor.SqlPrintInterceptor;
import com.wjh.mysql.read_write_mybatis_plus_xml.tool.db.DataSourceContextHolder;
import com.wjh.mysql.read_write_mybatis_plus_xml.tool.db.DataSourceType;

@Configuration
@AutoConfigureAfter({DataSource1Config.class,DataSource2Config.class})
@MapperScan(basePackages="com.wjh.mysql.read_write_mybatis_plus_xml.dao")
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
	@Autowired
	@Qualifier("twoDataSource")
	private DataSource readDataSource01;
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
            sessionFactoryBean.setDataSource(dataSource);
            
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

    
    /**
     * 把所有数据库都放在路由中
     * @return
     */
    @Bean(name="roundRobinDataSouceProxy")
    public AbstractRoutingDataSource roundRobinDataSouceProxy() {
    	
    	Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        //把所有数据库都放在targetDataSources中,注意key值要和determineCurrentLookupKey()中代码写的一至，
        //否则切换数据源时找不到正确的数据源
        targetDataSources.put(DataSourceType.write.getType(), writeDataSource);
        targetDataSources.put(DataSourceType.read.getType()+"1", readDataSource01);
    
        final int readSize = Integer.parseInt(readDataSourceSize);
   //     MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource(readSize);
        
        //路由类，寻找对应的数据源
        AbstractRoutingDataSource proxy = new AbstractRoutingDataSource(){
        	/**
             * 这是AbstractRoutingDataSource类中的一个抽象方法，
             * 而它的返回值是你所要用的数据源dataSource的key值，有了这个key值，
             * targetDataSources就从中取出对应的DataSource，如果找不到，就用配置默认的数据源。
             */
        	@Override
        	protected Object determineCurrentLookupKey() {
        		String typeKey = DataSourceContextHolder.getReadOrWrite();
        		
        		if (typeKey !=null) {
        			if (typeKey.equals(DataSourceType.write.getType())){
                    	System.err.println("使用数据库write.............");
                        return DataSourceType.write.getType();
                    }
				}
                //读库， 简单负载均衡
                
                return DataSourceType.read.getType()+1;
        	}
        };
        
        proxy.setDefaultTargetDataSource(writeDataSource);//默认库
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }


    @Bean
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory")SqlSessionFactory sqlSessionFactory) throws Exception {
    	return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    //事务管理
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(roundRobinDataSouceProxy());
    }
    
}
