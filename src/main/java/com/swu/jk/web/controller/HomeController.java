package com.swu.jk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/home")
public class HomeController {
	
	@RequestMapping(value="/fmain.action")
	public String fmain(){
		return "/home/fmain.jsp";
	}
	
	@RequestMapping(value="/title.action")
	public String title(){
		return "/home/title.jsp";
	}
	
	@RequestMapping(value="/left.action")
	public String left(){
		return "/home/left.jsp";
	}
	
	@RequestMapping(value="/main.action")
	public String olmsgList(){
		return "/home/olmsgList.jsp";
	}
	
	
	@RequestMapping(value="/cargoLeft.action")
	public String cpLeft(){
		return "/cargo/left.jsp";
	}
	
	@RequestMapping(value="/cargoMain.action")
	public String cargoMain(){
		return "/cargo/main.jsp";
	}
	
	
	
}
