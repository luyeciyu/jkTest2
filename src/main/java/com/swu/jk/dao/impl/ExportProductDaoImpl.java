package com.swu.jk.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.swu.jk.dao.ExportProductDao;
import com.swu.jk.domain.ExportProduct;

@Repository
public class ExportProductDaoImpl extends BaseDaoImpl<ExportProduct> implements ExportProductDao{

	public ExportProductDaoImpl() {
		super.setNs("com.swu.jk.mapper.ExportProductMapper");
	}
	@Override
	public void deleteByExportId(Serializable[] ids) {
		super.getSqlSession().delete(super.getNs() + ".deleteByExportId", ids);
	}
	@Override
	public List<ExportProduct> findByExportId(Serializable[] ids) {
		List<ExportProduct> liExportProducts = super.getSqlSession().selectList(super.getNs() + ".findByExportId", ids);
		return null;
	}
	
}
