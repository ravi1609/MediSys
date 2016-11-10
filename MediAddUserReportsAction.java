package com.medisys.ravi.actions;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.medisys.ravi.beans.MediAddUserReportsBean;
import com.medisys.ravi.dao.MediAddUserReportsDao;
import com.medisys.ravi.dao.MediAddUserTypeNameDao;
import com.medisys.ravi.lab.actions.MediLabUserReportsAction;
import com.medisys.ravi.lab.beans.MediLabUserReportsBean;
import com.medisys.ravi.lab.dao.MediAgencyTaxDao;
import com.medisys.ravi.pojo.MediUserReportsMasterPojo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MediAddUserReportsAction  extends ActionSupport implements SessionAware, ModelDriven<MediAddUserReportsBean> {

	
	// logger initialization 
	
	private static final Logger logger = Logger.getLogger(MediLabUserReportsAction.class);
	
	// fields	
	private Map<String,Object> session;
	private MediAddUserReportsBean add_user_report_bean;
	private String update_msg;
	private Integer max_report_id;
	private List<MediAddUserReportsBean> user_report_list;
	
	
	
	// getters and setters 
	
    public String getUpdate_msg() {
		return update_msg;
	}


	public void setUpdate_msg(String update_msg) {
		this.update_msg = update_msg;
	}


	public Integer getMax_report_id() {
		return max_report_id;
	}


	public void setMax_report_id(Integer max_report_id) {
		this.max_report_id = max_report_id;
	}

	
	// constructor
	
	public List<MediAddUserReportsBean> getUser_report_list() {
		return user_report_list;
	}


	public void setUser_report_list(List<MediAddUserReportsBean> user_report_list) {
		this.user_report_list = user_report_list;
	}


	public MediAddUserReportsAction(){
	
		 add_user_report_bean= new MediAddUserReportsBean();
	}
	
	


	public String execute(){
		
		max_report_id=MediAddUserReportsDao.getMaxTableId();
		max_report_id=max_report_id+1;
	//	agency_list=MediSysUtilityMethods.getActiveAgencyList();
		user_report_list=MediAddUserReportsDao.getDataForDisplay();	
		return SUCCESS;
	}
	
	
	public String saveOrUpdateReportName(){
		
		
		if(add_user_report_bean!=null){
			
		/*if(!Med.validateForm(state_bean))
	      {
		    return ERROR;
	       }*/
		String state_msg=MediAddUserReportsDao.AddOrUpdateReport(add_user_report_bean);
		if(state_msg.equals("saved"))
		{
			//session.put("error", "User Type Details Saved Successfuly..");
			update_msg="Report Details Saved Successfuly..";
			logger.info("Report Details Saved Successfuly..");
			return SUCCESS;
		} if(state_msg.equals("updated"))
		{
			//session.put("error", "User Type Details Updated Successfuly..");
			update_msg="Report Details Updated Successfuly..";
			logger.error("Report Details Updated Successfuly..");
			return SUCCESS;
		}
		 
		 if(state_msg.equals("deleted"))
			{
				//session.put("error", "User Type Details Deleted Successfuly..");
				update_msg="Report Details Deleted Successfuly..";
				logger.error("Report Details Deleted Successfuly..");
				return SUCCESS;
			}
		 if(state_msg.equals("error"))
			{
				//session.put("error", "User Type Details not updated.");
				update_msg="Report Details not updated.";
				logger.error("Report Details not updated...");
				return SUCCESS;
			}
		return ERROR;
		
		
	}

	
	else
	{
		//session.put("error", "Bean initialization failed..");
		update_msg="Bean initialization failed..";
		logger.error("Bean is setted to null...");
		return ERROR;
	}
	
		
	}
	
	
	
	// bean injection
	@Override
	public MediAddUserReportsBean getModel() {
		// TODO Auto-generated method stub
		return add_user_report_bean;
	}

	
	
	
	// session injection
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
	    this.session=session;	
	}

}
