package com.swu.jk.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swu.jk.domain.ExtCproduct;
import com.swu.jk.domain.Factory;
import com.swu.jk.service.ExtCproductService;
import com.swu.jk.service.FactoryService;

@Controller
@RequestMapping(value="/cargo/extcproduct")
public class ExtCproductControler {

	@Resource
	private ExtCproductService extCproductService;
	@Resource
	private FactoryService factoryService;
	
	@RequestMapping(value="/tocreate.action")
	public String toCreate(String contractProductId, Model model){
		
		model.addAttribute("contractProductId", contractProductId);
		
		List<Factory> factories = factoryService.getFactoryList();
		model.addAttribute("factoryList", factories);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contractProductId", contractProductId);
		List<ExtCproduct> extCproducts = extCproductService.find(map);
		model.addAttribute("dataList", extCproducts);
		
		return "/cargo/contract/jExtCproductCreate.jsp";
	}
	
	
	@RequestMapping(value="/insert.action")
	public String insert(ExtCproduct extCproduct, Model model){
		extCproductService.insert(extCproduct);
		model.addAttribute("contractProductId", extCproduct.getContractProductId());
		return "redirect:/cargo/extcproduct/tocreate.action";
	}
	
	@RequestMapping(value="/toupdate.action")
	public String toUpdate(String id, Model model){
		ExtCproduct extCproduct = extCproductService.get(id);
		model.addAttribute("obj", extCproduct);
		
		List<Factory> factories = factoryService.getFactoryList();
		model.addAttribute("factoryList", factories);
		
		return "/cargo/contract/jExtCproductUpdate.jsp";
	}
	
	@RequestMapping(value="/update.action")
	public String update(ExtCproduct extCproduct, Model model){
		extCproductService.update(extCproduct);
		model.addAttribute("contractProductId", extCproduct.getContractProductId());
		
		return "redirect:/cargo/extcproduct/tocreate.action";
	}
	
	@RequestMapping(value="/deleteById.action")
	public String deleteById(String id, String contractProductId, Model model){
		extCproductService.deleteById(id);
		model.addAttribute("contractProductId", contractProductId);
		return "redirect:/cargo/extcproduct/tocreate.action";
	}
	
}
