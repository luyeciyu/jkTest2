package com.swu.jk.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swu.jk.dao.ContractDao;
import com.swu.jk.dao.ContractProductDao;
import com.swu.jk.dao.ExportDao;
import com.swu.jk.dao.ExportProductDao;
import com.swu.jk.dao.ExtCproductDao;
import com.swu.jk.dao.ExtEproductDao;
import com.swu.jk.domain.Contract;
import com.swu.jk.domain.ContractProduct;
import com.swu.jk.domain.Export;
import com.swu.jk.domain.ExportProduct;
import com.swu.jk.domain.ExtCproduct;
import com.swu.jk.domain.ExtEproduct;
import com.swu.jk.pagination.Page;
import com.swu.jk.service.ExportService;
import com.swu.jk.util.UtilFuns;
import com.swu.jk.vo.ExportVO;

@Service
public class ExportServiceImpl implements ExportService{

	@Resource
	private ExportDao exportDao;
	@Resource
	private ExportProductDao exportProductDao;
	@Resource
	private ExtEproductDao extEproductDao;
	@Resource
	private ContractProductDao contractProductDao;
	@Resource 
	private ExtCproductDao extCproductDao;
	@Resource 
	private ContractDao contractDao;
	
	@Override
	public List<Export> findPage(Page page) {
		return exportDao.findPage(page);
	}

	@Override
	public List<Export> find(Map params) {
		return exportDao.find(params);
	}

	@Override
	public Export get(Serializable id) {
		return exportDao.get(id);
	}

	@Override
	public void update(Export export) {
		exportDao.update(export);
	}

	@Override
	public void deleteById(Serializable id) {
		Serializable[] ids = {id};
		extEproductDao.deleteByExportId(ids);         //删除该报运单下的附件
		exportProductDao.deleteByExportId(ids);       //删除该报运单下的货物
		exportDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		System.out.println(ids.toString());
		extEproductDao.deleteByExportId(ids);
		exportProductDao.deleteByExportId(ids);
		exportDao.delete(ids);
	}

	@Override
	public void insert(Export export) {
		export.setId(UUID.randomUUID().toString());
//		exportDao.insert(export);
	}

	@Override
	public ExportVO view(String id) {
		ExportVO exportVO = exportDao.view(id);
		return exportVO;
	}

