package com.swu.jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.swu.jk.domain.ExtCproduct;
import com.swu.jk.domain.ExtEproduct;
import com.swu.jk.domain.SysCode;
import com.swu.jk.pagination.Page;

public interface ExtEproductService {
	
	public List<ExtEproduct> findPage(Page page);		//分页查询
	public List<ExtEproduct> find(Map paraMap);			//带条件查询，条件可以为null，既没有条件；返回list对象集合
	public ExtEproduct get(Serializable id);			//只查询一个，常用于修改
	public void insert(ExtEproduct extEproduct);		//插入，用实体作为参数
	public void update(ExtEproduct extEproduct);		//修改，用实体作为参数
	public void deleteById(Serializable id);			//按id删除，删除一条；支持整数型和字符串类型ID
	public void delete(Serializable[] ids);				//批量删除；支持整数型和字符串类型ID
	
	public List<SysCode> getCtypeList();							//获取分类列表
}
