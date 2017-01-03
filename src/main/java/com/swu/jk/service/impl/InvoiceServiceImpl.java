package com.swu.jk.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swu.jk.dao.ExportDao;
import com.swu.jk.dao.InvoiceDao;
import com.swu.jk.dao.PackingListDao;
import com.swu.jk.domain.Invoice;
import com.swu.jk.domain.PackingList;
import com.swu.jk.pagination.Page;
import com.swu.jk.service.InvoiceService;
import com.swu.jk.util.UtilFuns;

@Service("invoiceService")
public class InvoiceServiceImpl implements InvoiceService{

	@Resource
	private InvoiceDao invoiceDao;
	
	@Resource
	private PackingListDao packingListDao;
	@Resource 
	private ExportDao exportDao;
	
	@Override
	public List<Invoice> findPage(Page page) {
		List<Invoice> invoices = invoiceDao.findPage(page);
		return invoices;
	}

	@Override
	public List<Invoice> find(Map params) {
		return invoiceDao.find(params);
	}

	@Override
	public Invoice get(Serializable id) {
		return invoiceDao.get(id);
	}

	@Override
	public void update(Invoice invoice) {
		invoiceDao.update(invoice);
	}

	@Override
	public void deleteById(Serializable id) {
		invoiceDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		invoiceDao.delete(ids);
	}

	@Override
	public void insertOrUpdate(Invoice invoice, String subid) {
		
		PackingList packingList = packingListDao.get(invoice.getId());
		
		invoice.setScNo(packingList.getExportNos().replace("|", " "));
		
		
		if(UtilFuns.isNotEmpty(subid)){     //更新
			invoiceDao.update(invoice);
		}else{   //新增
			invoiceDao.insert(invoice); 
		}
		
		//更改报运状态
		
		
		String[] exportIds = packingList.getExportIds().split("\\|");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", exportIds);
		map.put("state", 4);
		exportDao.updateState(map);
	}

}
