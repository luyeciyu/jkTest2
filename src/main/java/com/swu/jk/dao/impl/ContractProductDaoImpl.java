package com.swu.jk.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.swu.jk.dao.ContractProductDao;
import com.swu.jk.domain.ContractProduct;
import com.swu.jk.vo.ContractProductVO;

@Repository
public class ContractProductDaoImpl extends BaseDaoImpl<ContractProduct> implements ContractProductDao{

	public ContractProductDaoImpl() {
		super.setNs("com.swu.jk.mapper.ContractProductMapper");
	}

	@Override
	public void deleteByContractId(Serializable[] ids) {
		super.getSqlSession().delete(super.getNs() + ".deleteByContractId", ids);
	}

	@Override
	public List<ContractProductVO> findOutProductData(String date) {
		List<ContractProductVO> contractProductVOs = super.getSqlSession().selectList(super.getNs() + ".findOutProductData", date);
		
		return contractProductVOs;
	}
}
