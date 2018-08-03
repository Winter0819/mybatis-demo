package com.dachen.demo.mybatis.config;

import com.dachen.demo.util.IDGenerator;
import com.github.pagehelper.PageHelper;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfiguration {
	@Autowired(required = false)
	ServiceProperties serviceProperties;

	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties p = new Properties();
		p.setProperty("offsetAsPageNum", "true");
		p.setProperty("rowBoundsWithCount", "true");
		p.setProperty("reasonable", "false");
		pageHelper.setProperties(p);
		return pageHelper;
	}

	@Bean
	public IDGenerator idGenerator() {
		return new IDGenerator(serviceProperties.getWorkerId(), 0);
	}
}
