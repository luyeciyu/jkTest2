package com.swu.jk.dao;

import java.io.Serializable;
import java.util.List;

import com.swu.jk.domain.ExtEproduct;

public interface ExtEproductDao extends BaseDao<ExtEproduct>{
	public void deleteByExportProductId(Serializable[] ids); 
	public void deleteByExportId(Serializable[] ids);
	public void insertBatch(List<ExtEproduct> eproducts);
}
