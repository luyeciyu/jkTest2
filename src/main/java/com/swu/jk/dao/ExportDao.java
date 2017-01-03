package com.swu.jk.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.swu.jk.domain.Export;
import com.swu.jk.vo.ExportVO;

public interface ExportDao extends BaseDao<Export>{
	public ExportVO view(String id);
	
	//public void updateState(Serializable[] ids);
	public void updateState(Map param);
}
