package com.swu.jk.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swu.jk.domain.User;
import com.swu.jk.service.UserService;


@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/login.action", method=RequestMethod.GET)
	public String showLogin(){
		return "/login.jsp";
	}
	
	@RequestMapping(value="/login.action", method=RequestMethod.POST)
	public String login(@ModelAttribute User u, ModelMap modelMap, HttpSession session){
//		System.out.println(u);
//		User user = userService.login(u);
//		if(user == null){
//			modelMap.put("loginFailed", 1);
//			return "/login.jsp";
//		}
//		session.setAttribute("loginUser", user);
		return "redirect:/home/fmain.action";
	}
}
