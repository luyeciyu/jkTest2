package com.swu.jk.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.swu.jk.dao.ContractDao;
import com.swu.jk.domain.Contract;
import com.swu.jk.vo.ContractVO;

@Repository
public class ContractDaoImpl extends BaseDaoImpl<Contract> implements ContractDao{
	public ContractDaoImpl() {
		super.setNs("com.swu.jk.mapper.ContractMapper");
	}

	@Override
	public void updateState(Map map) {
		super.getSqlSession().update(super.getNs() + ".updateState", map);
	}

	@Override
	public ContractVO view(String contractId) {
		
		return super.getSqlSession().selectOne(super.getNs()+".view", contractId);
	}
	
}
