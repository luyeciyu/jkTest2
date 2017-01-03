package com.swu.jk.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
	public String login(@Valid User u, BindingResult bindingResult, ModelMap modelMap, HttpSession session){
		System.out.println(u);
		
		if(bindingResult.hasErrors()){  //校验不通过
			return "/login.jsp";
		}
		
		User user = userService.login(u);
		if(user == null){   //用户名或密码错误
			FieldError fieldError = new FieldError("user", "name", "用户名或密码错误");
			bindingResult.addError(fieldError);
			
//			List<FieldError>  err=bindingResult.getFieldErrors();
//	        for (int i = 0; i < err.size(); i++) {
//	        	FieldError fe=err.get(i);
//	        	String field=fe.getField();//得到那个字段验证出错
//	        	String errorMessage=fe.getDefaultMessage();//得到错误消息
//	            System.out.println(field +" : "+errorMessage);
//	        }
			return "/login.jsp";
		}
		
		session.setAttribute("loginUser", user);
		return "redirect:/home/fmain.action";
	}
}
