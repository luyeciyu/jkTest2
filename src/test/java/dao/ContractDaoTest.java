package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.swu.jk.dao.ContractDao;
import com.swu.jk.domain.Contract;
import com.swu.jk.vo.ContractVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class ContractDaoTest {
	
	@Autowired
	ContractDao contractDao;
	
	@Test
	public void test_get(){
		Map<String , Object> map = new HashMap<String, Object>();
		Contract contract =  contractDao.get("4028817a3357462e0133591b86ec0002");
		System.out.println(contract);
	}
	
	@Test
	public void test_find(){
		Map<String , Object> map = new HashMap<String, Object>();
		List<Contract> contract =  contractDao.find(null);
		for(Contract contract2 : contract){
			System.out.println(contract2);
		}
//		System.out.println(contract);
	}
	
	
	@Test
	public void test_view(){
		Map<String , Object> map = new HashMap<String, Object>();
		ContractVO contractVO = contractDao.view("4028817a33d4f8b40133d9878e88000d");
		
		System.out.println(contractVO);

	}
}
