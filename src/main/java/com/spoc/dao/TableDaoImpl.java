package com.spoc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spoc.dto.EmployeeDto;
import com.spoc.dto.TableDetailsDto;

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

	@Override
	public TableDetailsDto getEmployeeDetails(int totalRecords,  TableDetailsDto tableData) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int totalAfterSearch = totalRecords;
		TableDetailsDto result = new TableDetailsDto();
		JSONArray array = new JSONArray();
		String searchSQL = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dbConnectionURL = "jdbc:mysql://localhost:3306/faruk?user=root&password=root";
			Connection con = DriverManager.getConnection(dbConnectionURL);
		
		
		String sql = "SELECT " + "id, name, place, city, state, "
				+ "phone " + "FROM " + "person " + "WHERE ";

		String globeSearch = "id like '%" + tableData.getGlobalSearch() + "%'"
				+ "or name like '%" + tableData.getGlobalSearch() + "%'"
				+ "or place like '%" + tableData.getGlobalSearch() + "%'"
				+ "or city like '%" + tableData.getGlobalSearch() + "%'"
				+ "or state like  '%" + tableData.getGlobalSearch() + "%'"
				+ "or phone like '%" + tableData.getGlobalSearch() + "%'";
		
		String recIdSearch="id like " + tableData.getRecIdSearch() + "";
		String empIdSearch="name like '%" + tableData.getEmpIdSearch() + "%'";
		String empNameSearch=" place like '%" + tableData.getEmpNameSearch() + "%'";
		String empSalarySearch=" city like '%" + tableData.getEmpSalarySearch() + "%'";
		if (tableData.getGlobalSearch() != "") {
			searchSQL = globeSearch;
		}
		else if(tableData.getRecIdSearch() !="")
		{
			searchSQL=recIdSearch;
		}
		else if(tableData.getEmpIdSearch() !="")
		{
			searchSQL=empIdSearch;
		}
		else if(tableData.getEmpNameSearch()!="")
		{
			searchSQL=empNameSearch;
		}
		else if(tableData.getEmpSalarySearch()!="")
		{
			searchSQL=empSalarySearch;
		}
		sql += searchSQL;
		sql += " order by " + tableData.getColumnName() + " " + tableData.getDirection();
		sql += " limit " + tableData.getInitial() + ", " + tableData.getRecordSize();
        System.out.println(sql);
        //for searching
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			JSONArray ja = new JSONArray();
			ja.put(rs.getString("id"));
			ja.put(rs.getString("name"));
			ja.put(rs.getString("place"));
			ja.put(rs.getString("city"));
			ja.put(rs.getString("state"));
			ja.put(rs.getString("phone"));
			array.put(ja);	
		}
		stmt.close();
		rs.close();

		String query = "SELECT " + "COUNT(*) as count " + "FROM " + "person " + "WHERE ";

		//for pagination
		if (tableData.getGlobalSearch() != ""||tableData.getRecIdSearch() != "" || tableData.getEmpIdSearch() != "" ||tableData.getEmpNameSearch() != ""||tableData.getEmpSalarySearch() != "" ) {
			query += searchSQL;
			PreparedStatement st = con.prepareStatement(query);
			ResultSet results = st.executeQuery();
			if (results.next()) {
				totalAfterSearch = results.getInt("count");
			}
			st.close();
			results.close();
			con.close();
		}} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			tableData.setTotalRecords(totalRecords);
			tableData.setTotalDisplayRecords(totalAfterSearch);
			tableData.setArray(array);
		} catch (Exception e) {

		}

		return null;
	}

}
