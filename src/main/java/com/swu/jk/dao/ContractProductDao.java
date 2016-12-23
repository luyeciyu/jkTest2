package com.swu.jk.dao;

import java.io.Serializable;
import java.util.List;

import com.swu.jk.domain.ContractProduct;
import com.swu.jk.vo.ContractProductVO;

public interface ContractProductDao extends BaseDao<ContractProduct>{
	public void deleteByContractId(Serializable[] ids);
	public List<ContractProductVO> findOutProductData(String date);
}
