package com.spoc.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.spoc.dao.UserDAOImpl;
import com.spoc.dto.UserDto;
import com.spoc.model.UserSession;
import com.spoc.service.UserService;



@Service("loginSuccessHandler")
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
  private final Logger log = LoggerFactory.getLogger(LoginSuccessHandler.class);

  private UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }
  
  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

    User userDetail = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    UserDto user = userService.findByUserName(userDetail.getUsername());
    if (user != null) {
      log.info("Handling Successfull Authetication of User : " + user.getUserName());
      HttpSession session = request.getSession();

      if (session != null) {
        UserSession userSession = new UserSession();
        userSession.setDisplayName(user.getDisplayName());
        userSession.setUserName(user.getUserName());
        session.setAttribute("userSession", userSession);
        session.setAttribute("username", user.getUserName());
        log.info("Setting UserSession parameters completed");
        
        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            redirectStrategy.sendRedirect(request, response, "/get-home");
        } else if("USER".equalsIgnoreCase(user.getRole())) {
            redirectStrategy.sendRedirect(request, response, "/get-home");
        }  else {
            redirectStrategy.sendRedirect(request, response, "/");
        }
      } else {
        log.info(" No valid session");
      }
      log.info("updating user ");
    }

  }

}