	@Override
	public void saveContractToExport(String[] ids) {
		//List<ContractProduct> contractProducts = new ArrayList<ContractProduct>();
		List<ContractProduct> contractProducts = contractProductDao.findContractProductByContractId(ids);
		
		
		Export export = new Export();           // 报运单
		export.setId(UUID.randomUUID().toString());
		
		List<ExportProduct> exportProducts = new ArrayList<ExportProduct>(); //该报运单下单货物
		List<ExtEproduct> eproducts = new ArrayList<ExtEproduct>();          //某个货物下的附件
		
		ExportProduct exportProduct = null;
		ExtEproduct eproduct = null;
		ContractProduct contractProduct = null;
		Contract contract = null;
		ExtCproduct extCproduct = null;
		
		String contractIds = "";
		String customerContracts = "";
		int _rate = 0;
		int index = 0;
		
		if(contractProducts != null ){
			Iterator<ContractProduct> iterator = contractProducts.iterator();
			while(iterator.hasNext()){
				exportProduct = new ExportProduct();
				contractProduct = iterator.next();
				
				contract = contractDao.get(contractProduct.getContractId());
				
				if(contractIds.indexOf(contractProduct.getContractId()) == -1){    //排除已经在串中的
					contractIds += contractProduct.getContractId() + ",";                //拼接合同ID
				}
				//拼接合同或确认号 
				if(customerContracts.indexOf(contract.getContractNo()) == -1){
					customerContracts += contract.getContractNo() + ",";
				}
				
				exportProduct.setExportId(export.getId());
				exportProduct.setId(UUID.randomUUID().toString());
				exportProduct.setContractProductId(contractProduct.getId());
				exportProduct.setFactoryId(contractProduct.getFactoryId());
				exportProduct.setContractId(contractProduct.getContractId());
				//exportProduct.setContractNo();     //合同号
				
				exportProduct.setProductName(contractProduct.getProductName());
				exportProduct.setProductNo(contractProduct.getProductNo());
				exportProduct.setProductImage(contractProduct.getProductImage());
				exportProduct.setProductDesc(contractProduct.getProductDesc());
				exportProduct.setLoadingRate(contractProduct.getLoadingRate());
				exportProduct.setPackingUnit(contractProduct.getPackingUnit());
				exportProduct.setOutNumber(contractProduct.getOutNumber());
				
				//exportProduct.setFinished(contractProduct.getf);
				
				//合同货物的走货状态
				if(contractProduct.getOutNumber() > 0){
					//如果出过货，则界面显示剩余的货
					
					//System.out.println(contractProduct.getCnumber());
					exportProduct.setCnumber(contractProduct.getCnumber() - contractProduct.getOutNumber());
					if(contractProduct.getLoadingRate() != null && contractProduct.getCnumber() != null){
						_rate = Integer.parseInt(contractProduct.getLoadingRate().substring(contractProduct.getLoadingRate().indexOf("/")+1));
						exportProduct.setBoxNum(exportProduct.getCnumber()/_rate);										//箱数=数量/装率的分母
					}
				}else{
					exportProduct.setCnumber(contractProduct.getCnumber());
					exportProduct.setBoxNum(contractProduct.getBoxNum());
				}
				//System.out.println(exportProduct.getCnumber());
				//????
				contractProduct.setOutNumber(contractProduct.getCnumber());
				contractProduct.setFinished(true);	//默认全部出货
				
				
				exportProduct.setGrossWeight(contractProduct.getGrossWeight());
				exportProduct.setNetWeight(contractProduct.getNetWeight());
				exportProduct.setSizeLength(contractProduct.getSizeLength());
				exportProduct.setSizeWidth(contractProduct.getSizeWidth());
				exportProduct.setSizeHeight(contractProduct.getSizeHeight());
				exportProduct.setExPrice(contractProduct.getExPrice());
				exportProduct.setPrice(contractProduct.getPrice());
				exportProduct.setTax(contractProduct.getPrice());		//收购单价.含税=合同单价
				exportProduct.setAmount(contractProduct.getAmount());
				
				exportProduct.setOrderNo(index+1);	//根据list的顺序排序
				
//				Serializable[] contractProductId = {contractProduct.getId()};
//				List<ExtCproduct> cproductsList = extCproductDao.findByContractProductById(contractProductId);
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("contractProductId", contractProduct.getId());
				List<ExtCproduct> cproductsList = extCproductDao.find(map);           //查询该货物下的附件
				
				
				Iterator<ExtCproduct> iterator2 = cproductsList.iterator();
				
				while(iterator2.hasNext()){
					eproduct = new ExtEproduct();
					extCproduct = iterator2.next();
					
					eproduct.setId(UUID.randomUUID().toString());
					eproduct.setExportProductId(exportProduct.getId());
					eproduct.setFactoryId(extCproduct.getFactoryId());
					
					
					eproduct.setAccessories(extCproduct.isAccessories());
					eproduct.setAmount(extCproduct.getAmount());
					eproduct.setBoxNum(extCproduct.getBoxNum());
					eproduct.setCnumber(extCproduct.getCnumber());
					eproduct.setCostPrice(extCproduct.getCostPrice());
					eproduct.setCtype(extCproduct.getCtype());
					eproduct.setCunit(extCproduct.getCunit());
					eproduct.setExPrice(extCproduct.getExPrice());
					eproduct.setExUnit(extCproduct.getExUnit());
					eproduct.setFinished(extCproduct.isFinished());
					eproduct.setGrossWeight(extCproduct.getGrossWeight());
					eproduct.setLoadingRate(extCproduct.getLoadingRate());
					eproduct.setNetWeight(extCproduct.getNetWeight());
					eproduct.setNoTax(extCproduct.getNoTax());
					eproduct.setOutNumber(extCproduct.getOutNumber());
					eproduct.setPackingUnit(extCproduct.getPackingUnit());
					eproduct.setPrice(extCproduct.getPrice());
					eproduct.setProductDesc(extCproduct.getProductDesc());
					eproduct.setProductImage(extCproduct.getProductImage());
					eproduct.setProductName(extCproduct.getProductName());
					eproduct.setProductNo(extCproduct.getProductNo());
					eproduct.setProductRequest(extCproduct.getProductRequest());
					eproduct.setSizeHeight(extCproduct.getSizeHeight());
					eproduct.setSizeLength(extCproduct.getSizeLength());
					eproduct.setSizeWidth(extCproduct.getSizeWidth());
					eproduct.setExPrice(extCproduct.getExPrice());
					eproduct.setTax(extCproduct.getTax());
					
					eproducts.add(eproduct);
				}
				
				exportProducts.add(exportProduct);
				index++;
			}
			
			
			export.setContractIds(UtilFuns.delLastChar(contractIds));
			export.setCustomerContract(UtilFuns.delLastChar(customerContracts));
			
			exportDao.insert(export);                         //报运单
			exportProductDao.insertBatch(exportProducts);     //货物
			extEproductDao.insertBatch(eproducts);            //附件
			
			
			
		}
		
		
	}

}
