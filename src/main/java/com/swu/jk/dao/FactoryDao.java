package com.swu.jk.dao;

import java.util.Map;

import com.swu.jk.domain.Factory;

public interface FactoryDao extends BaseDao<Factory>{
	public void updateState(Map map);			//修改状态
}
