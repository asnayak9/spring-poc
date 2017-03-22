package com.spoc.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.spoc.dto.EmployeeDto; 
import com.spoc.dto.TableDetailsDto;

public interface TableService {

	List<EmployeeDto> getEmployeeData();

	int getTotalRecordCount();

	TableDetailsDto getEmployeeDetails(int totalRecords, TableDetailsDto tableData);
		
}
