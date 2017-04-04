package com.spoc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
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
		String searchSQL = null;
		try {
		/*Class.forName("com.mysql.jdbc.Driver");
		String dbConnectionURL = "jdbc:mysql://localhost:3306/faruk?user=root&password=root";
		Connection con = DriverManager.getConnection(dbConnectionURL);*/
		Connection con=jdbcTemplate.getDataSource().getConnection();
		String sql = "SELECT " + "rec_id, emp_id, emp_name, emp_salary "
				+ "FROM " + "employee ";

		String globeSearch = "rec_id like '%" + tableData.getGlobalSearch() + "%'"
				+ "or emp_id like '%" + tableData.getGlobalSearch() + "%'"
				+ "or emp_name like '%" + tableData.getGlobalSearch() + "%'"
				+ "or emp_salary like '%" + tableData.getGlobalSearch() + "%'";
		
		String recIdSearch=" rec_id like " + tableData.getRecIdSearch() + "";
		String empIdSearch=" emp_id like '%" + tableData.getEmpIdSearch() + "%'";
		String empNameSearch=" emp_name like '%" + tableData.getEmpNameSearch() + "%'";
		String empSalarySearch=" emp_salary like '%" + tableData.getEmpSalarySearch() + "%'";
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
		
		if(searchSQL!=null && !StringUtils.isBlank(searchSQL)){
			sql += " WHERE "+searchSQL;
		}
		sql += " order by " + tableData.getColumnName() + " " + tableData.getDirection();
		sql += " limit " + tableData.getInitial() + ", " + tableData.getRecordSize();
        System.out.println(sql);
        
        //for searching
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			JSONArray ja = new JSONArray();
			ja.put(rs.getString("rec_id"));
			ja.put(rs.getString("emp_id"));
			ja.put(rs.getString("emp_name"));
			ja.put(rs.getString("emp_salary"));
			array.put(ja);	
		}
		stmt.close();
		rs.close();

		String query = "SELECT " + "COUNT(*) as count " + "FROM " + "employee " + "WHERE ";

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
			result.setTotalRecords(totalRecords);
			result.setTotalDisplayRecords(totalAfterSearch);
			result.setArray(array);
		} catch (Exception e) {

		}

		return result;
	}

}
