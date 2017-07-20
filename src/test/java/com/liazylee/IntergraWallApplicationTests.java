package com.liazylee;

import com.liazylee.Mysql.AppAgentFacede;
import com.liazylee.Mysql.UsersAgentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntergraWallApplicationTests {
	@Autowired
	UsersAgentMapper usersagentMapper;
	@Autowired
	AppAgentFacede appAgentFacede;
	@Test
	public void contextLoads() {

		try {
			Map<String, Object> usersagentparams = new HashMap<String, Object>();
			usersagentparams.put("udid", "2121");
			usersagentparams.put("multipleurl", "1354");
			usersagentparams.put("isAcitve", 0);
			usersagentparams.put("iscallback", 0);
			usersagentparams.put("appid", "354");
			usersagentparams.put("dateCreated", new Date());
			usersagentMapper.insert(usersagentparams);
		}catch (Exception e){
			e.printStackTrace();
		}finally {

		}
	}

}
