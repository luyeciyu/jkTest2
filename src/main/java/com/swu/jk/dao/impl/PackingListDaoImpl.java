package com.swu.jk.dao.impl;

import org.springframework.stereotype.Repository;

import com.swu.jk.dao.PackingListDao;
import com.swu.jk.domain.PackingList;
import com.swu.jk.vo.PackingListVO;

@Repository
public class PackingListDaoImpl extends BaseDaoImpl<PackingList> implements PackingListDao{
	public PackingListDaoImpl() {
		super.setNs("com.swu.jk.mapper.PackingListMapper");
	}

	@Override
	public PackingListVO view(String id) {
		return super.getSqlSession().selectOne(super.getNs() + ".view", id);
	}
}
