package com.spoc.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.spoc.service.UserService;


/**
 * We will use this spring security Authentication provider to validate the username and password.
 * we will be sending two times salted hashed password from the client side and compare that password
 * with the salted hashed password stored in DB.We are using Bcrypt for the encryption which is 
 * widely referred in the spring documentation. 
 *
 */
@Service("customAuthenticationProviderService")
public class CustomAuthenticationProviderService implements AuthenticationProvider {

  @Autowired
  private UserService userService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    
	System.out.println("************************************************* hit");
    String username = authentication.getName();
    String password = (String) authentication.getCredentials();
    System.out.println("*******************Authenticate Method hit user name "+username+" password "+password);
    UserDetails user = null;
    if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password)) {
      user =  userService.loadUserByUsername(username);
      if (user == null) {
        throw new BadCredentialsException("User not found");
      }
      /*if (!user.isAccountNonLocked()) {
    	  throw new LockedException("User account is locked");
      }
      if (!user.isEnabled()) {
    	  throw new DisabledException("User is disabled");
      }
      if (!BCrypt.checkpw(user.getPassword(), password)) {
        throw new BadCredentialsException("Incorrect Password");
      }*/
      if(!password.equals(user.getPassword())){
          throw new BadCredentialsException("Incorrect Password");
      }
    }
    else {
      throw new BadCredentialsException("User not found");
    }
    return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return true;
  }

}
