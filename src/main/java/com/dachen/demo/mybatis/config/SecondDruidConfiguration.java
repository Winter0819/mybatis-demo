package com.dachen.demo.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;

import java.util.Properties;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages ="com.dachen.demo.mybatis.dao.second", sqlSessionFactoryRef = "secondSqlSessionFactory")
public class SecondDruidConfiguration {

	@Autowired
	private DruidProperties druidProperties;

	@Bean(name = "secondDataSource")
	@ConfigurationProperties("second.spring.datasource.*")
	public DruidDataSource secondDataSource(SecondSourceProperties properties) {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(properties.getDriverClassName());
		dataSource.setUrl(properties.getUrl());
		dataSource.setUsername(properties.getUsername());
		dataSource.setPassword(properties.getPassword());
		dataSource.setInitialSize(druidProperties.getInitialSize());
		dataSource.setMinIdle(druidProperties.getMinIdle());
		dataSource.setMaxActive(druidProperties.getMaxActive());
		DatabaseDriver databaseDriver = DatabaseDriver.fromJdbcUrl(properties.getUrl());
		String validationQuery = databaseDriver.getValidationQuery();
		if (validationQuery != null) {
			dataSource.setTestOnBorrow(true);
			dataSource.setValidationQuery(validationQuery);
		}
		return dataSource;
	}

	@Bean(name = "secondTransactionManager")
	public DataSourceTransactionManager secondTransactionManager(
			@Qualifier("secondDataSource") DruidDataSource secondDataSource) {
		return new DataSourceTransactionManager(secondDataSource);
	}

	/**
	 * @param secondDataSource
	 * @return
	 * @throws Exception
	 */
	@Bean(name = "secondSqlSessionFactory")
	public SqlSessionFactory secondSqlSessionFactory(@Qualifier("secondDataSource") DruidDataSource secondDataSource)
			throws Exception {
		final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(secondDataSource);
		//添加XML目录  放在统计目录就可以了
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:/mapper/second/*.xml"));
		PageHelper pageHelper = new PageHelper();
		Properties p = new Properties();
		p.setProperty("offsetAsPageNum", "true");
		p.setProperty("rowBoundsWithCount", "true");
		p.setProperty("reasonable", "false");
		pageHelper.setProperties(p);
		sessionFactoryBean.setPlugins(new Interceptor[] {pageHelper});
		return sessionFactoryBean.getObject();
	}
	
	
	
}
