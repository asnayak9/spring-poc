package com.spoc.dao;

import java.util.List;

import com.spoc.dto.EmployeeDto;

public interface TableDao {
	List<EmployeeDto> getEmployeeData();

	int getTotalRecordCount();
}
