package com.medisys.ravi.controllers;

import com.medisys.ravi.beans.LoginBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginController extends ActionSupport implements ModelDriven<LoginBean>
{

	/**
	 * 
	 * this class is developed for the login of the users
	 */
	private static final long serialVersionUID = 1L;
// fields 
	private LoginBean login_bean;
	
	//getters and setters 
	
	public LoginBean getLogin_bean() {
		return login_bean;
	}

	public void setLogin_bean(LoginBean login_bean) {
		this.login_bean = login_bean;
	}
	
	
	
	@Override
	public LoginBean getModel() {
		// TODO Auto-generated method stub
		return login_bean;
	}



	public String LoginUser()
	{
		
		
		
		
		return SUCCESS;
	}
	
	
	
	
	
}
