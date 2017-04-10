package com.spoc.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spoc.dto.UserDto;
import com.spoc.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping( value={"/", "/login"}, method=RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false) String error,
		      @RequestParam(value = "logout", required = false) String logout, @RequestParam(
		          value = "sessiontimeout", required = false) String sessionTimeOut, @RequestParam(
		          value = "multiplesession", required = false) String multipleSession,
		      HttpServletRequest request, Principal principal, HttpServletResponse response) 
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
	@RequestMapping(value="/get-home", method=RequestMethod.GET)
	public String getHomePage(Model model)
	{		
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
