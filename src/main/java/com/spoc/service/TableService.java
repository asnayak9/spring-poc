package com.spoc.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.spoc.dto.EmployeeDto; 

public interface TableService {

	List<EmployeeDto> getEmployeeData();

	int getTotalRecordCount();

	JSONObject getEmployeeDetails(int totalRecords, HttpServletRequest request);
		
}
