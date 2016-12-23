package com.swu.jk.service;

import java.util.List;

import com.swu.jk.vo.ContractProductVO;

public interface OutProductService {
	public List<ContractProductVO> findOutProductData(String date);
}
