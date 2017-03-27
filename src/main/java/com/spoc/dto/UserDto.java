package com.spoc.dto;

public class UserDto {
	private String userName;
	private String password;
	private String displayName;
	private String role;
	private boolean enabled;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@Override
	public String toString() {
		return "UserDto [userName=" + userName + ", password=" + password
				+ ", displayName=" + displayName + ", role=" + role
				+ ", enabled=" + enabled + "]";
	}
	
	
	
	
}
