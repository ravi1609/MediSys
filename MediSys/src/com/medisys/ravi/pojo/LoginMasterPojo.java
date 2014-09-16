package com.medisys.ravi.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="LOGIN_MASTER")
public class LoginMasterPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	//fields
	
	@Id
	@Column(name="USERNAME")
	private String user_name;
	
	
	
	@Column(name="PASSWORD")
	private String pass_word;
	
	
	
	@Column(name="RECORD_STATUS")
	private Integer record_status;
	
	
	
	@Column(name="LOGIN_FLAG")
	private Integer login_flag;




	
	//getters and setters 
	
	public String getUser_name() {
		return user_name;
	}



	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}



	public String getPass_word() {
		return pass_word;
	}



	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}



	public Integer getRecord_status() {
		return record_status;
	}



	public void setRecord_status(Integer record_status) {
		this.record_status = record_status;
	}



	public Integer getLogin_flag() {
		return login_flag;
	}



	public void setLogin_flag(Integer login_flag) {
		this.login_flag = login_flag;
	}
}
