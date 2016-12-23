package com.swu.jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.swu.jk.domain.ContractProduct;
import com.swu.jk.pagination.Page;

public interface ContractProductService {
	public List<ContractProduct> findPage(Page page);
	public List<ContractProduct> find(Map params);
	public ContractProduct get(Serializable id);
	public void update(ContractProduct contractProduct);
	public void deleteById(Serializable id);
	public void delete(Serializable[] ids);
	public void insert(ContractProduct contractProduct);
}
