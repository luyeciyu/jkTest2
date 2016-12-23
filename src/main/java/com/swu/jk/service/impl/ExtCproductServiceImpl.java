package com.swu.jk.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swu.jk.dao.ExtCproductDao;
import com.swu.jk.domain.ExtCproduct;
import com.swu.jk.domain.SysCode;
import com.swu.jk.pagination.Page;
import com.swu.jk.service.ExtCproductService;
import com.swu.jk.util.UtilFuns;

@Service
public class ExtCproductServiceImpl implements ExtCproductService{

	@Resource
	private ExtCproductDao extCproductDao;
	
	@Override
	public List<ExtCproduct> findPage(Page page) {
		return extCproductDao.findPage(page);
	}

	@Override
	public List<ExtCproduct> find(Map paraMap) {
		return extCproductDao.find(paraMap);
	}

	@Override
	public ExtCproduct get(Serializable id) {
		return extCproductDao.get(id);
	}

	@Override
	public void insert(ExtCproduct extCproduct) {
		extCproduct.setId(UUID.randomUUID().toString());
		
		//自动计算总金额=数量*单价		...修改，删除；同步合同总金额
		if(UtilFuns.isNotEmpty(extCproduct.getPrice())&& UtilFuns.isNotEmpty(extCproduct.getCnumber())){
			
			//?????????????
			BigDecimal b = new BigDecimal(extCproduct.getCnumber());
			b.multiply(extCproduct.getPrice());
			extCproduct.setAmount(b);
		
			//extCproduct.setAmount(extCproduct.getCnumber()*extCproduct.getPrice());
		}
		
		extCproductDao.insert(extCproduct);
	}

	@Override
	public void update(ExtCproduct extCproduct) {
		extCproductDao.update(extCproduct);
	}

	@Override
	public void deleteById(Serializable id) {
		extCproductDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		extCproductDao.delete(ids);
	}

	@Override
	public List<SysCode> getCtypeList() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("", "");
		return null;
	}

}
