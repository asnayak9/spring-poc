package com.spoc.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spoc.dao.TableDao;
import com.spoc.dto.EmployeeDto;
import com.spoc.dto.TableDetailsDto;

@Service
public class TableServiceImpl implements TableService{

	@Autowired
	public TableDao tableDao; 
	@Override
	public List<EmployeeDto> getEmployeeData() {
		return tableDao.getEmployeeData();
	}
	@Override
	public int getTotalRecordCount() {
		return tableDao.getTotalRecordCount();
	}
	@Override
	public TableDetailsDto getEmployeeDetails(int totalRecords,TableDetailsDto tableData) {
		return tableDao.getEmployeeDetails(totalRecords,tableData);
	}
	

}
