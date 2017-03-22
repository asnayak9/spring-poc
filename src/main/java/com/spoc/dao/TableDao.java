package com.spoc.dao;

import java.util.List;

import org.json.JSONObject;

import com.spoc.dto.EmployeeDto;
import com.spoc.dto.TableDetailsDto;

public interface TableDao {
	List<EmployeeDto> getEmployeeData();

	int getTotalRecordCount();

	TableDetailsDto getEmployeeDetails(int totalRecords, TableDetailsDto tableData);
}
