package com.swu.jk.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swu.jk.dao.FactoryDao;
import com.swu.jk.domain.Factory;
import com.swu.jk.pagination.Page;
import com.swu.jk.service.FactoryService;

@Service
public class FactoryServiceImpl implements FactoryService{

	@Resource
	private FactoryDao factoryDao;
	
	@Override
	public List<Factory> findPage(Page page) {
		return factoryDao.findPage(page);
	}

	@Override
	public List<Factory> find(Map paraMap) {
		return factoryDao.find(paraMap);
	}

	@Override
	public Factory get(Serializable id) {
		return factoryDao.get(id);
	}

	@Override
	public void insert(Factory factory) {
		factory.setId(UUID.randomUUID().toString());
		factory.setState("1");								//默认启用状态
		factoryDao.insert(factory);
	}

	@Override
	public void update(Factory factory) {
		factoryDao.update(factory);
	}

	@Override
	public void deleteById(Serializable id) {
		factoryDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		factoryDao.delete(ids);
	}

	@Override
	public void start(Serializable[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", 1);     //1启用
		map.put("ids", ids);
		factoryDao.updateState(map);
	}

	@Override
	public void stop(Serializable[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", 0);
		map.put("ids", ids);
		factoryDao.updateState(map);
	}

	@Override
	public List<Factory> getFactoryList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", 1);
		List<Factory> factories = factoryDao.find(map);
		return factories;
	}

}
