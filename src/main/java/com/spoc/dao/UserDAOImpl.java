package com.spoc.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spoc.dto.UserDto;


@Repository
public class UserDAOImpl implements UserDAO {

	private final Logger LOG = LoggerFactory.getLogger(UserDAOImpl.class);

	@Autowired
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	/*@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}*/

	@Override
	public void save() {
		
		String query = "insert into user (user_id, username, password) values (?,?,?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] args = new Object[] {9, "abc", "XYZ"};
		int out = jdbcTemplate.update(query, args);
		if(out !=0){
			System.out.println("Data Saved Successfully");
			LOG.info("Data Saved Successfully....");
		}else 
			LOG.info("Data not saved ....");
	}


	@Override
	public UserDto findByUserName(String username) {
		UserDto user=new UserDto();
		try {
			String query = "select user_id, username, password, role, display_name, enabled from user where username = ?";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, username);    
		    for (Map row : rows) {
		    	user.setUserName((String)(row.get("username")));
		    	user.setPassword((String)row.get("password"));
		    	user.setDisplayName((String)row.get("display_name"));
		    	user.setRole((String)row.get("role"));
		    	user.setEnabled((boolean)row.get("enabled"));
		    }
		} catch (Exception e) {
			LOG.info("ERROR  WHILE GETTING USER DETAILS "+	e.getMessage());

		}
		
		
		return user;
	}

	
}
