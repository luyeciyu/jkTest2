package com.swu.jk.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.swu.jk.dao.ExtEproductDao;
import com.swu.jk.domain.ExtEproduct;
import com.swu.jk.domain.SysCode;
import com.swu.jk.pagination.Page;
import com.swu.jk.service.ExtEproductService;
@Service
public class ExtEproductServiceImpl implements ExtEproductService{

	private ExtEproductDao extEproductDao;
	
	@Override
	public List<ExtEproduct> findPage(Page page) {
		return extEproductDao.findPage(page);
	}

	@Override
	public List<ExtEproduct> find(Map paraMap) {
		return extEproductDao.find(paraMap);
	}

	@Override
	public ExtEproduct get(Serializable id) {
		return extEproductDao.get(id);
	}

	@Override
	public void insert(ExtEproduct extEproduct) {
		extEproduct.setId(UUID.randomUUID().toString());
		
//		extEproductDao.insert(extEproduct);
	}

	@Override
	public void update(ExtEproduct extEproduct) {
		extEproductDao.update(extEproduct);
		
	}

	@Override
	public void deleteById(Serializable id) {
		extEproductDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		extEproductDao.delete(ids);
	}

	@Override
	public List<SysCode> getCtypeList() {
		return null;
	}

}
