package com.swu.jk.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swu.jk.domain.ContractProduct;
import com.swu.jk.domain.Factory;
import com.swu.jk.service.ContractProductService;
import com.swu.jk.service.FactoryService;

@Controller
@RequestMapping(value="/cargo/contractproduct")
public class ContractProductController {

	@Resource
	private ContractProductService contractProductService;
	@Resource
	private FactoryService factoryService;
	
	
	@RequestMapping(value="/tocreate.action")
	public String toCreate(String contractId, Model model){
		
		model.addAttribute("contractId", contractId);
		
		List<Factory> factories = factoryService.getFactoryList();
		model.addAttribute("factoryList", factories);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("conractId", contractId);
		List<ContractProduct> contractProducts = contractProductService.find(params);
		model.addAttribute("dataList", contractProducts);
		
		return "/cargo/contract/jContractProductCreate.jsp";
	}
	
	
	@RequestMapping(value="/insert.action")
	public String insert(ContractProduct contractProduct, Model model){
		contractProductService.insert(contractProduct);
		model.addAttribute("contractId", contractProduct.getContractId());
		
		return "redirect:/cargo/contractproduct/tocreate.action";
	}
	
	@RequestMapping(value="/toupdate.action")
	public String toUpdate(String id, Model model){
		ContractProduct contractProduct = contractProductService.get(id);
		model.addAttribute("obj", contractProduct);
		
		List<Factory> factories = factoryService.getFactoryList();
		model.addAttribute("factoryList", factories);
		
		return "/cargo/contract/jContractProductUpdate.jsp";
	}
	
	@RequestMapping(value="/update.action")
	public String update(ContractProduct contractProduct, Model model){
		
		contractProductService.update(contractProduct);
		
		model.addAttribute("contractId", contractProduct.getContractId());
		return "redirect:/cargo/contractproduct/tocreate.action";
	}
	
	@RequestMapping(value="/deleteById.action")
	public String deleteById(String id, String contractId, Model model){
		contractProductService.deleteById(id);
		model.addAttribute("contractId", contractId);
		
		return "redirect:/cargo/contractproduct/tocreate.action";
	}
}
