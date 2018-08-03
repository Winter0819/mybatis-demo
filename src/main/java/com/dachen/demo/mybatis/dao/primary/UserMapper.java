package com.dachen.demo.mybatis.dao.primary;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dachen.demo.mybatis.module.User;

@Mapper
public interface UserMapper {
	
	@Insert("<script>"
        + "insert into demo_user(name,age,telephone,title,address,createTime) "
		+" values "
        + "<foreach item='user' index='index' collection='userList'  separator=','>"
        + "(#{user.name},#{user.age},#{user.telephone},#{user.title},#{user.address},#{user.createTime})"
        + "</foreach>"
    + "</script>")
	Integer batchInsert(@Param("userList") List<User> userList);
	
	Integer batchXmlInsert(@Param("userList") List<User> userList);

	@Select({
		"select",
        "id,name,age,telephone,title,address,createTime",
        "from demo_user u",
        "where u.id = #{id,jdbcType=INTEGER}"
    })
	User getUserById(@Param("id")Integer id);	
	
	List<User> selectUserByNameAndTime(@Param("name")String name,@Param("time")Long time);
	
	
	Integer batchUpdateUser(@Param("userList")List<User> userList);
	
	
	Integer updateUser(@Param("user") User user);
	
	Integer insertUser(@Param("user") User user);
	
	
	
}
