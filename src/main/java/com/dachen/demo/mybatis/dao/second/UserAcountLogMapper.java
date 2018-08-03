package com.dachen.demo.mybatis.dao.second;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;

import com.dachen.demo.mybatis.module.UserAccountLog;
import com.dachen.demo.mybatis.module.UserAccountLogVO;
@Mapper
public interface UserAcountLogMapper {
	
	@Insert("<script>"
        + "insert into demo_user_account_log(id,userId,code,amount,createTime)"
		+" values "
        + "<foreach item='log' collection='accountLogs' index='index' separator=','>"
        + "(#{log.id},#{log.userId},#{log.code},#{log.amount},#{log.createTime})"
        + "</foreach>"
    + "</script>")
	Integer batchInsert(@Param("accountLogs") List<UserAccountLog> accountLogs);
	
	@Delete({
		"delete from demo_user_account_log"
	})	
	Integer clearData();
	
	List<UserAccountLogVO> selectLogsByUserId(@Param("userId")Integer userId);
	
	List<UserAccountLogVO> selectLogsByUserId1(@Param("userId")Integer userId);
	
	
	@SelectProvider(type=UserAccountLogSqlProvider.class, method="selectLogsByUserId2")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="userId", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="amount", property="amount", jdbcType=JdbcType.BIGINT),
        @Result(column="createTime", property="createTime", jdbcType=JdbcType.BIGINT),
        @Result(column="userId",property="userAccount",one=@One(select="com.dachen.demo.mybatis.dao.second.UserAcountMapper.getUserAccountByUserId"))
    })
	List<UserAccountLogVO> selectLogsByUserId2(@Param("userId")Integer userId);
	
}
