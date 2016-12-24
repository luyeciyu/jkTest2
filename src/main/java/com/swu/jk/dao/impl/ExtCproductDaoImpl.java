package com.swu.jk.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.swu.jk.dao.ExtCproductDao;
import com.swu.jk.domain.ExtCproduct;

@Repository
public class ExtCproductDaoImpl extends BaseDaoImpl<ExtCproduct> implements ExtCproductDao{

	public ExtCproductDaoImpl() {
		super.setNs("com.swu.jk.mapper.ExtCproductMapper");
	}
	
	@Override
	public void deleteByContractProductById(Serializable[] ids) {
		super.getSqlSession().delete(super.getNs() + ".deleteByContractProductById", ids);
		
	}

	@Override
	public void deleteByContractId(Serializable[] contractIds) {
		super.getSqlSession().delete(super.getNs() + ".deleteByContractId", contractIds);
	}

	@Override
	public List<ExtCproduct> findByContractProductById(Serializable[] ids) {
		List<ExtCproduct> extCproducts = super.getSqlSession().selectList(super.getNs() + ".findByContractProductById", ids);
		return extCproducts;
	}

}
