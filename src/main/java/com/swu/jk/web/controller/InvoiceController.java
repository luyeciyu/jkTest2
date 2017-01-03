package com.swu.jk.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swu.jk.dao.InvoiceDao;
import com.swu.jk.domain.Invoice;
import com.swu.jk.domain.PackingList;
import com.swu.jk.domain.ShippingOrder;
import com.swu.jk.service.InvoiceService;
import com.swu.jk.service.PackingListService;
import com.swu.jk.util.UtilFuns;

@Controller
@RequestMapping("/cargo/invoice")
public class InvoiceController {

	@Resource
	private InvoiceService invoiceService;
	@Resource
	private PackingListService packingListService;
	
	@RequestMapping("/toedit.action")
	public String toEdit(String id, Model model) throws Exception{
		System.out.println(id);
	
		if(UtilFuns.isNotEmpty(id)){
			PackingList packingList = packingListService.get(id);
			if(packingList == null){
				throw new Exception("请先填写装箱信息（发票号、发票时间、卖家、买家等）, 保存后再点击【发票】!");
			}
			Invoice invoice = invoiceService.get(id);
			model.addAttribute("obj", invoice);
			model.addAttribute("id", id);
			
		}
		
		return "/cargo/invoice/jInvoiceEdit.jsp";
	}
	
	
	@RequestMapping("/insert.action")
	public String insert(Invoice invoice, String subid, Model model){
		invoiceService.insertOrUpdate(invoice, subid);
		model.addAttribute("id", invoice.getId());
		
		return "redirect:/cargo/invoice/toedit.action";
	}
	
}
