package com.swu.jk.dao;

import java.io.Serializable;
import java.util.List;

import com.swu.jk.domain.ExportProduct;

public interface ExportProductDao extends BaseDao<ExportProduct>{
	public void deleteByExportId(Serializable[] ids);
	public List<ExportProduct> findByExportId(Serializable[] ids);
	public void insertBatch(List<ExportProduct> exportProducts);
}
