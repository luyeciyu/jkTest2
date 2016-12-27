package com.swu.jk.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swu.jk.dao.ContractProductDao;
import com.swu.jk.dao.ExportDao;
import com.swu.jk.service.ContractProductService;
import com.swu.jk.service.PrintService;
import com.swu.jk.vo.ContractProductVO;
import com.swu.jk.vo.ExportVO;

@Service
public class PrintServiceImpl implements PrintService{

	@Resource
	private ContractProductDao contractProductDao;
	@Resource
	private ExportDao exportDao;
	
	@Override
	public List<ContractProductVO> findOutProductData(String date) {
		return contractProductDao.findOutProductData(date);
	}
	@Override
	public ExportVO findPrintExportData(String id) {
		ExportVO exportVO = exportDao.view(id);
		return exportVO;
	}

}
