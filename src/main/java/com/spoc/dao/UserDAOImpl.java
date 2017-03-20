package com.spoc.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


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
		
		String query = "insert into user (userId, userName, userPassword) values (?,?,?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] args = new Object[] {9, "abc", "XYZ"};
		int out = jdbcTemplate.update(query, args);
		if(out !=0){
			System.out.println("Data Saved Successfully");
			LOG.info("Data Saved Successfully....");
		}else 
			LOG.info("Data not saved ....");
	}

	
}
