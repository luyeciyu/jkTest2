package com.swu.jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.swu.jk.domain.ExportProduct;
import com.swu.jk.pagination.Page;

public interface ExportProductService {
	public List<ExportProduct> findPage(Page page);
	public List<ExportProduct> find(Map params);
	public ExportProduct get(Serializable id);
	public void update(ExportProduct exportProduct);
	public void deleteById(Serializable id);
	public void delete(Serializable[] ids);
	public void insert(ExportProduct exportProduct);
}
