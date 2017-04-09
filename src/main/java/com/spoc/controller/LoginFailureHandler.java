package com.spoc.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import com.spoc.dto.UserDto;
import com.spoc.service.UserService;


@Service("loginFailureHandler")
public class LoginFailureHandler implements AuthenticationFailureHandler {

  private final Logger log = LoggerFactory.getLogger(LoginFailureHandler.class);

  private UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }
  

  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  @SuppressWarnings("deprecation")
  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authentication) throws IOException, ServletException {
    try {
      String userName = null;
      UserDto user = null;
      log.info("handling authentication failure");
      if (authentication != null) {
        log.info(" handling failure authentication for : " + authentication.getAuthentication());
        log.info(" LocalizedMessage : " + authentication.getLocalizedMessage());
        log.info(" Message : " + authentication.getMessage());
        log.info(" Cause : " + authentication.getCause());
        log.info(" ExtraInformation : " + authentication.getExtraInformation());
        log.info(" trace : " + authentication.getStackTrace().toString());
        if (authentication.getAuthentication() != null) {
          userName = authentication.getAuthentication().getName();
          user = userService.findByUserName(userName);
        }
      }
  
      /*if (user != null) {
        if (user.isEnabled() && !user.isLocked()) {
          int attemptCounter = user.getLoginAttemptCount();
          log.info(" user attaempt count : " + attemptCounter);
          attemptCounter++;
          log.info(" updating counter to : " + attemptCounter);
          if (attemptCounter == 5) {
            try {
              user.setLocked(true);
              Date loginAttemptDate = new Date();
              log.info(" last login attempt time : " + loginAttemptDate.toString());
              user.setLoginAttemptTime(loginAttemptDate);
              user.setLoginAttemptCount(attemptCounter);
               sending account locked email to user 
              emailClientService.sendMail(EmailClientService.USER_LOCKOUT_MAIL, user, request, null);
            } catch (Exception e) {
              log.info("Excpetion sending user lockout email : " + e);
            }
          } else {
            user.setLoginAttemptCount(attemptCounter);
          }
  
          userService.updateUser(user);
        }
      }*/
  
      HttpSession session = request.getSession();
      if (session != null) {
        log.info("Setting AuthenticationException to session");
        session.setAttribute("exception", authentication);
      } else {
        log.info("session null");
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    redirectStrategy.sendRedirect(request, response, "/login?error");
  }

}
