package com.swu.jk.service;

import java.util.List;

import com.swu.jk.vo.ContractProductVO;
import com.swu.jk.vo.ExportVO;

public interface PrintService {
	public List<ContractProductVO> findOutProductData(String date);
	public ExportVO findPrintExportData(String id);
}
