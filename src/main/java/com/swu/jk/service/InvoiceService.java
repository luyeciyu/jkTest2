package com.swu.jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.swu.jk.domain.Invoice;
import com.swu.jk.pagination.Page;

public interface InvoiceService {
	public List<Invoice> findPage(Page page);
	public List<Invoice> find(Map params);
	public Invoice get(Serializable id);
	public void update(Invoice invoice);
	public void deleteById(Serializable id);
	public void delete(Serializable[] ids);
	
	
	public void insertOrUpdate(Invoice invoice, String subid);
}
