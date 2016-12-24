package com.swu.jk.dao;

import java.io.Serializable;
import java.util.List;

import com.swu.jk.domain.ExtCproduct;

public interface ExtCproductDao extends BaseDao<ExtCproduct>{
	public void deleteByContractProductById(Serializable[] ids);
	public void deleteByContractId(Serializable[] contractIds);
	public List<ExtCproduct> findByContractProductById(Serializable[] ids);
}
