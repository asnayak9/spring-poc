package com.spoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spoc.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	
	
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getLoginPage(Model model)
	{		
		return "Login";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.GET)
	public void save(Model model)
	{		
		
		userService.save();
		//return "Login";
	}

}
