package com.swu.jk.dao;

import java.io.Serializable;

import com.swu.jk.domain.ExtCproduct;

public interface ExtCproductDao extends BaseDao<ExtCproduct>{
	public void deleteByContractProductById(Serializable[] ids);
	public void deleteByContractId(Serializable[] contractIds);
}
