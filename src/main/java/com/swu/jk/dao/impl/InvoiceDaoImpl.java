package com.swu.jk.dao.impl;

import org.springframework.stereotype.Repository;

import com.swu.jk.dao.InvoiceDao;
import com.swu.jk.domain.Invoice;

@Repository("invoiceDao")
public class InvoiceDaoImpl extends BaseDaoImpl<Invoice> implements InvoiceDao{
	
	public InvoiceDaoImpl() {
		super.setNs("com.swu.jk.mapper.Invoicapper");
	}
}
