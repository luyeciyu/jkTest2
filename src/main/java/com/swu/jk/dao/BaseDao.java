package com.swu.jk.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.swu.jk.pagination.Page;

public interface BaseDao<T> {
	public List<T> findPage(Page page);
	public List<T> find(Map params);
	public T get(Serializable id);
	public void update(T entity);
	public void deleteById(Serializable id);
	public void delete(Serializable[] ids);
	public void insert(T entity);
}
