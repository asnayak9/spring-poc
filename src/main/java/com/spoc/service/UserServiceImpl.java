package com.spoc.service;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spoc.dao.UserDAO;

@Service
public class UserServiceImpl implements UserService
{

	private Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public void save() {
		userDAO.save();
		
	} 
	
	

}
