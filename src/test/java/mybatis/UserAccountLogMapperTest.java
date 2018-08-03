package mybatis;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import com.dachen.demo.App;
import com.dachen.demo.mybatis.dao.second.UserAcountLogMapper;
import com.dachen.demo.mybatis.module.UserAccountLog;
import com.dachen.demo.mybatis.module.UserAccountLogVO;
import com.dachen.demo.mybatis.service.UserAccountServiceImpl;
import com.dachen.demo.util.IDGenerator;
import com.google.common.collect.Lists;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Service
public class UserAccountLogMapperTest {

	@Autowired
	UserAcountLogMapper userAcountLogMapper;
	
	@Autowired
	IDGenerator idGenerator;
	
	@Autowired
	UserAccountServiceImpl userAccountServiceImpl;

	@Test
	public void batchInsert() {
		List<UserAccountLog> logs = Lists.newArrayListWithExpectedSize(2);
		UserAccountLog log1 = new UserAccountLog();
	    log1.setId(idGenerator.nextId());
	    log1.setAmount(1000L);
	    log1.setUserId(1);
	    log1.setCode("5");
		log1.setCreateTime(System.currentTimeMillis());
		logs.add(log1);
		UserAccountLog log2 = new UserAccountLog();
		log2.setId(idGenerator.nextId());
		log2.setAmount(1000L);
		log2.setUserId(2);
	    log2.setCode("6");
	    log2.setCreateTime(System.currentTimeMillis());
	    logs.add(log2);
	    userAcountLogMapper.batchInsert(logs);
	}

	
	@Test
	public void batchInsert1() {
		userAccountServiceImpl.batchInsert(1453,1000L);
	}
	
	
	@Test
	public void selectLogsByUserId() {
		Integer userId = 1351;
		List<UserAccountLogVO> logs =  userAcountLogMapper.selectLogsByUserId(userId);
		System.out.println(logs.size());
	}
	
	
	@Test
	public void selectLogsByUserId1() {
		Integer userId = 1351;
		List<UserAccountLogVO> logs =  userAcountLogMapper.selectLogsByUserId1(userId);
		System.out.println(logs.size());
	}
	

}
