package mybatis;

import java.util.List;
import java.util.Objects;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import com.dachen.demo.App;
import com.dachen.demo.mybatis.dao.primary.UserMapper;
import com.dachen.demo.mybatis.module.User;
import com.dachen.demo.mybatis.service.PrimaryServiceImpl;
import com.google.common.collect.Lists;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserMapperTest {

	@Autowired
	UserMapper userMapper;
	@Autowired
	PrimaryServiceImpl primaryServiceImpl;
	@Autowired
	SqlSessionFactory sqlSessionFactory;	

	@Test
	public void batchInsert() {
		Integer count = primaryServiceImpl.batchInsert();
		if (Objects.equals(count, 50))
			Assert.assertTrue(true);
	}

	@Test
	public void getUserById() {
		Integer id = 1452;
		User u = userMapper.getUserById(id);
		@SuppressWarnings("unused")
		User u1 = userMapper.getUserById(id );
		if (Objects.nonNull(u))
			Assert.assertTrue(Objects.equals(id, u.getId()));
	}
	
	@Test
	public void getUserById1() {
		Integer id = 11161;
		SqlSession session = sqlSessionFactory.openSession(true);
		User u = session.selectOne("com.dachen.demo.mybatis.dao.primary.UserMapper.getUserById", id);
		@SuppressWarnings("unused")
		User u1 = session.selectOne("com.dachen.demo.mybatis.dao.primary.UserMapper.getUserById", id);
		if (Objects.nonNull(u))
			Assert.assertTrue(Objects.equals(id, u.getId()));
	}


	@Test
	public void getUserByNameAndTime() {
		String name = "user_1382378278";
		Long time = null;
		List<User> userList = userMapper.selectUserByNameAndTime(name, time);
		if (!CollectionUtils.isEmpty(userList))
			Assert.assertTrue(true);
	}
	
	@Test
	public void insertUser() {
		User user = new User();
		user.setTelephone("1000000000");
		user.setCreateTime(System.currentTimeMillis());
		user.setName("xiaowang");
		user.setTitle("CEO000");
		user.setAge(20);
		user.setAddress("中国大陆");
		Integer count = userMapper.insertUser(user);
		if (Objects.equals(count, 1))
			Assert.assertTrue(true);		
	}
	
	@Test
	public void updateUser() {
		User user = new User();
		user.setTelephone("13823782781");
		user.setName("xiaowang");
		user.setTitle("CEO111");
		Integer count = userMapper.updateUser(user);
		if (Objects.equals(count, 1))
			Assert.assertTrue(true);		
	}
	
	@Test
	public void batchUpdate() {
		List<User> users = Lists.newArrayList();
		User user1 = new User();
		user1.setTelephone("13823782781");
		user1.setName("xiaowang");
		user1.setTitle("CEO");
		user1.setCreateTime(System.currentTimeMillis());
		users.add(user1);
		User user2 = new User();
		user2.setTelephone("13823782782");
		user2.setName("liming");
		user2.setTitle("总裁");
		user2.setCreateTime(System.currentTimeMillis());
		users.add(user2);
		Integer count = userMapper.batchUpdateUser(users);
		if (Objects.equals(count, 2))
			Assert.assertTrue(true);		
	}
	

}
