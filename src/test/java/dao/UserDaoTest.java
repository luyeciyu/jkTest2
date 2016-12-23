package dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.swu.jk.dao.UserDao;
import com.swu.jk.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class UserDaoTest {

	@Resource
	private UserDao userDao;

	@Test
	public void login(){
		User user = new User("张三", "123456");
		System.out.println(userDao.login(user));
	}
}
