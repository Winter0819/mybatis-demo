package com.dachen.demo.mybatis.dao.second;

import java.util.Objects;

import org.apache.ibatis.jdbc.SQL;

public class UserAccountLogSqlProvider {
	public String selectLogsByUserId2(Integer userId) {
    	return new SQL(){
			{
				SELECT("id,userId,code,amount,createTime");
				FROM("demo_user_account_log");
				if(Objects.nonNull(userId))
				WHERE("userId = #{userId,jdbcType=INTEGER}");
				ORDER_BY("createTime desc");
				
			}
		}.toString();
    }
}
