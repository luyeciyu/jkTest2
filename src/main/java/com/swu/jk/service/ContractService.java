package com.swu.jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.swu.jk.domain.Contract;
import com.swu.jk.pagination.Page;
import com.swu.jk.vo.ContractVO;

public interface ContractService {
	public List<Contract> findPage(Page page);
	public List<Contract> find(Map params);
	public Contract get(Serializable id);
	public void update(Contract contract);
	public void deleteById(Serializable id);
	public void delete(Serializable[] ids);
	public void insert(Contract contract);
	public ContractVO view(String id);
	
}
