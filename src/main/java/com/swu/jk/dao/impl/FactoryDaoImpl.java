package com.swu.jk.dao.impl;


import java.util.Map;

import org.springframework.stereotype.Repository;

import com.swu.jk.dao.FactoryDao;
import com.swu.jk.domain.Factory;

@Repository
public class FactoryDaoImpl extends BaseDaoImpl<Factory> implements FactoryDao{
	public FactoryDaoImpl() {
		super.setNs("com.swu.jk.mapper.FactoryMapper");
	}

	@Override
	public void updateState(Map map) {
		super.getSqlSession().update(super.getNs() + ".updateState", map);
	}
}
