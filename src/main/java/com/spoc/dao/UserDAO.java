package com.spoc.dao;

import com.spoc.dto.UserDto;

public interface UserDAO {
	
	void save();

	UserDto findByUserName(String username);
}
