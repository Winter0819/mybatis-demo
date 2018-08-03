package com.dachen.demo.mybatis.dao.second;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.dachen.demo.mybatis.module.UserAccount;
import com.dachen.demo.mybatis.module.UserAccountVO;

@Mapper
public interface UserAcountMapper {
	
	@Update({"insert into demo_user_account(id,userId,amount,createTime,updateTime)",
		"values(#{id},#{userId},#{amount},#{createTime},#{updateTime})"
	})
	Integer insertUserAccount(UserAccount account);
	
	@Update({
        "update demo_user_account",
        "set amount = amount+ #{amount,jdbcType=BIGINT},",
        "updateTime = #{updateTime,jdbcType=BIGINT}",
        "where userId = #{userId,jdbcType=INTEGER}"
    })
	Integer updateUserAccount(@Param("amount") Long amount,@Param("userId") Integer userId,@Param("updateTime") Long updateTime);
	
	@Select({
		"select id,userId,amount,createTime,updateTime from demo_user_account",
		"where userId = #{userId,jdbcType=INTEGER}"
		
	})
	UserAccount getUserAccountByUserId(@Param("userId")Integer userId);
	
	UserAccountVO getAccountByUserId(@Param("userId")Integer userId);
	
	
	
	UserAccountVO getAccountByUserId1(@Param("userId")Integer userId);

	List<UserAccountVO> getAccounts();
}
