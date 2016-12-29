package com.swu.jk.web.controller.print;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swu.jk.service.PrintService;

@Controller
@RequestMapping("/cargo/packinglist")
public class PackingListPrintController {

	@Resource
	private PrintService printService;
	
	@RequestMapping("/print.action")
	public void print(){
		
	}
}
