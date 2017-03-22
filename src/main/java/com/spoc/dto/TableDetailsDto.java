package com.spoc.dto;

import org.json.JSONArray;

public class TableDetailsDto {
	
	private JSONArray array = new JSONArray();
	private Integer totalRecords;
	private Integer totalDisplayRecords;
	
	private String globalSearch;
	private String columnName;
	private String direction;
	private int initial;
	private int recordSize;
	private String recIdSearch, empIdSearch, empNameSearch, empSalarySearch;
	
	public JSONArray getArray() {
		return array;
	}
	public String getGlobalSearch() {
		return globalSearch;
	}
	public void setGlobalSearch(String globalSearch) {
		this.globalSearch = globalSearch;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public int getInitial() {
		return initial;
	}
	public void setInitial(int initial) {
		this.initial = initial;
	}
	public int getRecordSize() {
		return recordSize;
	}
	public void setRecordSize(int recordSize) {
		this.recordSize = recordSize;
	}
	public String getRecIdSearch() {
		return recIdSearch;
	}
	public void setRecIdSearch(String recIdSearch) {
		this.recIdSearch = recIdSearch;
	}
	public String getEmpIdSearch() {
		return empIdSearch;
	}
	public void setEmpIdSearch(String empIdSearch) {
		this.empIdSearch = empIdSearch;
	}
	public String getEmpNameSearch() {
		return empNameSearch;
	}
	public void setEmpNameSearch(String empNameSearch) {
		this.empNameSearch = empNameSearch;
	}
	public String getEmpSalarySearch() {
		return empSalarySearch;
	}
	public void setEmpSalarySearch(String empSalarySearch) {
		this.empSalarySearch = empSalarySearch;
	}
	public void setArray(JSONArray array) {
		this.array = array;
	}
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
	public Integer getTotalDisplayRecords() {
		return totalDisplayRecords;
	}
	public void setTotalDisplayRecords(Integer totalDisplayRecords) {
		this.totalDisplayRecords = totalDisplayRecords;
	}
	
	
}
