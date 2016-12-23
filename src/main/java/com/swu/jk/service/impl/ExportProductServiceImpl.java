
package com.swu.jk.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swu.jk.dao.ExportProductDao;
import com.swu.jk.dao.ExtEproductDao;
import com.swu.jk.domain.ExportProduct;
import com.swu.jk.pagination.Page;
import com.swu.jk.service.ExportProductService;

@Service
public class ExportProductServiceImpl implements ExportProductService{

	@Resource
	private ExportProductDao exportProductDao;
	@Resource
	private ExtEproductDao extEproductDao;
	
	@Override
	public List<ExportProduct> findPage(Page page) {
		return exportProductDao.findPage(page);
	}

	@Override
	public List<ExportProduct> find(Map params) {
		return exportProductDao.find(params);
	}

	@Override
	public ExportProduct get(Serializable id) {
		return exportProductDao.get(id);
	}

	@Override
	public void update(ExportProduct exportProduct) {
		exportProductDao.update(exportProduct);
	}

	@Override
	public void deleteById(Serializable id) {
		Serializable[] ids = {id};
		extEproductDao.deleteByExportProductId(ids);  //删除该货物下的附件
		exportProductDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		extEproductDao.deleteByExportProductId(ids);
		exportProductDao.delete(ids);
	}

	@Override
	public void insert(ExportProduct exportProduct) {
		exportProduct.setId(UUID.randomUUID().toString());
		
//		exportProductDao.insert(exportProduct);
	}

}
