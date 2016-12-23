package com.swu.jk.dao;

import java.io.Serializable;

import com.swu.jk.domain.ExtEproduct;

public interface ExtEproductDao extends BaseDao<ExtEproduct>{
	public void deleteByExportProductId(Serializable[] ids); 
	public void deleteByExportId(Serializable[] ids);
}
