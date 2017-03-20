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

import com.spoc.service.TableService;


@Controller
public class TableController {

	private String GLOBAL_SEARCH_TERM;
	private String COLUMN_NAME;
	private String DIRECTION;
	private int INITIAL;
	private int RECORD_SIZE;
	private String ID_SEARCH_TERM,NAME_SEARCH_TERM,PLACE_SEARCH_TERM,CITY_SEARCH_TERM,STATE_SEARCH_TERM,PHONE_SEARCH_TERM;
	
	@Autowired
	public TableService tableService; 
	
	@RequestMapping(value="/getDataTable", method=RequestMethod.GET)
	public String getDataTable(Model model){
		System.out.println(" Get Data Table Controller ");
		tableService.getEmployeeData();
		return "home-page";
	}
	
	
	
	@RequestMapping(value="/getDynamicTableData", method=RequestMethod.POST)
	public String getDataTable(HttpServletRequest request, HttpServletResponse response, Model model){
		System.out.println(" Get Data Table POST Controller ");
		String[] columnNames = { "recId", "empId", "empName", "Salary" };

		JSONObject jsonResult = new JSONObject();
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

		RECORD_SIZE = listDisplayAmount;
		GLOBAL_SEARCH_TERM = request.getParameter("sSearch");
		ID_SEARCH_TERM=request.getParameter("sSearch_0");
		NAME_SEARCH_TERM=request.getParameter("sSearch_1");
		PLACE_SEARCH_TERM=request.getParameter("sSearch_2");
		CITY_SEARCH_TERM=request.getParameter("sSearch_3");
		STATE_SEARCH_TERM=request.getParameter("sSearch_4");
		PHONE_SEARCH_TERM=request.getParameter("sSearch_5");
		COLUMN_NAME = colName;
		DIRECTION = dir;
		INITIAL = start;

		/*try {
			jsonResult = tableService.getEmployeeDetails(totalRecords, request);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();
		out.print(jsonResult);*/
		return "home-page";
	}
}
