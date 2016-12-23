package com.swu.jk.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swu.jk.domain.Contract;
import com.swu.jk.service.ContractService;
import com.swu.jk.vo.ContractVO;

@Controller
@RequestMapping(value="/cargo")
public class ContractController {
	
	@Resource
	private ContractService contractService;
	
	
	@RequestMapping(value="/contract/list.action")
	public String contractList(Model model){
		List<Contract> lContracts = contractService.find(null);
		model.addAttribute("dataList", lContracts);
		return "/cargo/contract/jContractList.jsp";
	}
	
	@RequestMapping(value="/outproduct/toedit.action")
	public String contractToedit(){
		return "/cargo/contract/jContractCreate.jsp";
	}
	
	@RequestMapping(value="/contract/toview.action")
	public String contractToview(String id,Model model){
		ContractVO contractVO = contractService.view(id);
		model.addAttribute("obj", contractVO);
		return "/cargo/contract/jContractView.jsp";
	}
	
	@RequestMapping(value="/contract/tocreate.action")
	public String contractTocreate(){
		return "/cargo/contract/jContractCreate.jsp";
	}
	
	@RequestMapping(value="/contract/insert.action")
	public String contractInsert(Contract contract){
		contractService.insert(contract);
		return "redirect:/cargo/contract/list.action";
	}
	
	@RequestMapping(value="/contract/toupdate.action")
	public String contractToupdate(String id, Model model){
		Contract contract = contractService.get(id);
		model.addAttribute("obj", contract);
		return "/cargo/contract/jContractUpdate.jsp";
	}
	
	@RequestMapping(value="/contract/update.action")
	public String contractUpdate(Contract contract){
		System.out.println(contract);
		contractService.update(contract);
		
		return "redirect:/cargo/contract/list.action";
	}
	
	
	@RequestMapping(value="/contract/delete.action")
	public String contractDelete(String id, Model model){
		contractService.deleteById(id);
		return "redirect:/cargo/contract/list.action";
	}
	
	
	@RequestMapping(value="/contract/print.action")
	public void contractPrint(String id, Model model){
		
	}
	
	
	
}
