package com.swu.jk.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swu.jk.dao.ContractProductDao;
import com.swu.jk.service.ContractProductService;
import com.swu.jk.service.OutProductService;
import com.swu.jk.vo.ContractProductVO;

@Service
public class OutProductServiceImpl implements OutProductService{

	@Resource
	private ContractProductDao contractProductDao;
	@Override
	public List<ContractProductVO> findOutProductData(String date) {
		return contractProductDao.findOutProductData(date);
	}

}
