package com.spoc.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spoc.dto.EmployeeDto;

@Repository
public class TableDaoImpl implements TableDao{

	@Autowired
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<EmployeeDto> getEmployeeData() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM employee";
		List<EmployeeDto> empolyeeList =new ArrayList<EmployeeDto>();
		try {
			 empolyeeList  = jdbcTemplate.query(sql, new BeanPropertyRowMapper(EmployeeDto.class));
			 System.out.println("Employee List"+empolyeeList.size());
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}
		return empolyeeList;
	}

	@Override
	public int getTotalRecordCount() {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT count(*) FROM employee";
		Integer empTotalCount =new Integer(0);
		try {
			 empTotalCount  = jdbcTemplate.queryForObject(sql, Integer.class);
			 System.out.println("Total Employee Count"+empTotalCount);
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}
		return empTotalCount;
	}

}
