package com.spoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spoc.dto.UserDto;
import com.spoc.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping( value={"/", "/login"}, method=RequestMethod.GET)
	public String getLoginPage(Model model)
	{		
		System.out.println("LOGIN CONTROLLER ");
		return "Login";
	}
	
	
	@RequestMapping(value="/save", method=RequestMethod.GET)
	public void save(Model model)
	{		
		userService.save();
	}
	
	//Home Page View
	@RequestMapping(value="/get-home", method=RequestMethod.POST)
	public String getHomePage(@ModelAttribute("userForm")UserDto userForm, BindingResult result, Model model)
	{		
		System.out.println(" user name "+userForm.getUserName());
		System.out.println(" user password "+userForm.getPassword());
		return "home-page";
	}
	
	
	//Get Data Table Page
	@RequestMapping(value="/get-table", method=RequestMethod.POST)
	public String getDataTable(@ModelAttribute("userForm")UserDto userForm, BindingResult result, Model model)
	{		
		return "home-page";
	}
	
	
	
	// Get logout view page
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String getLogout(Model model){
		return "Login";
	}

}
