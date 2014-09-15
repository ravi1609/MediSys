package com.medisys.ravi.beans;

import java.io.Serializable;

public class LoginBean implements Serializable {
	
	
	/**
	 * login bean
	 */
	private static final long serialVersionUID = 1L;
	
	// fields
	private String username;
	private String password;
	
	
	
	// getters and setters for the login of the user
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
