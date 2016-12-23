package com.swu.jk.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swu.jk.dao.ContractDao;
import com.swu.jk.dao.ContractProductDao;
import com.swu.jk.dao.ExtCproductDao;
import com.swu.jk.domain.Contract;
import com.swu.jk.pagination.Page;
import com.swu.jk.service.ContractProductService;
import com.swu.jk.service.ContractService;
import com.swu.jk.service.ExtCproductService;
import com.swu.jk.vo.ContractVO;

@Service
public class ContractServiceImpl implements ContractService{

	@Resource
	private ContractDao contractDao;
	@Resource
	private ContractProductDao contractProductDao;
	@Resource
	private ExtCproductDao extCproductDao;
	
	@Override
	public List<Contract> findPage(Page page) {
		return contractDao.findPage(page);
	}

	@Override
	public List<Contract> find(Map params) {
		return contractDao.find(params);
	}

	@Override
	public Contract get(Serializable id) {
		return contractDao.get(id);
	}

	@Override
	public void update(Contract contract) {
		contractDao.update(contract);
	}

	@Override
	public void deleteById(Serializable id) {
		Serializable[] contractIds = {id};
		extCproductDao.deleteByContractId(contractIds);         //合同下的附件
		contractProductDao.deleteByContractId(contractIds);     //合同下的货物
		contractDao.deleteById(id); //删除
	}

	@Override
	public void delete(Serializable[] ids) {
		extCproductDao.deleteByContractId(ids);
		contractProductDao.deleteByContractId(ids);
		contractDao.delete(ids); //删除
	}

	@Override
	public void insert(Contract contract) {
		contract.setId(UUID.randomUUID().toString());
		contract.setState(0);
		contractDao.insert(contract);
	}

	@Override
	public ContractVO view(String id) {
		return contractDao.view(id);
	}

}
