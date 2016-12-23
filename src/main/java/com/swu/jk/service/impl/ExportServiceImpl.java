package com.swu.jk.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swu.jk.dao.ExportDao;
import com.swu.jk.dao.ExportProductDao;
import com.swu.jk.dao.ExtEproductDao;
import com.swu.jk.domain.Export;
import com.swu.jk.pagination.Page;
import com.swu.jk.service.ExportService;
import com.swu.jk.vo.ExportVO;

@Service
public class ExportServiceImpl implements ExportService{

	@Resource
	private ExportDao exportDao;
	@Resource
	private ExportProductDao exportProductDao;
	@Resource
	private ExtEproductDao extEproductDao;
	
	@Override
	public List<Export> findPage(Page page) {
		return exportDao.findPage(page);
	}

	@Override
	public List<Export> find(Map params) {
		return exportDao.find(params);
	}

	@Override
	public Export get(Serializable id) {
		return exportDao.get(id);
	}

	@Override
	public void update(Export export) {
		exportDao.update(export);
	}

	@Override
	public void deleteById(Serializable id) {
		Serializable[] ids = {id};
		extEproductDao.deleteByExportId(ids);         //删除该报运单下的附件
		exportProductDao.deleteByExportId(ids);       //删除该报运单下的货物
		exportDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		extEproductDao.deleteByExportId(ids);
		exportProductDao.deleteByExportId(ids);
		exportDao.delete(ids);
	}

	@Override
	public void insert(Export export) {
		export.setId(UUID.randomUUID().toString());
//		exportDao.insert(export);
	}

	@Override
	public List<ExportVO> view(String id) {
		List<ExportVO> exportVOs = exportDao.view(id);
		return exportVOs;
	}

	@Override
	public void contractSave(String[] ids) {
		
	}

}
