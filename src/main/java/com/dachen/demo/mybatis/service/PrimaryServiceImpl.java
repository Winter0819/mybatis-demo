package com.dachen.demo.mybatis.service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dachen.demo.mybatis.dao.primary.UserMapper;
import com.dachen.demo.mybatis.module.User;
import com.google.common.collect.Lists;

@Service
public class PrimaryServiceImpl {
	
	private static AtomicLong al = new AtomicLong(13823782780L);
	
	@Autowired
	UserMapper userMapper;

	@Autowired
	SqlSessionFactory sqlSessionFactory;

	@Transactional
	public Integer batchInsert() {
		return userMapper.batchInsert(buildBatchUser());
	}
	
	@Transactional
	public Integer batchXmlInsert() {
		return userMapper.batchXmlInsert(buildBatchUser());
	}
	
	public User getUserById(Integer id) {
		UserMapper userMapper1  = sqlSessionFactory.openSession().getMapper(UserMapper.class);
		userMapper1.getUserById(id);
		userMapper1.getUserById(id);
		System.out.println("------------------------------------------------------------------");
		userMapper.getUserById(id);
		return userMapper.getUserById(id);
	}
	
	private List<User> buildBatchUser() {
		Long curTime = System.currentTimeMillis();
		List<User> users = Lists.newArrayListWithCapacity(50);
		for(int i=0;i<50;i++){
			User u = new User();
			u.setName("user_"+al.incrementAndGet());
			u.setTelephone(al.get()+"");
			u.setTitle("JAVA开发工程师");
			u.setAge(randAge());
			u.setAddress("深圳科技园");
			u.setCreateTime(curTime);
			users.add(u);
		}
		return users;
	}
	
	private Integer randAge(){
		try {
			return Random.class.newInstance().nextInt(100)+1;
		} catch (InstantiationException | IllegalAccessException e) {
			return -1;
		}
	}


	














}
