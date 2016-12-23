package com.swu.jk.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swu.jk.dao.ContractProductDao;
import com.swu.jk.dao.ExtCproductDao;
import com.swu.jk.domain.ContractProduct;
import com.swu.jk.pagination.Page;
import com.swu.jk.service.ContractProductService;
import com.swu.jk.util.UtilFuns;

@Service
public class ContractProductServiceImpl implements ContractProductService{

	@Resource
	private ContractProductDao contractProductDao;
	@Resource 
	private ExtCproductDao extCproductDao;
	
	@Override
	public List<ContractProduct> findPage(Page page) {
		return contractProductDao.findPage(page);
	}

	@Override
	public List<ContractProduct> find(Map params) {
		return contractProductDao.find(params);
	}

	@Override
	public ContractProduct get(Serializable id) {
		return contractProductDao.get(id);
	}

	@Override
	public void update(ContractProduct contractProduct) {
		contractProductDao.update(contractProduct);
	}

	@Override
	public void deleteById(Serializable id) {
		Serializable[] ids = {id};
		extCproductDao.deleteByContractProductById(ids);
		contractProductDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		extCproductDao.deleteByContractProductById(ids);
		contractProductDao.delete(ids);
	}

	@Override
	public void insert(ContractProduct contractProduct) {
		contractProduct.setId(UUID.randomUUID().toString());
		//自动计算总金额=数量*单价		...修改，删除；同步合同总金额
		if(UtilFuns.isNotEmpty(contractProduct.getPrice())&& UtilFuns.isNotEmpty(contractProduct.getCnumber())){
			
			//?????????????
			BigDecimal b = new BigDecimal(contractProduct.getCnumber());
			b.multiply(contractProduct.getPrice());
			contractProduct.setAmount(b);
			
			
			//contractProduct.setAmount(contractProduct.getCnumber()*contractProduct.getPrice());
		}
		contractProductDao.insert(contractProduct);
	}

}
