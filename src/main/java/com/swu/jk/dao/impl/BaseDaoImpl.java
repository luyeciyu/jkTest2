package com.swu.jk.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.ss.formula.functions.T;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.swu.jk.dao.BaseDao;
import com.swu.jk.pagination.Page;


public class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T>{

	private String ns;  //命名空间
	
	
	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	@Override
	public List<T> findPage(Page page) {
		List<T> lists =  this.getSqlSession().selectList(ns + ".findPage", page);
		return lists;
	}

	@Override
	public List<T> find(Map params) {
		List<T> lists = this.getSqlSession().selectList(ns + ".find", params);
		return lists;
	}

	@Override
	public T get(Serializable id) {
		return this.getSqlSession().selectOne(ns + ".get", id);
	}

	@Override
	public void update(T entity) {		
		this.getSqlSession().update(ns + ".update", entity);
	}

	@Override
	public void deleteById(Serializable id) {		
		this.getSqlSession().delete(ns + ".deleteById", id);
	}

	@Override
	public void delete(Serializable[] ids) {
		this.getSqlSession().delete(ns + ".delete", ids);
	}

	@Override
	public void insert(T entity) {
		this.getSqlSession().insert(ns + ".insert", entity);
	}

	public String getNs() {
		return ns;
	}

	public void setNs(String ns) {
		this.ns = ns;
	}
	
	
	
}
