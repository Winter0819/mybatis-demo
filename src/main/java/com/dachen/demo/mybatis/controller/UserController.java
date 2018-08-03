package com.dachen.demo.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dachen.demo.mybatis.module.User;
import com.dachen.demo.mybatis.service.PrimaryServiceImpl;
import com.dachen.demo.util.JSONMessage;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("primary")
public class UserController {
	
	@Autowired
	PrimaryServiceImpl primaryServiceImpl;	

	@ApiOperation(value = "batchInsert",notes = "成功返回{\"data\": {},\"resultCode\": 1,\"resultMsg\": xx}",response=Integer.class)
    @RequestMapping(value = "/user/batchInsert",method = {RequestMethod.POST})
	public JSONMessage batchInsert() {
		return JSONMessage.success(primaryServiceImpl.batchInsert());
	}
	
	@ApiOperation(value = "batchXmlInsert",notes = "成功返回{\"data\": {},\"resultCode\": 1,\"resultMsg\": xx}",response=Integer.class)
    @RequestMapping(value = "/user/batchXmlInsert",method = {RequestMethod.POST})
	public JSONMessage batchXmlInsert() {
		return JSONMessage.success(primaryServiceImpl.batchXmlInsert());
	}
	
	@ApiOperation(value = "getUserById",notes = "成功返回{\"data\": {},\"resultCode\": 1,\"resultMsg\": xx}",response=User.class)
    @RequestMapping(value = "/user/{id}",method = {RequestMethod.GET})
	public JSONMessage getUserById(@PathVariable Integer id) {
		return JSONMessage.success(primaryServiceImpl.getUserById(id));
	}
}
