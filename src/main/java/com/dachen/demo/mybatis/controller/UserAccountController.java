package com.dachen.demo.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dachen.demo.mybatis.service.UserAccountServiceImpl;
import com.dachen.demo.util.JSONMessage;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("sencond")
public class UserAccountController {
	
	@Autowired
	UserAccountServiceImpl userAccountServiceImpl;	

	@ApiOperation(value = "batchInsert",notes = "成功返回{\"data\": {},\"resultCode\": 1,\"resultMsg\": xx}",response=Integer.class)
    @RequestMapping(value = "/log/batchInsert/{userId}/{amount}",method = {RequestMethod.POST})
	public JSONMessage batchInsert(@PathVariable Integer userId,@PathVariable Long amount) {
		return JSONMessage.success(userAccountServiceImpl.batchInsert(userId,amount));
	}
	
	@RequestMapping(value = "/log/getLogs/{userId}/{pageIndex}/{pageSize}",method = {RequestMethod.GET})
	public JSONMessage getLogs(@PathVariable Integer userId,@PathVariable Integer pageIndex,@PathVariable Integer pageSize) {
		return JSONMessage.success(userAccountServiceImpl.getLogs(userId,pageIndex,pageSize));
	}
	
	@RequestMapping(value = "/account/getAccountByUserId/{userId}",method = {RequestMethod.GET})
	public JSONMessage getAccountByUserId(@PathVariable Integer userId) {
		return JSONMessage.success(userAccountServiceImpl.getAccountByUserId(userId));
	}
	
	@RequestMapping(value = "/account/getAccounts/{pageIndex}/{pageSize}",method = {RequestMethod.GET})
	public JSONMessage getAccounts(@PathVariable Integer pageIndex,@PathVariable Integer pageSize) {
		return JSONMessage.success(userAccountServiceImpl.getAccounts(pageIndex,pageSize));
	}
}
