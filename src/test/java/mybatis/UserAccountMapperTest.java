package mybatis;


import java.util.List;
import java.util.Objects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dachen.demo.App;
import com.dachen.demo.mybatis.dao.second.UserAcountMapper;
import com.dachen.demo.mybatis.module.UserAccount;
import com.dachen.demo.mybatis.module.UserAccountLog;
import com.dachen.demo.mybatis.module.UserAccountVO;
import com.dachen.demo.util.IDGenerator;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserAccountMapperTest {

	@Autowired
	UserAcountMapper userAcountMapper;
	@Autowired
	IDGenerator idGenerator;
	
	@Test
	public void insert() {
		Long id = idGenerator.nextId();
		UserAccount account = new UserAccount();
		account.setId(id);
		account.setUserId(1352);
		account.setAmount(1000L);
		account.setCreateTime(System.currentTimeMillis());
		account.setUpdateTime(System.currentTimeMillis());
		if(Objects.nonNull(userAcountMapper.getUserAccountByUserId(account.getUserId()))){
			userAcountMapper.updateUserAccount(account.getAmount(),account.getUserId(), System.currentTimeMillis());
		}else{
			userAcountMapper.insertUserAccount(account);
		}
	}
	
	@Test
	public void selectLogsByUserId() {
		Integer userId = 1351;
		UserAccountVO accountVO =  userAcountMapper.getAccountByUserId(userId);
		List<UserAccountLog> logs = accountVO.getLogs();
		System.out.println(logs.size());
	}
	
	
	@Test
	public void selectLogsByUserId1() {
		Integer userId = 1351;
		UserAccountVO accountVO =  userAcountMapper.getAccountByUserId1(userId);
		List<UserAccountLog> logs = accountVO.getLogs();
		System.out.println(logs.size());
	}
				
	
	

}
