package com.spoc.controller;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spoc.dto.TableDetailsDto;
import com.spoc.service.TableService;


@Controller
public class TableController {

	
	
	@Autowired
	public TableService tableService; 
	
	@RequestMapping(value="/getDataTable", method=RequestMethod.GET)
	public String getDataTable(Model model){
		System.out.println(" Get Data Table Controller ");
		tableService.getEmployeeData();
		return "home-page";
	}
	
	
	
	@RequestMapping(value="/getDynamicTableData", method=RequestMethod.POST)
	@ResponseBody
	public TableDetailsDto getDataTable(HttpServletRequest request, HttpServletResponse response, Model model){
		System.out.println(" Get Data Table POST Controller ");
		String[] columnNames = { "recId", "empId", "empName", "Salary" };
		TableDetailsDto tableData = new TableDetailsDto();
		int listDisplayAmount = 10;
		int start = 0;
		int column = 0;
		String dir = "asc";
		String pageNo = request.getParameter("iDisplayStart");
		String pageSize = request.getParameter("iDisplayLength");
		String colIndex = request.getParameter("iSortCol_0");
		String sortDirection = request.getParameter("sSortDir_0");
		
		if (pageNo != null) {
			start = Integer.parseInt(pageNo);
			if (start < 0) {
				start = 0;
			}
		}
		if (pageSize != null) {
			listDisplayAmount = Integer.parseInt(pageSize);
			if (listDisplayAmount < 10 || listDisplayAmount > 50) {
				listDisplayAmount = 10;
			}
		}
		if (colIndex != null) {
			column = Integer.parseInt(colIndex);
			if (column < 0 || column > 5)
				column = 0;
		}
		if (sortDirection != null) {
			if (!sortDirection.equals("asc"))
				dir = "desc";
		}

		String colName = columnNames[column];
		int totalRecords= -1;
		try {
			totalRecords = tableService.getTotalRecordCount();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		tableData.setRecordSize(listDisplayAmount);
		tableData.setGlobalSearch(request.getParameter("sSearch"));
		tableData.setRecIdSearch(request.getParameter("sSearch_0"));
		tableData.setEmpIdSearch(request.getParameter("sSearch_1"));
		tableData.setEmpNameSearch(request.getParameter("sSearch_2"));
		tableData.setEmpSalarySearch(request.getParameter("sSearch_3"));
		
		tableData.setColumnName(colName);
		tableData.setDirection(dir);
		tableData.setInitial(start);
		tableData = tableService.getEmployeeDetails(totalRecords,tableData);
		return tableData;
	}
}
