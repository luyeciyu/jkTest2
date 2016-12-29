package com.swu.jk.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swu.jk.domain.ShippingOrder;
import com.swu.jk.service.ShippingOrderService;

@Controller
@RequestMapping("/cargo/shippingorder")
public class ShippingOrderController {

	@Resource
	private ShippingOrderService shippingOrderService;
	
	@RequestMapping("/toedit.action")
	public String toEdit(String id, Model model){
		System.out.println(id);
		ShippingOrder shippingOrder = shippingOrderService.get(id);
		
		model.addAttribute("obj", shippingOrder);
		model.addAttribute("id", id);
		return "/cargo/shippingorder/jShippingOrderEdit.jsp";
	}
	
	
	@RequestMapping("/insert.action")
	public String insert(ShippingOrder shippingOrder, String subid, Model model){
		shippingOrderService.insert(shippingOrder, subid);
		
		model.addAttribute("id", shippingOrder.getId());
		
		return "redirect:/cargo/shippingorder/toedit.action";
	}
}
