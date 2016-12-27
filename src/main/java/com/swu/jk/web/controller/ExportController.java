package com.swu.jk.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swu.jk.domain.Export;
import com.swu.jk.service.ExportService;
import com.swu.jk.util.UtilFuns;
import com.swu.jk.vo.ExportVO;

@Controller
@RequestMapping("/cargo/export")
public class ExportController {

	@Resource
	private ExportService exportService;
	
	
	@RequestMapping("/list.action")
	public String exportList(Model model){
		
		List<Export> dataList = exportService.find(null);
		model.addAttribute("dataList", dataList);
		return "/cargo/export/jExportList.jsp";
	}
	
	@RequestMapping("/contractsave.action")
	public String contractsave(String id, Model model){
		
		if(id != null){
			String[] contractIds = UtilFuns.splitStr(id, ",");
			exportService.saveContractToExport(contractIds);
			System.out.println(id);
		}
		return "redirect:/cargo/export/list.action";
	}
	
	@RequestMapping("/toview.action")
	public String view(String id, Model model){
		System.out.println(id);
		ExportVO exportVO = exportService.view(id);
		model.addAttribute("obj", exportVO);
		return "/cargo/export/jExportView.jsp";
	}
	
	@RequestMapping("/delete.action")
	public String delete(String id, Model model){
		String[] ids = UtilFuns.splitStr(id, ",");
		exportService.delete(ids);
		return "redirect:/cargo/export/list.action";
	}
	
	@RequestMapping("/toupdate.action")
	public String toUpdate(String id, Model model){
		System.out.println(id);
		Export export = exportService.get(id);
		model.addAttribute("obj", export);
		return "/cargo/export/jExportUpdate.jsp";
	}
	
	@RequestMapping("/update.action")
	public String update(Export export, Model model){
		exportService.update(export);
		
		model.addAttribute("obj", export);
		return "/cargo/export/jExportUpdate.jsp";
	}
}
