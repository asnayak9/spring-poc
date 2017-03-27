package com.spoc.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spoc.dao.UserDAO;
import com.spoc.dto.UserDto;

@Service
public class UserServiceImpl implements UserService
{

	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public void save() {
		userDAO.save();
		
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		// TODO Auto-generated method stub
		UserDto user = userDAO.findByUserName(username);
		
		if (user == null) {
			log.info(" No User Found");
			throw new UsernameNotFoundException(username);
		}
		else {
          log.info("validating User : " + user.getUserName());
          /*if (user.isLocked()) {
            Date now = new Date();
            Date lastLoginAttemptDate = user.getLoginAttemptTime();
      
            if (lastLoginAttemptDate != null) {
	            long lastLoginAttemptTime = lastLoginAttemptDate.getTime() / 1000;
	            long nowTime = now.getTime() / 1000;
	      
	            long diffime = nowTime - lastLoginAttemptTime;
	             handling login attempts 
	            
	            if (diffime > 1800) {
	              user.setLocked(false);
	              user.setLoginAttemptCount(0);
	              user.setLoginAttemptTime(null);
	            //  userDao.updateUser(user);
	            }
            }
        
          }*/
		}
		List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());
		return buildUserForAuthentication(user, authorities);
	} 
	
	private org.springframework.security.core.userdetails.User buildUserForAuthentication(UserDto user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}
	
	
	private List<GrantedAuthority> buildUserAuthority(String userRole) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		// Build user's authorities
		if (userRole != null) {
			log.info(" Adding user Role to Authority : " + userRole);
			setAuths.add(new SimpleGrantedAuthority(userRole.toUpperCase()));
		}
		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
		return result;
	}
	
	

}
