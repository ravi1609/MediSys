package com.medisys.ravi.actions;

import com.medisys.ravi.beans.LoginBean;
import com.medisys.ravi.dao.LoginDao;
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
		String validateUser=LoginDao.ValidateUser(login_bean);
		if(validateUser.equals("success")){
			return SUCCESS;
		}else if(validateUser.equals("deactive"))
		{
			return ERROR;
		}else if(validateUser.equals("loggedin"))
		{
			return ERROR;
		}
		
		return ERROR;
		
		
	}
	
	
	
	
	
}
