package com.swu.jk.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.swu.jk.dao.ExtEproductDao;
import com.swu.jk.domain.ExtEproduct;

@Repository
public class ExtEproductDaoImpl extends BaseDaoImpl<ExtEproduct> implements ExtEproductDao{

	public ExtEproductDaoImpl() {
		super.setNs("com.swu.jk.mapper.ExtEproductMapper");
	}
	@Override
	public void deleteByExportProductId(Serializable[] ids) {
		super.getSqlSession().delete(super.getNs() + ".deleteByExportProductId", ids);
	}

	@Override
	public void deleteByExportId(Serializable[] ids) {
		super.getSqlSession().delete(super.getNs() + ".deleteByExportId", ids);
	}
	
}
