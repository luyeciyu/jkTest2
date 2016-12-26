package dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.swu.jk.dao.ExportDao;
import com.swu.jk.domain.ExportProduct;
import com.swu.jk.vo.ExportProductVO;
import com.swu.jk.vo.ExportVO;
import com.swu.jk.vo.ExtEproductVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class ExportDaoTest {
	@Resource
	private ExportDao exportDao;
	
	@Test
	public void view_test(){
		ExportVO exportVO = exportDao.view("f383e861-9cfc-4c5b-845e-1dbabb52e313");
		/*List<ExportProduct> exportProductVOs = exportVO.getExportProducts();
		for(ExportProduct exportProduct : exportProductVOs){
			System.out.println(exportProduct.getExportId());
			
		}*/
		
		/*List<ExportProductVO> exportProductVOs = exportVO.getExportProducts();
		for(ExportProductVO exportProduct : exportProductVOs){
			System.out.println(exportProduct.getProductDesc());
			System.out.println("----");
			for(ExtEproductVO eproductVO : exportProduct.getExtEproducts())
				System.out.println(eproductVO.getProductDesc());
			System.out.println();
			System.out.println();
			System.out.println();
		}*/

	}
}
