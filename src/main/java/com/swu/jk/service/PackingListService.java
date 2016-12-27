package com.swu.jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.swu.jk.domain.PackingList;
import com.swu.jk.pagination.Page;
import com.swu.jk.vo.PackingListVO;

public interface PackingListService {
	public List<PackingList> findPage(Page page);		//分页查询
	public List<PackingList> find(Map paraMap);			//带条件查询，条件可以为null，既没有条件；返回list对象集合
	public PackingList get(Serializable id);			//只查询一个，常用于修改
	public void insert(PackingList packingList);			//插入，用实体作为参数
	public void update(PackingList packingList);			//修改，用实体作为参数
	public void deleteById(Serializable id);		//按id删除，删除一条；支持整数型和字符串类型ID
	public void delete(Serializable[] ids);			//批量删除；支持整数型和字符串类型ID\\
	
	public PackingListVO view(String id);
	public List<Map> getExportToPackingListData(String[] ids);
}
