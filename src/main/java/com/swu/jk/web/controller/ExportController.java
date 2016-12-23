package com.swu.jk.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swu.jk.domain.Export;
import com.swu.jk.service.ExportService;

@Controller
@RequestMapping("/cargo/export")
public class ExportController {

	@Resource
	private ExportService exportService;
	
	@RequestMapping("/contractsave.action")
	public String contractsave(String id, Model model){
		
		System.out.println(id);
		return "redirect:/cargo/contract/list.action";
	}
}
