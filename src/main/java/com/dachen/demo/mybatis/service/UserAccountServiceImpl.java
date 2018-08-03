package com.dachen.demo.mybatis.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dachen.demo.mybatis.dao.second.UserAcountLogMapper;
import com.dachen.demo.mybatis.dao.second.UserAcountMapper;
import com.dachen.demo.mybatis.module.UserAccount;
import com.dachen.demo.mybatis.module.UserAccountLog;
import com.dachen.demo.mybatis.module.UserAccountLogVO;
import com.dachen.demo.mybatis.module.UserAccountVO;
import com.dachen.demo.util.IDGenerator;
import com.dachen.demo.util.PageVO;
import com.dachen.demo.util.ServiceException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;

@Service
public class UserAccountServiceImpl {
	
	@Autowired
	UserAcountLogMapper userAcountLogMapper;
	
	@Autowired
	IDGenerator idGenerator;
	
	@Autowired
	UserAcountMapper userAcountMapper;
	
	@Transactional(value = "secondTransactionManager")
	public Integer batchInsert(Integer userId,Long amount) {
		Long curTime = System.currentTimeMillis();
		List<UserAccountLog> logs = Lists.newArrayListWithExpectedSize(100);
		int start = userId*1000;
		int end = start+100;
		for(int i=start;i<end;i++){
			UserAccountLog log = new UserAccountLog();
			log.setId(idGenerator.nextId());
			log.setAmount(amount);
		    log.setUserId(userId);
		    log.setCode(i+"");
		    log.setCreateTime(curTime);
		    logs.add(log);
		}
		Integer count = userAcountLogMapper.batchInsert(logs);
	    if(count == logs.size()){
	    	Long sumAmount = logs.stream().filter(p->Objects.nonNull(p.getAmount()))
    				.mapToLong(UserAccountLog::getAmount).sum();
	    	if(Objects.nonNull(userAcountMapper.getUserAccountByUserId(userId))){
	    		userAcountMapper.updateUserAccount(sumAmount, userId, curTime);
	    	}else{
	    		userAcountMapper.insertUserAccount(new UserAccount(idGenerator.nextId(), userId, sumAmount, curTime, curTime));
	    	}
	    }else{
	    	throw new ServiceException("logs异常插入");
	    }
	    /*if(count>0)	throw new ServiceException("回滚");*/
	    return count;
	}

	public PageVO getLogs(Integer userId, Integer pageIndex, Integer pageSize) {
		if(Objects.isNull(pageIndex)) pageIndex = 0;
		if(Objects.isNull(pageSize)) pageSize = 10;
		Page<UserAccountLogVO> page = PageHelper.startPage(pageIndex+1, pageSize);
		List<UserAccountLogVO> list = userAcountLogMapper.selectLogsByUserId2(null);
		return new PageVO(list, page.getTotal(), pageIndex, pageSize);
	}

	public PageVO getAccounts(Integer pageIndex, Integer pageSize) {
		if(Objects.isNull(pageIndex)) pageIndex = 0;
		if(Objects.isNull(pageSize)) pageSize = 10;
		Page<UserAccountVO> page = PageHelper.startPage(pageIndex+1, pageSize);
		List<UserAccountVO> list = userAcountMapper.getAccounts();
		return new PageVO(list, page.getTotal(), pageIndex, pageSize);
	}

	public UserAccountVO getAccountByUserId(Integer userId) {
		return userAcountMapper.getAccountByUserId1(userId);
	}
}
