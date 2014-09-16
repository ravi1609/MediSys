package com.medisys.ravi.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.medisys.ravi.beans.LoginBean;
import com.medisys.ravi.pojo.LoginMasterPojo;
import com.medisys.ravi.utility.HibernateSessionFactoryProvider;

public class LoginDao  {
	
	//fields
	
	//@SessionTarget
	private static Session session;
	//@TransactionTarget
	private Transaction tx;
	private static String validateUser;
	private static Criteria criteria;
	private static List<LoginMasterPojo> login_data_list;
	private static Iterator iterator;
	private static LoginMasterPojo login_master_pojo;
	private static String username;
	private static String password;
	private static Integer record_status;
	private static Integer login_flag;
	
	
	
	
	
	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		LoginDao.username = username;
	}

	public static String getPassword() {
		return password;
	}



	public static void setPassword(String password) {
		LoginDao.password = password;
	}


	public static Integer getRecord_status() {
		return record_status;
	}


	public static void setRecord_status(Integer record_status) {
		LoginDao.record_status = record_status;
	}

	public static Integer getLogin_flag() {
		return login_flag;
	}



	public static void setLogin_flag(Integer login_flag) {
		LoginDao.login_flag = login_flag;
	}

    public static LoginMasterPojo getLogin_master_pojo() {
		return login_master_pojo;
	}

    public static void setLogin_master_pojo(LoginMasterPojo login_master_pojo) {
		LoginDao.login_master_pojo = login_master_pojo;
	}


	public static List<LoginMasterPojo> getLogin_data_list() {
		return login_data_list;
	}


	public static void setLogin_data_list(List<LoginMasterPojo> login_data_list) {
		LoginDao.login_data_list = login_data_list;
	}


	public static String ValidateUser(LoginBean loginBean) {

		if (session == null) {
			System.out.println("hello session is null");
			try {
				// session=
				// com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getNewSession();
				session = HibernateSessionFactoryProvider.getSessionFactory()
						.getCurrentSession();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!session.isOpen())
				throw new NullPointerException(
						"Fix the code: session's not here");
		}

		criteria = session.createCriteria(LoginMasterPojo.class);
		criteria.add(Restrictions.eq("USERNAME", loginBean.getUsername()));
		login_data_list = criteria.list();
		iterator = login_data_list.iterator();
		while (iterator.hasNext()) {
			login_master_pojo = new LoginMasterPojo();
			login_master_pojo = (LoginMasterPojo) iterator.next();
			password = login_master_pojo.getPass_word();
			record_status = login_master_pojo.getRecord_status();
			login_flag = login_master_pojo.getLogin_flag();

		}
		if (loginBean.getPassword() == password) {
			validateUser = "success";
		} else if (record_status != 0) {
			validateUser = "deactive";
		} else if (login_flag == 1) {
			validateUser = "loggedin";
		}
		return validateUser;
	}

}
