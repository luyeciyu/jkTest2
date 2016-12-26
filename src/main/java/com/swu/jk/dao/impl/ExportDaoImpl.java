package com.swu.jk.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.swu.jk.dao.ExportDao;
import com.swu.jk.domain.Export;
import com.swu.jk.vo.ExportVO;

@Repository
public class ExportDaoImpl extends BaseDaoImpl<Export> implements ExportDao{
	public ExportDaoImpl() {
		super.setNs("com.swu.jk.mapper.ExprotMapper");
	}

	@Override
	public ExportVO view(String id) {
		ExportVO exportVO = super.getSqlSession().selectOne(super.getNs() + ".view", id);
		return exportVO;
	}
}
