package com.swu.jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.swu.jk.domain.Export;
import com.swu.jk.pagination.Page;
import com.swu.jk.vo.ExportVO;

public interface ExportService {
	public List<Export> findPage(Page page);
	public List<Export> find(Map params);
	public Export get(Serializable id);
	public void update(Export export);
	public void deleteById(Serializable id);
	public void delete(Serializable[] ids);
	public void insert(Export export);
	public List<ExportVO> view(String id);
	public void saveContractToExport(String[] ids);   
}
