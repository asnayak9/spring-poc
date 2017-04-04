package com.spoc.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.spoc.dto.UserDto;


@Service
public interface UserService extends UserDetailsService{
	void save();
	UserDto findByUserName(String Username);
}
