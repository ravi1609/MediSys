package com.medisys.ravi.utility;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.Work;

import com.medisys.ravi.beans.MediCityMasterDisplayBean;
import com.medisys.ravi.beans.UserProfileUpdateBean;
import com.medisys.ravi.lab.beans.AgencyProfileUpdateBean;
import com.medisys.ravi.lab.pojo.MediUserFileDownloadMasterPojo;
import com.medisys.ravi.pojo.AgencyRegistrationPojo;
import com.medisys.ravi.pojo.MediCityMasterPojo;
import com.medisys.ravi.pojo.MediCountryMasterPojo;
import com.medisys.ravi.pojo.MediCurrencyMasterPojo;
import com.medisys.ravi.pojo.MediLabTypeMasterPojo;
import com.medisys.ravi.pojo.MediMenuGroupMasterPojo;
import com.medisys.ravi.pojo.MediMenuMasterPojo;
import com.medisys.ravi.pojo.MediRoleMasterPojo;
import com.medisys.ravi.pojo.MediSampleMasterPojo;
import com.medisys.ravi.pojo.MediServiceCategoryMasterPojo;
import com.medisys.ravi.pojo.MediServiceMasterPojo;
import com.medisys.ravi.pojo.MediStateMasterPojo;
import com.medisys.ravi.pojo.MediSubTestMasterPojo;
import com.medisys.ravi.pojo.MediTestComboMasterPojo;
import com.medisys.ravi.pojo.MediTestMasterPojo;
import com.medisys.ravi.pojo.MediUserReportsMasterPojo;
import com.medisys.ravi.pojo.ReportStatusMasterPojo;
import com.medisys.ravi.pojo.UserRegistrationPojo;

public class MediSysUtilityMethods {
//fields
	
	private static final Logger logger= Logger.getLogger(MediSysUtilityMethods.class);
	
	
	
	private static Session session;
	private static Transaction tr;
	
	// getting the max sr no utility method written on 31-07-2015
	
	
	public static Integer getMaxTableId(String pojonamewithclass,String property){
		Integer a=0;
		try{
			
			if (session == null || session.isOpen() == false) 
	  		{
	  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	  		}
	  		session.getTransaction().begin();
			
			Criteria criteria=session.createCriteria(MediStateMasterPojo.class);
			ProjectionList prj=Projections.projectionList();
			prj.add(Projections.max("STATE_ID"));
			criteria.setProjection(prj);			
	  		List max=criteria.list();
	  		
	  		if(max!=null && max.size()>0){
				a=(Integer)max.get(0);
				}
				
		}catch(Exception e){
			logger.error("Error in fetching the max id ..."+e.getMessage());
		}
		finally{
			try{
			
				session.close();
				logger.info("session closed..");
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}
		return a;
		
	}
	
	
	
	
	
	public static String getCountryDate(final String country_code){
		String date="";
		
		
		 final List res=new ArrayList<String>();
		 final List date_func= new ArrayList<String>();
			if (session == null || session.isOpen() == false) 
	  		{
	  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	  		}
		 
		 
		Work work=new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
			
				
				// for date generation function
				String fileCall = "{? = call getdate(?)}";
				CallableStatement stmt_1 = connection.prepareCall(fileCall);
				// change this parameter according to the country id 
				stmt_1.setString(2,country_code);
				stmt_1.registerOutParameter(1, Types.VARCHAR);
				stmt_1.execute();
				//result_md5=stmt.getString(1);
				//System.out.println(result_md5);
				date_func.add(stmt_1.getString(1));
				
			}
		};
		
		session.doWork(work);
	  date =(String)date_func.get(0);
		
	return date;
	}
		
	
/*	
	public static Date getDate(final String country_code){
		Date date=null;
		
		
		 final List res=new ArrayList<String>();
		 final List date_func= new ArrayList<Date>();
			if (session == null || session.isOpen() == false) 
	  		{
	  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	  		}
		 
		 
		Work work=new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
			
				
				// for date generation function
				String fileCall = "{? = call getdate(?)}";
				CallableStatement stmt_1 = connection.prepareCall(fileCall);
				// change this parameter according to the country id 
				//java.sql.Date sqlDate = new java.sql.Date(country_code.getTime());
				
				stmt_1.setString(2,country_code);
				stmt_1.registerOutParameter(1, Types.VARCHAR);
				stmt_1.execute();
				//result_md5=stmt.getString(1);
				//System.out.println(result_md5);
				date_func.add((Date)stmt_1.getDate(1));
				
			}
		};
		
		session.doWork(work);
	  date =(Date)date_func.get(0);
		
	return date;
	}*/
	
	// get user names 
	public static String getUsername(final String country_code,final String user_type){
		String username=null;
		
		
		
		 final List username_func= new ArrayList<String>();
			if (session == null || session.isOpen() == false) 
	  		{
	  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	  		}
		 
		 
		Work work=new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
			
				
				// for date generation function
				String fileCall = "{? = call MEDISYS_AGT_USR_ID(?,?)}";
				CallableStatement stmt_1 = connection.prepareCall(fileCall);
				// change this parameter according to the country id 
				//java.sql.Date sqlDate = new java.sql.Date(country_code.getTime());
				
				stmt_1.setString(2,country_code);
				stmt_1.setString(3,user_type);
				stmt_1.registerOutParameter(1, Types.VARCHAR);
				stmt_1.execute();
				//result_md5=stmt.getString(1);
				//System.out.println(result_md5);
				username_func.add((String)stmt_1.getString(1));
				
			}
		};
		
		session.doWork(work);
		username =(String)username_func.get(0);
		
	return username;
	}
	
	
	// get user file no    
	
	public static String getUserFileNumber(final String country_code){
		String file_number=null;		
		 final List file_no_func= new ArrayList<String>();
			if (session == null || session.isOpen() == false) 
	  		{
	  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	  		}	 
		Work work=new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
						
				
				// for file no generation function
				String fileCall = "{? = call MEDISYS_FILEGEN(?)}";
				CallableStatement stmt_1 = connection.prepareCall(fileCall);
				// change this parameter according to the country id 
				stmt_1.setString(2,country_code);
				stmt_1.registerOutParameter(1, Types.VARCHAR);
				stmt_1.execute();			
				file_no_func.add(stmt_1.getString(1));
				
				
			}
		};
		
		session.doWork(work);
		file_number =(String)file_no_func.get(0);
		
	return file_number;
	}
	
	
	//getting the file no as on 23-06-2016
	
	public static String getUserOrederId(final String user_id){
		String order_id=null;
		
	//	user_id=user_id.substring(0,3);
		
		 final List file_no_func= new ArrayList<String>();
			if (session == null || session.isOpen() == false) 
	  		{
	  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	  		}
		 
		 
		Work work=new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				// for file no generation function
				String fileCall = "{? = call MEDISYS_ORDERID(?)}";
				CallableStatement stmt_1 = connection.prepareCall(fileCall);
				// change this parameter according to the country id 
				stmt_1.setString(2,user_id);
				stmt_1.registerOutParameter(1, Types.VARCHAR);
				stmt_1.execute();
				
				file_no_func.add(stmt_1.getString(1));								
			}
		};
		
		session.doWork(work);
		order_id =(String)file_no_func.get(0);
		
	return order_id;
	}
	
	
	
	public static String getUserPasswordMD5(final String passtoken){
		String passmd5=null;
		
		
		
		 final List md5_no_func= new ArrayList<String>();
			if (session == null || session.isOpen() == false) 
	  		{
	  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	  		}
		 
		 
		Work work=new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
			
				
				
				// for file no generation function
				String fileCall = "{? = call GEN_MD5(?)}";
				CallableStatement stmt_1 = connection.prepareCall(fileCall);
				// change this parameter according to the country id 
				stmt_1.setString(2,passtoken);
				stmt_1.registerOutParameter(1, Types.VARCHAR);
				stmt_1.execute();
				//result_md5=stmt.getString(1);
				System.out.println(stmt_1.getString(1));
				
				md5_no_func.add(stmt_1.getString(1));
				
				
			}
		};
		
		session.doWork(work);
		passmd5 =(String)md5_no_func.get(0);
		
	return passmd5;
	}
	
	
	
	
	
	
	
	
	
	
	public static HashMap<Integer,String> getCurrencyList(){
		HashMap<Integer,String> getCurrencyList=null;
		
		try{
			getCurrencyList=new HashMap<Integer,String>();
			
			 if (session == null || session.isOpen() == false) 
		  		{
		  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
		  			} 
		  			
		  				session.getTransaction().begin();
			
		  				Criteria criteria = session.createCriteria(MediCurrencyMasterPojo.class);
		  				criteria.add(Restrictions.eq("RECORD_STATUS",0));
		  				ProjectionList prj = Projections.projectionList();
		  				prj.add(Projections.property("CURRENCY_ID")).add(Projections.property("CURRENCY_NAME"));
		  				criteria.setProjection(prj).addOrder(Order.asc("CURRENCY_NAME"));		
		  				List<Object[]> MsnList=(List<Object[]>)criteria.list();
		  				for(Object[] obj:MsnList){
		  					getCurrencyList.put((Integer)obj[0], (String)obj[1]);
		  				}
		  				
		}catch(Exception e){
			logger.error("error in fetching the currency list .."+e);
			getCurrencyList=new HashMap<Integer,String>();
		}
		finally{
			try{			
				session.close();
				logger.info("session closed.. fetching the currency list .");
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}
		
		return getCurrencyList;
		
	}
	
	
	
	// this method is written for getting the mission list on 22-07-15
	
		public static HashMap<String,String> getCountryList(){
			HashMap<String,String> getCountryList=null;
			
			try{
				getCountryList=new HashMap<String,String>();
				
				 if (session == null || session.isOpen() == false) 
			  		{
			  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
			  			} 
			  			
			  				session.getTransaction().begin();
				
			  				Criteria criteria = session.createCriteria(MediCountryMasterPojo.class);
			  				criteria.add(Restrictions.eq("RECORD_STATUS",0));
			  				ProjectionList prj = Projections.projectionList();
			  				prj.add(Projections.property("COUNTRY_ID")).add(Projections.property("COUNTRY_NAME"));
			  				criteria.setProjection(prj).addOrder(Order.asc("COUNTRY_NAME"));		
			  				List<Object[]> MsnList=(List<Object[]>)criteria.list();
			  				for(Object[] obj:MsnList){
			  					getCountryList.put((String)obj[0], (String)obj[1]);
			  				}
			  				
			}catch(Exception e){
				logger.error("error in fetching the country name .."+e);
				getCountryList=new HashMap<String,String>();
			}
			finally{
				try{			
					session.close();
					logger.info("session closed..fetching the country name .");
				}catch(Exception e){
					logger.error(e.getMessage());
				}
			}
			
			return getCountryList;
			
		}
	
	
	
	
		public static Date getDateDDMMYYFormateToDate(final Date country_code){
			Date date=null;
			
			
			 final List res=new ArrayList<String>();
			 final List date_func= new ArrayList<Date>();
				if (session == null || session.isOpen() == false) 
		  		{
		  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
		  		}
			 
			 
			Work work=new Work() {
				
				@Override
				public void execute(Connection connection) throws SQLException {
				
					
					// for date generation function
					String fileCall = "{? = call to_date(?,'yyyy-mm-dd')}";
					CallableStatement stmt_1 = connection.prepareCall(fileCall);
					// change this parameter according to the country id 
					java.sql.Date sqlDate = new java.sql.Date(country_code.getTime());
					stmt_1.setDate(2,sqlDate);
					stmt_1.registerOutParameter(1, Types.VARCHAR);
					stmt_1.execute();
					//result_md5=stmt.getString(1);
					//System.out.println(result_md5);
					date_func.add(stmt_1.getString(1));
					
				}
			};
			
			session.doWork(work);
		  date =(Date)date_func.get(0);
			
		return date;
		}
	
		public static Date getDateDDMMYYYYFormateToDate(final Date country_code){
			Date date=null;
			
			
			 final List res=new ArrayList<String>();
			 final List date_func= new ArrayList<Date>();
				if (session == null || session.isOpen() == false) 
		  		{
		  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
		  		}
			 
			 
			Work work=new Work() {
				
				@Override
				public void execute(Connection connection) throws SQLException {
				
					
					// for date generation function
					String fileCall = "{? = call to_date(?,'dd-mm-yyyy')}";
					CallableStatement stmt_1 = connection.prepareCall(fileCall);
					// change this parameter according to the country id 
					java.sql.Date sqlDate = new java.sql.Date(country_code.getTime());
					stmt_1.setDate(2,sqlDate);
					stmt_1.registerOutParameter(1, Types.VARCHAR);
					stmt_1.execute();
					//result_md5=stmt.getString(1);
					//System.out.println(result_md5);
					date_func.add(stmt_1.getString(1));
					
				}
			};
			
			session.doWork(work);
		  date =(Date)date_func.get(0);
			
		return date;
		}
		// method is writtemn for the date conversion to tochar
		
	
		public static String getDateDDMMYYYYFormateToChar(final Date country_code){
			String date="";
			
			
			 final List res=new ArrayList<String>();
			 final List date_func= new ArrayList<String>();
				if (session == null || session.isOpen() == false) 
		  		{
		  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
		  		}
			 
			 
			Work work=new Work() {
				
				@Override
				public void execute(Connection connection) throws SQLException {
				
					
					// for date generation function
					String fileCall = "{? = call to_char(?,'dd-mm-yyyy')}";
					CallableStatement stmt_1 = connection.prepareCall(fileCall);
					// change this parameter according to the country id 
					java.sql.Date sqlDate = new java.sql.Date(country_code.getTime());
					
					stmt_1.setDate(2,sqlDate);
					stmt_1.registerOutParameter(1, Types.VARCHAR);
					stmt_1.execute();
					//result_md5=stmt.getString(1);
					//System.out.println(result_md5);
					date_func.add(stmt_1.getString(1));
					
				}
			};
			
			session.doWork(work);
		  date =(String)date_func.get(0);
			
		return date;
		}
	
	
		// this method is written for getting the state list on 27-07-15
		
		public static HashMap<Integer,String> getStateList(){
			HashMap<Integer,String> getStateList=null;
			
			try{
				getStateList=new HashMap<Integer,String>();
				
				 if (session == null || session.isOpen() == false) 
			  		{
			  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
			  			} 
			  			
			  				session.getTransaction().begin();
				
			  				Criteria criteria = session.createCriteria(MediStateMasterPojo.class);
			  				criteria.add(Restrictions.eq("RECORD_STATUS",0));
			  				ProjectionList prj = Projections.projectionList();
			  				prj.add(Projections.property("STATE_ID")).add(Projections.property("STATE_NAME"));
			  				criteria.setProjection(prj).addOrder(Order.asc("STATE_NAME"));		
			  				List<Object[]> MsnList=(List<Object[]>)criteria.list();
			  				for(Object[] obj:MsnList){
			  					getStateList.put((Integer)obj[0], (String)obj[1]);
			  				}
			  				
			}catch(Exception e){
				logger.error("error in fetching the state name .."+e);
				getStateList=new HashMap<Integer,String>();
			}
			finally{
				try{			
					session.close();
					logger.info("session closed..fetching the state name .");
				}catch(Exception e){
					logger.error(e.getMessage());
				}
			}
			
			return getStateList;
			
		}
		
		// this method is written for getting the state list on 27-07-15
		
				public static HashMap<Integer,String> getCityList(){
					HashMap<Integer,String> getStateList=null;
					
					try{
						getStateList=new HashMap<Integer,String>();
						
						 if (session == null || session.isOpen() == false) 
					  		{
					  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
					  			} 
					  			
					  				session.getTransaction().begin();
						
					  				Criteria criteria = session.createCriteria(MediCityMasterPojo.class);
					  				criteria.add(Restrictions.eq("RECORD_STATUS",0));
					  				ProjectionList prj = Projections.projectionList();
					  				prj.add(Projections.property("CITY_ID")).add(Projections.property("CITY_NAME"));
					  				criteria.setProjection(prj).addOrder(Order.asc("CITY_NAME"));		
					  				List<Object[]> MsnList=(List<Object[]>)criteria.list();
					  				for(Object[] obj:MsnList){
					  					getStateList.put((Integer)obj[0], (String)obj[1]);
					  				}
					  				
					}catch(Exception e){
						logger.error("error in fetching the city name .."+e);
						getStateList=new HashMap<Integer,String>();
					}
					finally{
						try{			
							session.close();
							logger.info("session closed..fetching the city name .");
						}catch(Exception e){
							logger.error(e.getMessage());
						}
					}
					
					return getStateList;
					
				}
				public static HashMap<Integer,String> getCityListStateWise(Integer state_id){
					HashMap<Integer,String> getStateList=null;
					
					try{
						getStateList=new HashMap<Integer,String>();
						
						 if (session == null || session.isOpen() == false) 
					  		{
					  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
					  			} 
					  			
					  				session.getTransaction().begin();
						
					  				Criteria criteria = session.createCriteria(MediCityMasterPojo.class);
					  				criteria.add(Restrictions.eq("RECORD_STATUS",0)).add(Restrictions.eq("STATE_ID", state_id));
					  				ProjectionList prj = Projections.projectionList();
					  				prj.add(Projections.property("CITY_ID")).add(Projections.property("CITY_NAME"));
					  				criteria.setProjection(prj).addOrder(Order.asc("CITY_NAME"));		
					  				List<Object[]> MsnList=(List<Object[]>)criteria.list();
					  				for(Object[] obj:MsnList){
					  					getStateList.put((Integer)obj[0], (String)obj[1]);
					  				}
					  				
					}catch(Exception e){
						logger.error("error in fetching the city name state wise.."+e);
						getStateList=new HashMap<Integer,String>();
					}
					finally{
						try{			
							session.close();
							logger.info("session closed..fetching the city name state wise .");
						}catch(Exception e){
							logger.error(e.getMessage());
						}
					}
					
					return getStateList;
					
				}
				
				
				
				
				
				
				
				
				
	// this method is written for getting the state list on 27-07-15
		
		public static HashMap<Integer,String> getStateListWithStateCode(){
			HashMap<Integer,String> getStateList=null;
			
			try{
				getStateList=new HashMap<Integer,String>();
				
				 if (session == null || session.isOpen() == false) 
			  		{
			  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
			  			} 
			  			
			  				session.getTransaction().begin();
				
			  				Criteria criteria = session.createCriteria(MediStateMasterPojo.class);
			  				criteria.add(Restrictions.eq("RECORD_STATUS",0));
			  				ProjectionList prj = Projections.projectionList();
			  				prj.add(Projections.property("STATE_CODE")).add(Projections.property("STATE_NAME"));
			  				criteria.setProjection(prj).addOrder(Order.asc("STATE_NAME"));		
			  				List<Object[]> MsnList=(List<Object[]>)criteria.list();
			  				for(Object[] obj:MsnList){
			  					getStateList.put((Integer)obj[0], (String)obj[1]);
			  				}
			  				
			}catch(Exception e){
				logger.error("error in fetching the state and state code name .."+e);
				getStateList=new HashMap<Integer,String>();
			}
			finally{
				try{			
					session.close();
					logger.info("session closed..fetching the state and state code name .");
				}catch(Exception e){
					logger.error(e.getMessage());
				}
			}
			
			return getStateList;
			
		}
		
		
		
		
		
		// this method is written for getting the state list on 27-07-15
		
				public static HashMap<Integer,String> getStateListCountryWise(String country_id){
					HashMap<Integer,String> getStateList=null;
					
					try{
						getStateList=new HashMap<Integer,String>();
						
						 if (session == null || session.isOpen() == false) 
					  		{
					  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
					  			} 
					  			
					  				session.getTransaction().begin();
						
					  				Criteria criteria = session.createCriteria(MediStateMasterPojo.class);
					  				criteria.add(Restrictions.eq("RECORD_STATUS",0)).add(Restrictions.eq("COUNTRY_ID", country_id));
					  				ProjectionList prj = Projections.projectionList();
					  				prj.add(Projections.property("STATE_ID")).add(Projections.property("STATE_NAME"));
					  				criteria.setProjection(prj).addOrder(Order.asc("STATE_NAME"));		
					  				List<Object[]> MsnList=(List<Object[]>)criteria.list();
					  				for(Object[] obj:MsnList){
					  					getStateList.put((Integer)obj[0], (String)obj[1]);
					  				}
					  				
					}catch(Exception e){
						logger.error("error in fetching the state country wise name .."+e);
						getStateList=new HashMap<Integer,String>();
					}
					finally{
						try{			
							session.close();
							logger.info("session closed..fetching the state country wise name .");
						}catch(Exception e){
							logger.error(e.getMessage());
						}
					}
					
					return getStateList;
					
				}
				
		
		
		// get state names state code wise
				public static HashMap<Integer,String> getStateListStateCodeWise(String state_code){
					HashMap<Integer,String> getStateList=null;
					
					try{
						getStateList=new HashMap<Integer,String>();
						
						 if (session == null || session.isOpen() == false) 
					  		{
					  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
					  			} 
					  			
					  				session.getTransaction().begin();
						
					  				Criteria criteria = session.createCriteria(MediStateMasterPojo.class);
					  				criteria.add(Restrictions.eq("RECORD_STATUS",0)).add(Restrictions.eq("STATE_CODE", state_code));
					  				ProjectionList prj = Projections.projectionList();
					  				prj.add(Projections.property("STATE_ID")).add(Projections.property("STATE_NAME"));
					  				criteria.setProjection(prj).addOrder(Order.asc("STATE_NAME"));		
					  				List<Object[]> MsnList=(List<Object[]>)criteria.list();
					  				for(Object[] obj:MsnList){
					  					getStateList.put((Integer)obj[0], (String)obj[1]);
					  				}
					  				
					}catch(Exception e){
						logger.error("error in fetching the  state code wise name .."+e);
						getStateList=new HashMap<Integer,String>();
					}
					finally{
						try{			
							session.close();
							logger.info("session closed..fetching the state code wise name .");
						}catch(Exception e){
							logger.error(e.getMessage());
						}
					}
					
					return getStateList;
					
				}
				
				
				// get state names state code wise
				public static HashMap<Integer,String> getStateListStateCodeAndCountryWise(String state_code,String country_code){
					HashMap<Integer,String> getStateList=null;
					
					try{
						getStateList=new HashMap<Integer,String>();
						
						 if (session == null || session.isOpen() == false) 
					  		{
					  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
					  			} 
					  			
					  				session.getTransaction().begin();
						
					  				Criteria criteria = session.createCriteria(MediStateMasterPojo.class);
					  				criteria.add(Restrictions.eq("RECORD_STATUS",0)).add(Restrictions.eq("STATE_CODE", state_code)).add(Restrictions.eq("COUNTRY_ID", country_code));
					  				ProjectionList prj = Projections.projectionList();
					  				prj.add(Projections.property("STATE_ID")).add(Projections.property("STATE_NAME"));
					  				criteria.setProjection(prj).addOrder(Order.asc("STATE_NAME"));		
					  				List<Object[]> MsnList=(List<Object[]>)criteria.list();
					  				for(Object[] obj:MsnList){
					  					getStateList.put((Integer)obj[0], (String)obj[1]);
					  				}
					  				
					}catch(Exception e){
						logger.error("error in fetching the  state code and country wise name .."+e);
						getStateList=new HashMap<Integer,String>();
					}
					finally{
						try{			
							session.close();
							logger.info("session closed..fetching the state code and country wise name .");
						}catch(Exception e){
							logger.error(e.getMessage());
						}
					}
					
					return getStateList;
					
				}
				
				// get the state code state id wise

				public static String getStateCode(Integer state_id){
					String a="";
					try{
						
						if (session == null || session.isOpen() == false) 
				  		{
				  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
				  		}
				  		session.getTransaction().begin();
						
						Criteria criteria=session.createCriteria(MediStateMasterPojo.class);
						criteria.add(Restrictions.eq("RECORD_STATUS",0)).add(Restrictions.eq("STATE_ID", state_id));
						ProjectionList prj=Projections.projectionList();
						prj.add(Projections.property("STATE_CODE"));
						criteria.setProjection(prj);			
				  		List max=criteria.list();
				  		logger.info("getting the state code ");
				  		if(max!=null && max.size()>0)
							a=(String)max.get(0);
							
							if(a==null)
								a="NA";
							
					}catch(Exception e){
						logger.error("Error in fetching the state code ..."+e.getMessage());
					}
					finally{
						try{
						
							session.close();
							logger.info("session closed..");
						}catch(Exception e){
							logger.error(e.getMessage());
						}
					}
					return a;
					
				}
				
				
			
				
				
				
				
				
				
				
				
				
				
		
				public static HashMap<Integer,String> GetTestIdName(){
					int test_id =0;
					String test_name;
					HashMap<Integer,String> test_id_name=new HashMap<Integer,String>();
					try{
						
						
						if (session == null || session.isOpen() == false) 
						{
						
									session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
							}
							
							
						//	session.getTransaction().begin();
							 
						Criteria	criteria = session.createCriteria(MediTestMasterPojo.class);
							//criteria.add(Restrictions.eq("user_name", ""));
							criteria.add(Restrictions.eq("RECORD_STATUS",0));
							criteria.add(Restrictions.eq("DELETE_FLAG",0));
							criteria.setCacheable(true);
							ProjectionList proj = Projections.projectionList();
							proj.add(Projections.property("TEST_ID"));
							proj.add(Projections.property("TEST_NAME"));
							criteria.setProjection(proj);
							List<Object[]> test_list=criteria.list();
						    Iterator itr=test_list.iterator();
							if(test_list!=null && test_list.size()>0){
								for (Object[] row : test_list) {
									test_id_name.put((Integer)row[0], (String)row[1]);
							}
								
							}
							else{
								test_id_name= new HashMap<Integer,String>();
							}
							//session.getTransaction().commit();
							logger.info("transaction comitted..");
							
					}catch(Exception e)
					{
						session.getTransaction().rollback();
						logger.error("error:-"+e.getMessage());
						
						}
					finally{
						try{
						
							session.close();
							logger.info("session closed..");
						}catch(Exception e){
							logger.error(e.getMessage());
						}
					}
					
					return test_id_name;
					
					
					
				}

		
		
		
				public static HashMap<Integer,String> GetSubTestIdName(){
					int test_id =0;
					String test_name;
					HashMap<Integer,String> test_id_name=new HashMap<Integer,String>();
					try{
						
						
						if (session == null || session.isOpen() == false) 
						{
						
									session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
							}
							
							
						//	session.getTransaction().begin();
							 
						Criteria	criteria = session.createCriteria(MediSubTestMasterPojo.class);
							//criteria.add(Restrictions.eq("user_name", ""));
							criteria.add(Restrictions.eq("RECORD_STATUS",0));
							criteria.add(Restrictions.eq("DELETE_FLAG",0));
							criteria.setCacheable(true);
							ProjectionList proj = Projections.projectionList();
							proj.add(Projections.property("SUB_TEST_ID"));
							proj.add(Projections.property("SUB_TEST_NAME"));
							criteria.setProjection(proj);
							List<Object[]> test_list=criteria.list();
						    Iterator itr=test_list.iterator();
							if(test_list!=null && test_list.size()>0){
								for (Object[] row : test_list) {
									test_id_name.put((Integer)row[0], (String)row[1]);
							}
								
							}
							else{
								test_id_name= new HashMap<Integer,String>();
							}
							//session.getTransaction().commit();
							logger.info("transaction comitted..");
							
					}catch(Exception e)
					{
						session.getTransaction().rollback();
						logger.error("error:-"+e.getMessage());
						
						}
					finally{
						try{
						
							session.close();
							logger.info("session closed..");
						}catch(Exception e){
							logger.error(e.getMessage());
						}
					}
					
					return test_id_name;
					
					
					
				}
		
		
		
		
				public static HashMap<Integer,String> GetSubTestIdNameTestWise(Integer test_id){
					
					String test_name;
					HashMap<Integer,String> test_id_name=new HashMap<Integer,String>();
					try{
						
						
						if (session == null || session.isOpen() == false) 
						{
						
									session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
							}
							
							
						//	session.getTransaction().begin();
							 
						Criteria	criteria = session.createCriteria(MediSubTestMasterPojo.class);
							//criteria.add(Restrictions.eq("user_name", ""));
							criteria.add(Restrictions.eq("RECORD_STATUS",0)).add(Restrictions.eq("TEST_ID", test_id));
							criteria.setCacheable(true);
							ProjectionList proj = Projections.projectionList();
							proj.add(Projections.property("SUB_TEST_ID"));
							proj.add(Projections.property("SUB_TEST_NAME"));
							criteria.setProjection(proj);
							List<Object[]> test_list=criteria.list();
						    Iterator itr=test_list.iterator();
							if(test_list!=null && test_list.size()>0){
								for (Object[] row : test_list) {
									test_id_name.put((Integer)row[0], (String)row[1]);
							}
								
							}
							else{
								test_id_name= new HashMap<Integer,String>();
							}
							//session.getTransaction().commit();
							logger.info("transaction comitted..");
							
					}catch(Exception e)
					{
						session.getTransaction().rollback();
						logger.error("error:-"+e.getMessage());
						
						}
					finally{
						try{
						
							session.close();
							logger.info("session closed..");
						}catch(Exception e){
							logger.error(e.getMessage());
						}
					}
					
					return test_id_name;
					
					
					
				}
		
	
		public static String getFomatedDate(String date1){
			DateFormat originalFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
		    DateFormat targetFormat = new SimpleDateFormat("dd-MM-yy");
		    Date date = null;
			try {
				date = originalFormat.parse(date1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				logger.error("error in formating the date "+e.getMessage());
			}
		    String formattedDate = targetFormat.format(date); 
		    logger.info("formated date is "+formattedDate);
			
			
			
			return formattedDate;
			
		}
		
		
/*		public static Date getCurrentDate()
		 {
							 java.sql.Date sqlDate = null;
				 try
				 {
			  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
						session.beginTransaction();
					// CallableStatement cs = ((Object) session).connection().prepareCall("{ ? = call SDATE(?) }");		
						session.beginTransaction();
						
						CallableStatement cs =  session.connection().prepareCall("{ ? = call SDATE(?) }");		
							cs.registerOutParameter(1, OracleTypes.DATE);
							cs.setString(2, missionId);		
							cs.execute();
							 sqlDate=cs.getDate(1);
				     CallableStatement cs =(CallableStatement) session.createStoredProcedureCall("{? = call getdate(?)}");
					 cs.registerOutParameter(1, OracleTypes.DATE);
						cs.setString(2, "IND");		
						cs.execute();
						 sqlDate=((CallableStatement) cs).getDate(1); 
				 }
				 catch (SQLException e) {
			      e.printStackTrace();
			      }
				return sqlDate;


		
		
		 }	*/
		

		private static java.sql.Date sqlDate=null ;
	    public static Date getDate(final String country_code)
	    {
	        Session session =null;
	       
	        try{
	        	session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	            session.beginTransaction();
	            
	            session.doWork(new Work(){
	            	
	              public void execute(Connection connection) throws SQLException {
	            	
	            	  CallableStatement statement =null;
	                 //   String sqlString ="{? = call getdate(?)}"; to_date(getdate('IND'),'dd-mm-yyyy')
	            	  String sqlString ="{? = call to_date(getdate(?),'dd-mm-yyyy')}";
	                    statement = connection.prepareCall(sqlString);
	                    statement.registerOutParameter(1, OracleTypes.DATE);
	                    statement.setString(2, country_code);		
	                 
	                    statement.executeUpdate();
	                    sqlDate=statement.getDate(1); 
	                  
	                }
	            });
	            session.getTransaction().commit();
	        }catch(HibernateException e){
	          
	        }finally{
	            session.close();
	        }
			return sqlDate;
	 
	    }



	 // this method is written for getting the state list on 27-07-15
		
	 		public static HashMap<Integer,String> getServiceList(){
	 			HashMap<Integer,String> getServiceList=null;
	 			
	 			try{
	 				getServiceList=new HashMap<Integer,String>();
	 				
	 				 if (session == null || session.isOpen() == false) 
	 			  		{
	 			  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	 			  			} 
	 			  			
	 			  				session.getTransaction().begin();
	 				
	 			  				Criteria criteria = session.createCriteria(MediServiceMasterPojo.class);
	 			  				criteria.add(Restrictions.eq("RECORD_STATUS",0));
	 			  				ProjectionList prj = Projections.projectionList();
	 			  				prj.add(Projections.property("SERVICE_ID")).add(Projections.property("SERVICE_NAME"));
	 			  				criteria.setProjection(prj).addOrder(Order.asc("SERVICE_NAME"));		
	 			  				List<Object[]> ServiceList=(List<Object[]>)criteria.list();
	 			  				for(Object[] obj:ServiceList){
	 			  					getServiceList.put((Integer)obj[0], (String)obj[1]);
	 			  				}
	 			  				
	 			}catch(Exception e){
	 				logger.error("error in fetching the Service name .."+e);
	 				getServiceList=new HashMap<Integer,String>();
	 			}
	 			finally{
	 				try{			
	 					session.close();
	 					logger.info("session closed..fetching the Service name .");
	 				}catch(Exception e){
	 					logger.error(e.getMessage());
	 				}
	 			}
	 			
	 			return getServiceList;
	 			
	 		}

	 		//overloaded methos as on 14-06-2016
	 		
	 		public static HashMap<Integer,String> getServiceList(String username){
	 			HashMap<Integer,String> getServiceList=null;
	 			
	 			try{
	 				getServiceList=new HashMap<Integer,String>();
	 				
	 				 if (session == null || session.isOpen() == false) 
	 			  		{
	 			  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	 			  			} 
	 			  			
	 			  				session.getTransaction().begin();
	 				
	 			  				Criteria criteria = session.createCriteria(MediServiceMasterPojo.class);
	 			  				criteria.add(Restrictions.eq("RECORD_STATUS",0)).add(Restrictions.eq("CREATED_BY", username));
	 			  				ProjectionList prj = Projections.projectionList();
	 			  				prj.add(Projections.property("SERVICE_ID")).add(Projections.property("SERVICE_NAME"));
	 			  				criteria.setProjection(prj).addOrder(Order.asc("SERVICE_NAME"));		
	 			  				List<Object[]> ServiceList=(List<Object[]>)criteria.list();
	 			  				for(Object[] obj:ServiceList){
	 			  					getServiceList.put((Integer)obj[0], (String)obj[1]);
	 			  				}
	 			  				
	 			}catch(Exception e){
	 				logger.error("error in fetching the Service name .."+e);
	 				getServiceList=new HashMap<Integer,String>();
	 			}
	 			finally{
	 				try{			
	 					session.close();
	 					logger.info("session closed..fetching the Service name .");
	 				}catch(Exception e){
	 					logger.error(e.getMessage());
	 				}
	 			}
	 			
	 			return getServiceList;
	 			
	 		}

	 		
	 		
	 		
	 		
	 		
	 		
	 		
	 		
	 		
	 		
	 	// this method is written for getting the state list on 27-07-15
			
	 		public static HashMap<String,String> getActiveAgencyList(){
	 			HashMap<String,String> getActiveAgencyList=null;
	 			
	 			try{
	 				getActiveAgencyList=new HashMap<String,String>();
	 				
	 				 if (session == null || session.isOpen() == false) 
	 			  		{
	 			  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	 			  			} 
	 			  			
	 			  				session.getTransaction().begin();
	 				
	 			  				Criteria criteria = session.createCriteria(AgencyRegistrationPojo.class);
	 			  				criteria.add(Restrictions.eq("RECORD_STATUS",0));
	 			  				ProjectionList prj = Projections.projectionList();
	 			  				prj.add(Projections.property("AGENCY_ID")).add(Projections.property("AGENCY_REGISTERED_NAME"));
	 			  				criteria.setProjection(prj).addOrder(Order.asc("AGENCY_REGISTERED_NAME"));		
	 			  				List<Object[]> ServiceList=(List<Object[]>)criteria.list();
	 			  				for(Object[] obj:ServiceList){
	 			  					getActiveAgencyList.put((String)obj[0], (String)obj[1]);
	 			  				}
	 			  				
	 			}catch(Exception e){
	 				logger.error("error in fetching the Active agency list .."+e);
	 				getActiveAgencyList=new HashMap<String,String>();
	 			}
	 			finally{
	 				try{			
	 					session.close();
	 					logger.info("session closed..fetching Active agency list .");
	 				}catch(Exception e){
	 					logger.error(e.getMessage());
	 				}
	 			}
	 			
	 			return getActiveAgencyList;
	 			
	 		}


	 		 // this method is written for getting the combo list on 27-07-15
			
	 		public static HashMap<Integer,String> getComboList(){
	 			HashMap<Integer,String> getComboList=null;
	 			
	 			try{
	 				getComboList=new HashMap<Integer,String>();
	 				
	 				 if (session == null || session.isOpen() == false) 
	 			  		{
	 			  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	 			  			} 
	 			  			
	 			  				session.getTransaction().begin();
	 				
	 			  				Criteria criteria = session.createCriteria(MediTestComboMasterPojo.class);
	 			  				criteria.add(Restrictions.eq("RECORD_STATUS",0));
	 			  				ProjectionList prj = Projections.projectionList();
	 			  				prj.add(Projections.property("COMBO_ID")).add(Projections.property("COMBO_NAME"));
	 			  				criteria.setProjection(prj).addOrder(Order.asc("COMBO_NAME"));		
	 			  				List<Object[]> ServiceList=(List<Object[]>)criteria.list();
	 			  				for(Object[] obj:ServiceList){
	 			  					getComboList.put((Integer)obj[0], (String)obj[1]);
	 			  				}
	 			  				
	 			}catch(Exception e){
	 				logger.error("error in fetching the Combo name .."+e);
	 				getComboList=new HashMap<Integer,String>();
	 			}
	 			finally{
	 				try{			
	 					session.close();
	 					logger.info("session closed..fetching the Combo name .");
	 				}catch(Exception e){
	 					logger.error(e.getMessage());
	 				}
	 			}
	 			
	 			return getComboList;
	 			
	 		}

	 		
	 		//overloadded
	 		
	 		public static HashMap<Integer,String> getComboList(String username){
	 			HashMap<Integer,String> getComboList=null;
	 			
	 			try{
	 				getComboList=new HashMap<Integer,String>();
	 				
	 				 if (session == null || session.isOpen() == false) 
	 			  		{
	 			  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	 			  			} 
	 			  			
	 			  				session.getTransaction().begin();
	 				
	 			  				Criteria criteria = session.createCriteria(MediTestComboMasterPojo.class);
	 			  				criteria.add(Restrictions.eq("RECORD_STATUS",0)).add(Restrictions.eq("CREATED_BY", username));
	 			  				ProjectionList prj = Projections.projectionList();
	 			  				prj.add(Projections.property("COMBO_ID")).add(Projections.property("COMBO_NAME"));
	 			  				criteria.setProjection(prj).addOrder(Order.asc("COMBO_NAME"));		
	 			  				List<Object[]> ServiceList=(List<Object[]>)criteria.list();
	 			  				for(Object[] obj:ServiceList){
	 			  					getComboList.put((Integer)obj[0], (String)obj[1]);
	 			  				}
	 			  				
	 			}catch(Exception e){
	 				logger.error("error in fetching the Combo name .."+e);
	 				getComboList=new HashMap<Integer,String>();
	 			}
	 			finally{
	 				try{			
	 					session.close();
	 					logger.info("session closed..fetching the Combo name .");
	 				}catch(Exception e){
	 					logger.error(e.getMessage());
	 				}
	 			}
	 			
	 			return getComboList;
	 			
	 		}
	 		
	 		

	 		 // this method is written for getting the service category list on 09-10-15
			
	 		public static HashMap<Integer,String> getServiceCategoryList(){
	 			HashMap<Integer,String> getServiceCategoryList=null;
	 			
	 			try{
	 				getServiceCategoryList=new HashMap<Integer,String>();
	 				
	 				 if (session == null || session.isOpen() == false) 
	 			  		{
	 			  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	 			  			} 
	 			  			
	 			  				session.getTransaction().begin();
	 				
	 			  				Criteria criteria = session.createCriteria(MediServiceCategoryMasterPojo.class);
	 			  				criteria.add(Restrictions.eq("RECORD_STATUS",0));
	 			  				ProjectionList prj = Projections.projectionList();
	 			  				prj.add(Projections.property("SERVICE_CAT_ID")).add(Projections.property("SERVICE_CAT_NAME"));
	 			  				criteria.setProjection(prj).addOrder(Order.asc("SERVICE_CAT_NAME"));		
	 			  				List<Object[]> ServiceList=(List<Object[]>)criteria.list();
	 			  				for(Object[] obj:ServiceList){
	 			  					getServiceCategoryList.put((Integer)obj[0], (String)obj[1]);
	 			  				}
	 			  				
	 			}catch(Exception e){
	 				logger.error("error in fetching the Service Category name .."+e);
	 				getServiceCategoryList=new HashMap<Integer,String>();
	 			}
	 			finally{
	 				try{			
	 					session.close();
	 					logger.info("session closed..fetching the Service Category name .");
	 				}catch(Exception e){
	 					logger.error(e.getMessage());
	 				}
	 			}
	 			
	 			return getServiceCategoryList;
	 			
	 		}

	 // this method is written for getting the status category list on 09-10-15
			
	 		public static HashMap<Integer,String> getStatusCategoryList(){
	 			HashMap<Integer,String> getStatusCategoryList=null;
	 			
	 			try{
	 				getStatusCategoryList=new HashMap<Integer,String>();
	 				
	 				 if (session == null || session.isOpen() == false) 
	 			  		{
	 			  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	 			  			} 
	 			  			
	 			  				session.getTransaction().begin();
	 				
	 			  				Criteria criteria = session.createCriteria(ReportStatusMasterPojo.class);
	 			  				criteria.add(Restrictions.eq("RECORD_STATUS",0));
	 			  				ProjectionList prj = Projections.projectionList();
	 			  				prj.add(Projections.property("STATUS_ID")).add(Projections.property("STATUS_DESCRIPTION"));
	 			  				criteria.setProjection(prj).addOrder(Order.asc("STATUS_DESCRIPTION"));		
	 			  				List<Object[]> ServiceList=(List<Object[]>)criteria.list();
	 			  				for(Object[] obj:ServiceList){
	 			  					getStatusCategoryList.put((Integer)obj[0], (String)obj[1]);
	 			  				}
	 			  				
	 			}catch(Exception e){
	 				logger.error("error in fetching the Service Category name .."+e);
	 				getStatusCategoryList=new HashMap<Integer,String>();
	 			}
	 			finally{
	 				try{			
	 					session.close();
	 					logger.info("session closed..fetching the Service Category name .");
	 				}catch(Exception e){
	 					logger.error(e.getMessage());
	 				}
	 			}
	 			
	 			return getStatusCategoryList;
	 			
	 		}
	 		
	 		
   // this method is written for getting the role list on 10-12-2015
			
	 		public static HashMap<Integer,String> getRoleList(){
	 			HashMap<Integer,String> getRoleList=null;
	 			
	 			try{
	 				getRoleList=new HashMap<Integer,String>();
	 				
	 				 if (session == null || session.isOpen() == false) 
	 			  		{
	 			  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	 			  			} 
	 			  			
	 			  				session.getTransaction().begin();
	 				
	 			  				Criteria criteria = session.createCriteria(MediRoleMasterPojo.class);
	 			  				criteria.add(Restrictions.eq("RECORD_STATUS",0));
	 			  				ProjectionList prj = Projections.projectionList();
	 			  				prj.add(Projections.property("ROLE_ID")).add(Projections.property("ROLE_NAME"));
	 			  				criteria.setProjection(prj).addOrder(Order.asc("ROLE_NAME"));		
	 			  				List<Object[]> ServiceList=(List<Object[]>)criteria.list();
	 			  				for(Object[] obj:ServiceList){
	 			  					getRoleList.put((Integer)obj[0], (String)obj[1]);
	 			  				}
	 			  				
	 			}catch(Exception e){
	 				logger.error("error in fetching role list .."+e);
	 				getRoleList=new HashMap<Integer,String>();
	 			}
	 			finally{
	 				try{			
	 					session.close();
	 					logger.info("session closed..fetching the role list. .");
	 				}catch(Exception e){
	 					logger.error(e.getMessage());
	 				}
	 			}
	 			
	 			return getRoleList;
	 			
	 		}
	 		
	 		
	 	   // this method is written for getting the menu group menu list on 10-12-2015
	 				
	 		 		public static HashMap<Integer,String> getMenuGroupList(){
	 		 			HashMap<Integer,String> getMenuGroupList=null;
	 		 			
	 		 			try{
	 		 				getMenuGroupList=new HashMap<Integer,String>();
	 		 				
	 		 				 if (session == null || session.isOpen() == false) 
	 		 			  		{
	 		 			  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	 		 			  			} 
	 		 			  			
	 		 			  				session.getTransaction().begin();
	 		 				
	 		 			  				Criteria criteria = session.createCriteria(MediMenuGroupMasterPojo.class);
	 		 			  				criteria.add(Restrictions.eq("RECORD_STATUS",0));
	 		 			  				ProjectionList prj = Projections.projectionList();
	 		 			  				prj.add(Projections.property("GROUP_ID")).add(Projections.property("GROUP_NAME"));
	 		 			  				criteria.setProjection(prj).addOrder(Order.asc("GROUP_NAME"));		
	 		 			  				List<Object[]> ServiceList=(List<Object[]>)criteria.list();
	 		 			  				for(Object[] obj:ServiceList){
	 		 			  				getMenuGroupList.put((Integer)obj[0], (String)obj[1]);
	 		 			  				}
	 		 			  				
	 		 			}catch(Exception e){
	 		 				logger.error("error in fetching group list .."+e);
	 		 				getMenuGroupList=new HashMap<Integer,String>();
	 		 			}
	 		 			finally{
	 		 				try{			
	 		 					session.close();
	 		 					logger.info("session closed..fetching the group list. .");
	 		 				}catch(Exception e){
	 		 					logger.error(e.getMessage());
	 		 				}
	 		 			}
	 		 			
	 		 			return getMenuGroupList;
	 		 			
	 		 		}
	 		 		
	 		 		
// this method is written for getting the  menu list on 10-12-2015
	 				
	 		 		public static HashMap<Integer,String> getMenuList(){
	 		 			HashMap<Integer,String> getMenuList=null;
	 		 			
	 		 			try{
	 		 				getMenuList=new HashMap<Integer,String>();
	 		 				
	 		 				 if (session == null || session.isOpen() == false) 
	 		 			  		{
	 		 			  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	 		 			  			} 
	 		 			  			
	 		 			  				session.getTransaction().begin();
	 		 				
	 		 			  				Criteria criteria = session.createCriteria(MediMenuMasterPojo.class);
	 		 			  				criteria.add(Restrictions.eq("RECORD_STATUS",0));
	 		 			  				ProjectionList prj = Projections.projectionList();
	 		 			  				prj.add(Projections.property("MENU_ID")).add(Projections.property("MENU_NAME"));
	 		 			  				criteria.setProjection(prj).addOrder(Order.asc("MENU_NAME"));		
	 		 			  				List<Object[]> ServiceList=(List<Object[]>)criteria.list();
	 		 			  				for(Object[] obj:ServiceList){
	 		 			  				getMenuList.put((Integer)obj[0], (String)obj[1]);
	 		 			  				}
	 		 			  				
	 		 			}catch(Exception e){
	 		 				logger.error("error in fetching menu list .."+e);
	 		 				getMenuList=new HashMap<Integer,String>();
	 		 			}
	 		 			finally{
	 		 				try{			
	 		 					session.close();
	 		 					logger.info("session closed..fetching the menu list. .");
	 		 				}catch(Exception e){
	 		 					logger.error(e.getMessage());
	 		 				}
	 		 			}
	 		 			
	 		 			return getMenuList;
	 		 			
	 		 		}
	 		 		
	 	//this is the method writen for the loading the dynamic menu and group menu as on 15-12-2015
	 		 		public static String getMenuAccordingToUser(final String username){
	 		 			String usermenu=null;
	 		 			
	 		 			
	 		 			
	 		 			 final List username_func= new ArrayList<String>();
	 		 				if (session == null || session.isOpen() == false) 
	 		 		  		{
	 		 		  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	 		 		  		}
	 		 			 
	 		 			 
	 		 			Work work=new Work() {
	 		 				
	 		 				@Override
	 		 				public void execute(Connection connection) throws SQLException {
	 		 					String fileCall="";
	 		 					if(username.startsWith("IN")){
	 		 						 fileCall = "{? = call GETMENUAGENCY(?)}";
	 		 						}
	 		 					// for date generation function
	 		 					else{
	 		 						 fileCall = "{? = call GETMENU(?)}";
	 		 					}
	 		 					
	 		 					CallableStatement stmt_1 = connection.prepareCall(fileCall);
	 		 					// change this parameter according to the country id 
	 		 					//java.sql.Date sqlDate = new java.sql.Date(country_code.getTime());
	 		 					
	 		 					stmt_1.setString(2,username);
	 		 					
	 		 					stmt_1.registerOutParameter(1, Types.VARCHAR);
	 		 					stmt_1.execute();
	 		 					//result_md5=stmt.getString(1);
	 		 					//System.out.println(result_md5);
	 		 					username_func.add((String)stmt_1.getString(1));
	 		 					
	 		 				}
	 		 			};
	 		 			
	 		 			session.doWork(work);
	 		 			usermenu =(String)username_func.get(0);
	 		 			
	 		 		return usermenu;
	 		 		}
	 		 		
	 		 		
	 		 		// get user menus 
	 		 		public static String getMenus(final String userid){
	 		 			String menuname=null;
	 		 			
	 		 			
	 		 			
	 		 			 final List menuname_func= new ArrayList<String>();
	 		 				if (session == null || session.isOpen() == false) 
	 		 		  		{
	 		 		  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	 		 		  		}
	 		 			 
	 		 			 
	 		 			Work work=new Work() {
	 		 				
	 		 				@Override
	 		 				public void execute(Connection connection) throws SQLException {
	 		 				
	 		 					
	 		 					// for date generation function
	 		 					String fileCall = "{? = call GETMENU(?)}";
	 		 					CallableStatement stmt_1 = connection.prepareCall(fileCall);
	 		 					// change this parameter according to the country id 
	 		 					//java.sql.Date sqlDate = new java.sql.Date(country_code.getTime());
	 		 					
	 		 					stmt_1.setString(2,userid);
	 		 					stmt_1.registerOutParameter(1, Types.VARCHAR);
	 		 					stmt_1.execute();
	 		 					//result_md5=stmt.getString(1);
	 		 					//System.out.println(result_md5);
	 		 					menuname_func.add((String)stmt_1.getString(1));
	 		 					
	 		 				}
	 		 			};
	 		 			
	 		 			session.doWork(work);
	 		 			menuname =(String)menuname_func.get(0);
	 		 			
	 		 		return menuname;
	 		 		}	
	 		 		
	 		 		
	 		 		
	 		 		//this method is written for the agency type list fetching
	 		 		
	 		 		
	 				
	 		 		public static HashMap<Integer,String> getAgencyTypeList(){
	 		 			HashMap<Integer,String> getAgencyTypeList=null;
	 		 			
	 		 			try{
	 		 				getAgencyTypeList=new HashMap<Integer,String>();
	 		 				
	 		 				 if (session == null || session.isOpen() == false) 
	 		 			  		{
	 		 			  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	 		 			  			} 
	 		 			  			
	 		 			  				session.getTransaction().begin();
	 		 				
	 		 			  				Criteria criteria = session.createCriteria(MediLabTypeMasterPojo.class);
	 		 			  				criteria.add(Restrictions.eq("RECORD_STATUS",0));
	 		 			  				ProjectionList prj = Projections.projectionList();
	 		 			  				prj.add(Projections.property("TYPE_ID")).add(Projections.property("TYPE_NAME"));
	 		 			  				criteria.setProjection(prj).addOrder(Order.asc("TYPE_NAME"));		
	 		 			  				List<Object[]> ServiceList=(List<Object[]>)criteria.list();
	 		 			  				for(Object[] obj:ServiceList){
	 		 			  				getAgencyTypeList.put((Integer)obj[0], (String)obj[1]);
	 		 			  				}
	 		 			  				
	 		 			}catch(Exception e){
	 		 				logger.error("error in fetching the Agency type list .."+e);
	 		 				getAgencyTypeList=new HashMap<Integer,String>();
	 		 			}
	 		 			finally{
	 		 				try{			
	 		 					session.close();
	 		 					logger.info("session closed..fetching the Agency type list .");
	 		 				}catch(Exception e){
	 		 					logger.error(e.getMessage());
	 		 				}
	 		 			}
	 		 			
	 		 			return getAgencyTypeList;
	 		 			
	 		 		}
	 		 		
	 		 		
	 		 		//this is the method written for getting the order status list
	 		 		
	 				public static HashMap<Integer,String> getOrderStatusList(){
	 		 			HashMap<Integer,String> getOrderStatusList=null;
	 		 			
	 		 			try{
	 		 				getOrderStatusList=new HashMap<Integer,String>();
	 		 				
	 		 				 if (session == null || session.isOpen() == false) 
	 		 			  		{
	 		 			  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	 		 			  			} 
	 		 			  			
	 		 			  				session.getTransaction().begin();
	 		 				
	 		 			  				Criteria criteria = session.createCriteria(ReportStatusMasterPojo.class);
	 		 			  				criteria.add(Restrictions.eq("RECORD_STATUS",0)).add(Restrictions.ne("STATUS_ID", 6));
	 		 			  				ProjectionList prj = Projections.projectionList();
	 		 			  				prj.add(Projections.property("STATUS_ID")).add(Projections.property("STATUS_DESCRIPTION"));
	 		 			  				criteria.setProjection(prj).addOrder(Order.asc("STATUS_ID"));		
	 		 			  				List<Object[]> ServiceList=(List<Object[]>)criteria.list();
	 		 			  				for(Object[] obj:ServiceList){
	 		 			  				getOrderStatusList.put((Integer)obj[0], (String)obj[1]);
	 		 			  				}
	 		 			  				
	 		 			}catch(Exception e){
	 		 				logger.error("error in fetching the Agency type list .."+e);
	 		 				getOrderStatusList=new HashMap<Integer,String>();
	 		 			}
	 		 			finally{
	 		 				try{			
	 		 					session.close();
	 		 					logger.info("session closed..fetching the Agency type list .");
	 		 				}catch(Exception e){
	 		 					logger.error(e.getMessage());
	 		 				}
	 		 			}
	 		 			
	 		 			return getOrderStatusList;
	 		 			
	 		 		}
	 		 		
	 		 		
	
	 				
	 		 		public static List<UserProfileUpdateBean> getUserProfileInfo(String username,Integer record_status){
	 		 			List<UserProfileUpdateBean> user_profile_info_list=null;
	 		 			
	 		 			
	 		 			try{
	 		 				user_profile_info_list=new ArrayList<UserProfileUpdateBean>();
	 		 				
	 		 				 if (session == null || session.isOpen() == false) 
	 		 			  		{
	 		 			  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	 		 			  			} 
	 		 			  			
	 		 			  				session.getTransaction().begin();
	 		 				
	 		 			  				Criteria criteria = session.createCriteria(UserRegistrationPojo.class);
	 		 			  			    criteria.add(Restrictions.eq("APP_FILE_NO",username));
	 		 			  				criteria.add(Restrictions.eq("RECORD_STATUS",record_status));	 		 			  			   
	 		 			  				ProjectionList prj = Projections.projectionList();
	 		 			  				prj.add(Projections.property("APP_ADDRESS_ONE")).add(Projections.property("APP_ADDRESS_TWO"))
	 		 			  				.add(Projections.property("APP_ADDRESS_THREE")).add(Projections.property("PINCODE")).add(Projections.property("APP_HEIGHT"))
	 		 			  				.add(Projections.property("APP_WEIGHT")).add(Projections.property("APP_BLOOD_GROUP"));
	 		 			  				criteria.setProjection(prj);	
	 		 			  				
	 		 			  			//List<UserRegistrationPojo> profileList=(List<UserRegistrationPojo>)criteria.list();
	 		 				 		 
	 		 			  			
	 		 			  			UserRegistrationPojo usr_registration_pojo = (UserRegistrationPojo) session.get(UserRegistrationPojo.class,username);
	 		 			  			UserProfileUpdateBean dbean= new UserProfileUpdateBean();
		 							dbean.setUser_address_one(usr_registration_pojo.getAPP_ADDRESS_ONE());
		 							dbean.setUser_address_two(usr_registration_pojo.getAPP_ADDRESS_TWO());
		 						    dbean.setUser_address_three(usr_registration_pojo.getAPP_ADDRESS_THREE());
		 						    dbean.setUser_blood_group(usr_registration_pojo.getAPP_BLOOD_GROUP());
		 						    dbean.setUser_height(usr_registration_pojo.getAPP_HEIGHT());
		 						    dbean.setUser_weight(usr_registration_pojo.getAPP_WEIGHT());
		 						    dbean.setUser_pincode(usr_registration_pojo.getPINCODE());
		 							user_profile_info_list.add(dbean);
	 		 			  
		 							//UserRegistrationPojo usr_registration_pojo = (UserRegistrationPojo) session.get(UserRegistrationPojo.class,username);
		 		 					
	 		 			  				
	 		 			  				
	 		 			  				
	 		 			  				
	 		 			  				
		 		 			  				/*List<UserRegistrationPojo> profileList=(List<UserRegistrationPojo>)criteria.list();
	 		 			  				Iterator<UserRegistrationPojo> iterator=profileList.iterator();
	 		 			  			   while(iterator.hasNext()){			
	 		 			  				UserRegistrationPojo user_profile_pojo= new UserRegistrationPojo();
	 		 							user_profile_pojo=(UserRegistrationPojo)iterator.next();
	 		 							UserProfileUpdateBean dbean= new UserProfileUpdateBean();	
	 		 							dbean.setUser_address_one(user_profile_pojo.getAPP_ADDRESS_ONE());
	 		 							dbean.setUser_address_two(user_profile_pojo.getAPP_ADDRESS_TWO());
	 		 						    dbean.setUser_address_three(user_profile_pojo.getAPP_ADDRESS_THREE());
	 		 						    dbean.setUser_blood_group(user_profile_pojo.getAPP_BLOOD_GROUP());
	 		 						    dbean.setUser_height(user_profile_pojo.getAPP_HEIGHT());
	 		 						    dbean.setUser_weight(user_profile_pojo.getAPP_WEIGHT());
	 		 						    dbean.setUser_pincode(user_profile_pojo.getPINCODE());
	 		 							user_profile_info_list.add(dbean);
	 		 						}
	 		 			  				*/
	 		 			}catch(Exception e){
	 		 				logger.error("error in fetching the user profile details .."+e);
	 		 				user_profile_info_list=new ArrayList<UserProfileUpdateBean>();
	 		 			}
	 		 			finally{
	 		 				try{			
	 		 					session.close();
	 		 					logger.info("session closed.. in fetching the user profile details.");
	 		 				}catch(Exception e){
	 		 					logger.error(e.getMessage());
	 		 					user_profile_info_list=new ArrayList<UserProfileUpdateBean>();
	 		 				}
	 		 			}
	 		 			
	 		 			
	 		 			return user_profile_info_list;
	 		 		}
	 		
	 		 		
	 		 		
	 		 		public static List<AgencyProfileUpdateBean> getAgencyProfileInfo(String agency_id,Integer record_status){
	 		 			List<AgencyProfileUpdateBean> agency_profile_info_list=null;
	 		 			
	 		 			
	 		 			try{
	 		 				agency_profile_info_list=new ArrayList<AgencyProfileUpdateBean>();
	 		 				
	 		 				 if (session == null || session.isOpen() == false) 
	 		 			  		{
	 		 			  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	 		 			  			} 
	 		 			  			
	 		 			  				session.getTransaction().begin();
	 		 				
	 		 			  			//	Criteria criteria = session.createCriteria(UserRegistrationPojo.class);
	 		 			  			   /* criteria.add(Restrictions.eq("APP_FILE_NO",agency_id));
	 		 			  				criteria.add(Restrictions.eq("RECORD_STATUS",record_status));	 		 			  			   
	 		 			  				ProjectionList prj = Projections.projectionList();
	 		 			  				prj.add(Projections.property("APP_ADDRESS_ONE")).add(Projections.property("APP_ADDRESS_TWO"))
	 		 			  				.add(Projections.property("APP_ADDRESS_THREE")).add(Projections.property("PINCODE")).add(Projections.property("APP_HEIGHT"))
	 		 			  				.add(Projections.property("APP_WEIGHT")).add(Projections.property("APP_BLOOD_GROUP"));
	 		 			  				criteria.setProjection(prj);	
	 		 			  				*/
	 		 			  			//List<UserRegistrationPojo> profileList=(List<UserRegistrationPojo>)criteria.list();
	 		 				 		 
	 		 			  			
	 		 			  			AgencyRegistrationPojo agency_registration_pojo = (AgencyRegistrationPojo) session.get(AgencyRegistrationPojo.class,agency_id);
	 		 			  			AgencyProfileUpdateBean dbean= new AgencyProfileUpdateBean();
	 		 			  		
	 		 			  			
	 		 			  			
	 		 			  			
	 		 			  	 dbean.setLab_registered_name(agency_registration_pojo.getAGENCY_REGISTERED_NAME());
	 		 			     dbean.setLab_registered_no(agency_registration_pojo.getAGENCY_REGISTERED_NO());
	 		 			  	 dbean.setLab_type_id(agency_registration_pojo.getAGENCY_TYPE_ID());
	 		 			  	 dbean.setLab_email(agency_registration_pojo.getAGENCY_EMAIL());
	 		 			  	 dbean.setLab_mobile(agency_registration_pojo.getAGENCY_TELEPHONE());
	 		 			  	 dbean.setLab_fax(agency_registration_pojo.getAGENCY_FAX());
	 		 			  	 dbean.setLab_website(agency_registration_pojo.getAGENCY_WEBSITE());
	 		 			  	 dbean.setLab_address_one(agency_registration_pojo.getAGENCY_ADDRESS_ONE());
	 		 			  	 dbean.setLab_address_two(agency_registration_pojo.getAGENCY_ADDRESS_TWO());
	 		 			  	 dbean.setLab_address_three(agency_registration_pojo.getAGENCY_ADDRESS_THREE());
	 		 			  	 dbean.setLab_remark_one(agency_registration_pojo.getAGENCY_REMARK_ONE());
	 		 			  	 dbean.setLab_remark_two(agency_registration_pojo.getAGENCY_REMARK_TWO());
	 		 			  	 dbean.setLab_remark_three(agency_registration_pojo.getAGENCY_REMARK_THREE());
	 		 			  	 dbean.setLab_summer_opening_time(agency_registration_pojo.getAGENCY_SUMMER_TIME());
	 		 			  	 dbean.setLab_winter_opening_time(agency_registration_pojo.getAGENCY_WINTER_TIME());;
	 		 			     dbean.setLab_expiry_date(agency_registration_pojo.getEXPIRY_DATE());
	 		 			  	 dbean.setLab_activation_date(agency_registration_pojo.getACTIVATION_DATE());
	 		 			     dbean.setLab_pincode(agency_registration_pojo.getAGENCY_ADDRESS_PINCODE().toString());
	 		 			  	 dbean.setLab_cmo_name(agency_registration_pojo.getAGENCY_CMO_NAME());
	 		 			  	 dbean.setBank_account_no(agency_registration_pojo.getACOOUNT_NO());	
	 		 			  	 dbean.setBank_branch_code(agency_registration_pojo.getBANK_BRANCH_CODE());
	 		 			  	 dbean.setBank_branch_micr_code(agency_registration_pojo.getBANK_MICR_CODE());
	 		 			  	 dbean.setBank_branch_name(agency_registration_pojo.getBANK_BRANCH_NAME());
	 		 			  	 dbean.setBank_ifsc_code(agency_registration_pojo.getIFSC_CODE());
	 		 			  	 dbean.setBank_name(agency_registration_pojo.getBANK_NAME());
	 		 			  	 dbean.setMerchant_id(agency_registration_pojo.getMERCHANT_ID());
	 		 			  	 dbean.setLab_owner_email(agency_registration_pojo.getAGENCY_OWNER_EMAIL());
	 		 			  	 dbean.setLab_owner_gender(agency_registration_pojo.getAGENCY_OWNER_GENDER_ID());
	 		 			  	 dbean.setLab_owner_mobile_no(agency_registration_pojo.getAGENCY_OWNER_MOBILE_NO());
	 		 			  	 dbean.setLab_owner_name(agency_registration_pojo.getAGENCY_OWNER_FIRST_NAME());
	 		 			  	 dbean.setLab_owner_surname(agency_registration_pojo.getAGENCY_OWNER_SUR_NAME());
	 		 			  	
	 		 			  	 
		 							agency_profile_info_list.add(dbean);
	 		 			  
	 		 			}catch(Exception e){
	 		 				logger.error("error in fetching the user profile details .."+e);
	 		 				agency_profile_info_list=new ArrayList<AgencyProfileUpdateBean>();
	 		 			}
	 		 			finally{
	 		 				try{			
	 		 					session.close();
	 		 					logger.info("session closed.. in fetching the user profile details.");
	 		 				}catch(Exception e){
	 		 					logger.error(e.getMessage());
	 		 					agency_profile_info_list=new ArrayList<AgencyProfileUpdateBean>();
	 		 				}
	 		 			}
	 		 			
	 		 			
	 		 			return agency_profile_info_list;
	 		 		}
	 		 		
	 		 		
	 		 		
	 		 		
	 		 		
	 		 		
	 		 		
	 		 		//fetching the user pincode status
	 		 		
	 		 		
	 		 		
	 		 		public static  boolean getUserPincodeStatus(String username){
	 		 			boolean saveflag=false;
	 		 		   	 if(username!=null)
	 		 				{
	 		 				try{
	 		 					
	 		 					
	 		 					if (session == null || session.isOpen() == false) 
	 		 					{
	 		 					
	 		 								session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	 		 					}
	 		 						
	 		 					session.getTransaction().begin();

	 		 					
	 		 					String created_by=(String)ServletActionContext.getContext().getSession().get("username");
	 		 					if(created_by==null){
	 		 						
	 		 						created_by="ADMIN";
	 		 						
	 		 					}
	 		 					
	 		 					//session.getTransaction().begin();
	 		 					//UserRegistrationPojo usr_registration_pojo = (UserRegistrationPojo) session.get(UserRegistrationPojo.class,username);
	 		 					
	 		 					
	 		 					UserRegistrationPojo usr_registration_pojo = (UserRegistrationPojo) session.get(UserRegistrationPojo.class,username);
	 		 					
	 		 					
	 		 					//logger.info(usr_registration_pojo.getPINCODE());
	 		 					
	 		 					String pincode=usr_registration_pojo.getPINCODE();
	 		 					
	 		 					if(pincode==null||pincode.length()<6||pincode.isEmpty())
	 		 					{
	 		 						
	 		 						saveflag=false;	
	 		 					}    					
	 		 					else{
	 		 						
	 		 					    saveflag=true;
	 		 					}
	 		 					
	 		 					/*
	 		 					criteria = session.createCriteria(UserRegistrationPojo.class);
	 		 					criteria.add(Restrictions.eq("APP_FILE_NO", username));
	 		 					//criteria.setCacheable(true);	
	 		 					ProjectionList prj=Projections.projectionList();
	 		 					prj.add(Projections.property("PINCODE"));
	 		 					
	 		 					criteria.setProjection(prj);
	 		 					user_data_list = criteria.list();
	 		 					iterator = user_data_list.iterator();
	 		 					if (iterator.hasNext()) {
	 		 						UserRegistrationPojo	usr_registration_pojo = new UserRegistrationPojo();
	 		 						usr_registration_pojo = (UserRegistrationPojo) iterator.next();
	 		 						
	 		 						logger.info(usr_registration_pojo.getPINCODE());
	 		 						String pincode=usr_registration_pojo.getPINCODE();
	 		 						
	 		 						if(pincode==null||pincode.length()<6||pincode.isEmpty())
	 		 						{
	 		 							
	 		 							saveflag="false";	
	 		 						}    					
	 		 						else{
	 		 						saveflag="true";
	 		 						}
	 		 					}*/
	 		 					
	 		 				}catch(Exception e)
	 		 				{
	 		 					session.getTransaction().rollback();
	 		 					saveflag=false;
	 		 					logger.error("error:-"+e.getMessage());
	 		 					
	 		 					
	 		 				}
	 		 				finally{
	 		 					try{
	 		 						session.close();
	 		 						logger.info("session closed..");
	 		 					}catch(Exception e){
	 		 						logger.error("error in closing the session..");
	 		 						logger.error(e.getMessage());
	 		 					}
	 		 				}
	 		 				
	 		 				return saveflag;
	 		 			}
	 		 				else{
	 		 					saveflag=false;
	 		 				return	saveflag;
	 		 				}
	 		 			}
	 		 			
	 		 		
	 		 		public static  boolean getAgencyPincodeStatus(String agency_id){
	 		 			boolean saveflag=false;
	 		 		   	 if(agency_id!=null)
	 		 				{
	 		 				try{
	 		 					
	 		 					
	 		 					if (session == null || session.isOpen() == false) 
	 		 					{
	 		 					
	 		 								session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	 		 					}
	 		 						
	 		 					session.getTransaction().begin();

	 		 					
	 		 					String created_by=(String)ServletActionContext.getContext().getSession().get("loginname");
	 		 					if(created_by==null){
	 		 						
	 		 						created_by="ADMIN";
	 		 						
	 		 					}
	 		 					
	 		 					//session.getTransaction().begin();
	 		 					//UserRegistrationPojo usr_registration_pojo = (UserRegistrationPojo) session.get(UserRegistrationPojo.class,username);
	 		 					
	 		 					
	 		 					AgencyRegistrationPojo agency_registration_pojo = (AgencyRegistrationPojo) session.get(AgencyRegistrationPojo.class,agency_id);
	 		 					
	 		 					
	 		 					//logger.info(usr_registration_pojo.getPINCODE());
	 		 					
	 		 					String pincode=agency_registration_pojo.getAGENCY_ADDRESS_PINCODE().toString();
	 		 					
	 		 					if(pincode==null||pincode.length()<6||pincode.isEmpty())
	 		 					{
	 		 						
	 		 						saveflag=false;	
	 		 					}    					
	 		 					else{
	 		 						
	 		 					    saveflag=true;
	 		 					}
	 		 					
	 		 					/*
	 		 					criteria = session.createCriteria(UserRegistrationPojo.class);
	 		 					criteria.add(Restrictions.eq("APP_FILE_NO", username));
	 		 					//criteria.setCacheable(true);	
	 		 					ProjectionList prj=Projections.projectionList();
	 		 					prj.add(Projections.property("PINCODE"));
	 		 					
	 		 					criteria.setProjection(prj);
	 		 					user_data_list = criteria.list();
	 		 					iterator = user_data_list.iterator();
	 		 					if (iterator.hasNext()) {
	 		 						UserRegistrationPojo	usr_registration_pojo = new UserRegistrationPojo();
	 		 						usr_registration_pojo = (UserRegistrationPojo) iterator.next();
	 		 						
	 		 						logger.info(usr_registration_pojo.getPINCODE());
	 		 						String pincode=usr_registration_pojo.getPINCODE();
	 		 						
	 		 						if(pincode==null||pincode.length()<6||pincode.isEmpty())
	 		 						{
	 		 							
	 		 							saveflag="false";	
	 		 						}    					
	 		 						else{
	 		 						saveflag="true";
	 		 						}
	 		 					}*/
	 		 					
	 		 				}catch(Exception e)
	 		 				{
	 		 					session.getTransaction().rollback();
	 		 					saveflag=false;
	 		 					logger.error("error:-"+e.getMessage());
	 		 					
	 		 					
	 		 				}
	 		 				finally{
	 		 					try{
	 		 						session.close();
	 		 						logger.info("session closed..");
	 		 					}catch(Exception e){
	 		 						logger.error("error in closing the session..");
	 		 						logger.error(e.getMessage());
	 		 					}
	 		 				}
	 		 				
	 		 				return saveflag;
	 		 			}
	 		 				else{
	 		 					saveflag=false;
	 		 				return	saveflag;
	 		 				}
	 		 			}
	 		 			
	 		 				
	 		 		
	 		 		// checking the status of the report upload as on 16/08/2016
	 		 		
	 		 		
	 				
	 		 		public static  boolean getUserReportStatus(String order_id){
	 		 			boolean saveflag=false;
	 		 		   	 if(order_id!=null)
	 		 				{
	 		 				try{
	 		 					
	 		 					
	 		 					if (session == null || session.isOpen() == false) 
	 		 					{
	 		 					
	 		 								session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	 		 					}
	 		 						
	 		 					session.getTransaction().begin();
	 		 					
	 		 					MediUserFileDownloadMasterPojo usr_file_master_pojo = new MediUserFileDownloadMasterPojo();
	 		 				  Criteria criteria = session.createCriteria(MediUserFileDownloadMasterPojo.class);
	 		 				  criteria.add(Restrictions.eq("ORDER_ID",order_id));
	 		 				  usr_file_master_pojo= (MediUserFileDownloadMasterPojo)criteria.uniqueResult();
	 		 					
	 		 					

	 		 					String order=usr_file_master_pojo.getORDER_ID();
	 		 					
	 		 					if(order==null||order.length()<6||order.isEmpty())
	 		 					{
	 		 						
	 		 						saveflag=false;	
	 		 					}    					
	 		 					else{
	 		 						
	 		 					    saveflag=true;
	 		 					}
	 		 					
		 		 				}catch(Exception e)
	 		 				{
	 		 					session.getTransaction().rollback();
	 		 					saveflag=false;
	 		 					logger.error("error:-"+e.getMessage());
	 		 					
	 		 					
	 		 				}
	 		 				finally{
	 		 					try{
	 		 						session.close();
	 		 						logger.info("session closed..");
	 		 					}catch(Exception e){
	 		 						logger.error("error in closing the session..");
	 		 						logger.error(e.getMessage());
	 		 					}
	 		 				}
	 		 				
	 		 				return saveflag;
	 		 			}
	 		 				else{
	 		 					saveflag=false;
	 		 				return	saveflag;
	 		 				}
	 		 			}
	 		 		
	 		 		
	 		 		
	 		 		// checking the status of the user registration using mobile
	 		 		
	 		 		

	 				
	 		 		public static  boolean getUserRegistrationStatusMobile(String mobile_no){
	 		 			boolean saveflag=false;
	 		 		   	 if(mobile_no!=null)
	 		 				{
	 		 				try{
	 		 					
	 		 					
	 		 					if (session == null || session.isOpen() == false) 
	 		 					{
	 		 					
	 		 								session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	 		 					}
	 		 						
	 		 					session.getTransaction().begin();
	 		 					
	 		 					UserRegistrationPojo usr_reg_master_pojo = new UserRegistrationPojo();
	 		 				  Criteria criteria = session.createCriteria(UserRegistrationPojo.class);
	 		 				  criteria.add(Restrictions.eq("APP_MOBILE_NO",mobile_no));
	 		 				  usr_reg_master_pojo= (UserRegistrationPojo)criteria.uniqueResult();
	 		 				    
	 		 				   String app_file_no=usr_reg_master_pojo.getAPP_FILE_NO();
	 		 				
	 		 				
	 		 					
	 		 					if(app_file_no==null||app_file_no.length()<6||app_file_no.isEmpty())
	 		 					{
	 		 						
	 		 						saveflag=false;	
	 		 					}    					
	 		 					else{
	 		 						
	 		 						ServletActionContext.getRequest().getSession().setAttribute("reg_file_no", app_file_no);
	 		 						
	 		 					    saveflag=true;
	 		 					}
	 		 					
		 		 				}catch(Exception e)
	 		 				{
	 		 					session.getTransaction().rollback();
	 		 					saveflag=false;
	 		 					logger.error("error:-"+e.getMessage());
	 		 					
	 		 					
	 		 				}
	 		 				finally{
	 		 					try{
	 		 						session.close();
	 		 						logger.info("session closed..");
	 		 					}catch(Exception e){
	 		 						logger.error("error in closing the session..");
	 		 						logger.error(e.getMessage());
	 		 					}
	 		 				}
	 		 				
	 		 				return saveflag;
	 		 			}
	 		 				else{
	 		 					saveflag=false;
	 		 				return	saveflag;
	 		 				}
	 		 			}
	 		 		
	 		 		

	 		 		// checking the status of the user registration using email
	 		 		
	 		 		

	 				
	 		 		public static  boolean getUserRegistrationStatusEmail(String app_email){
	 		 			boolean saveflag=false;
	 		 		   	 if(app_email!=null)
	 		 				{
	 		 				try{
	 		 					
	 		 					
	 		 					if (session == null || session.isOpen() == false) 
	 		 					{
	 		 					
	 		 								session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	 		 					}
	 		 						
	 		 					session.getTransaction().begin();
	 		 					
	 		 					UserRegistrationPojo usr_reg_master_pojo = new UserRegistrationPojo();
	 		 				  Criteria criteria = session.createCriteria(UserRegistrationPojo.class);
	 		 				  criteria.add(Restrictions.eq("APP_EMAIL",app_email));
	 		 				  usr_reg_master_pojo= (UserRegistrationPojo)criteria.uniqueResult();
	 		 				    
	 		 				   String app_file_no_using_email=usr_reg_master_pojo.getAPP_FILE_NO();
	 		 				
	 		 				
	 		 					
	 		 					if(app_file_no_using_email==null||app_file_no_using_email.length()<6||app_file_no_using_email.isEmpty())
	 		 					{
	 		 						
	 		 						saveflag=false;	
	 		 					}    					
	 		 					else{
	 		 						ServletActionContext.getRequest().getSession().setAttribute("reg_file_no", app_file_no_using_email);	
	 		 					    saveflag=true;
	 		 					}
	 		 					
		 		 				}catch(Exception e)
	 		 				{
	 		 					session.getTransaction().rollback();
	 		 					saveflag=false;
	 		 					logger.error("error:-"+e.getMessage());
	 		 					
	 		 					
	 		 				}
	 		 				finally{
	 		 					try{
	 		 						session.close();
	 		 						logger.info("session closed..");
	 		 					}catch(Exception e){
	 		 						logger.error("error in closing the session..");
	 		 						logger.error(e.getMessage());
	 		 					}
	 		 				}
	 		 				
	 		 				return saveflag;
	 		 			}
	 		 				else{
	 		 					saveflag=false;
	 		 				return	saveflag;
	 		 				}
	 		 			}
		 		
	 		 		
	 		 		
	 		 		public static  boolean getUserRegistrationStatusEmailAndMobile(String mobile,String app_email){
	 		 			boolean saveflag=false;
	 		 		   	 if(app_email!=null)
	 		 				{
	 		 				try{
	 		 					
	 		 					
	 		 					if (session == null || session.isOpen() == false) 
	 		 					{
	 		 					
	 		 								session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
	 		 					}
	 		 						
	 		 					session.getTransaction().begin();
	 		 					
	 		 					UserRegistrationPojo usr_reg_master_pojo = new UserRegistrationPojo();
	 		 				  Criteria criteria = session.createCriteria(UserRegistrationPojo.class);
	 		 				  criteria.add(Restrictions.disjunction().add(Restrictions.eq("APP_EMAIL", app_email)).add(Restrictions.eq("APP_MOBILE_NO", mobile)));
	 		 				  usr_reg_master_pojo= (UserRegistrationPojo)criteria.uniqueResult();
	 		 				    
	 		 				   String app_file_no_using_email=usr_reg_master_pojo.getAPP_FILE_NO();
	 		 				
	 		 				
	 		 					
	 		 					if(app_file_no_using_email==null||app_file_no_using_email.length()<6||app_file_no_using_email.isEmpty())
	 		 					{
	 		 						
	 		 						saveflag=false;	
	 		 					}    					
	 		 					else{
	 		 						ServletActionContext.getRequest().getSession().setAttribute("reg_file_no", app_file_no_using_email);
	 		 					    saveflag=true;
	 		 					}
	 		 					
		 		 				}catch(Exception e)
	 		 				{
	 		 					session.getTransaction().rollback();
	 		 					saveflag=false;
	 		 					logger.error("error:-"+e.getMessage());
	 		 					
	 		 					
	 		 				}
	 		 				finally{
	 		 					try{
	 		 						session.close();
	 		 						logger.info("session closed..");
	 		 					}catch(Exception e){
	 		 						logger.error("error in closing the session..");
	 		 						logger.error(e.getMessage());
	 		 					}
	 		 				}
	 		 				
	 		 				return saveflag;
	 		 			}
	 		 				else{
	 		 					saveflag=false;
	 		 				return	saveflag;
	 		 				}
	 		 			}
		 		
	 		 		
	 		 		// fetchSampleData according to agency written as on 15/10/2016
	 		 		
	 				// this method is written for getting the state list on 27-07-15
	 				
					public static HashMap<Integer,String> fetchSampleDataLab(String lab_id){
						HashMap<Integer,String> getSampleList=null;
						
						try{
							getSampleList=new HashMap<Integer,String>();
							
							 if (session == null || session.isOpen() == false) 
						  		{
						  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
						  			} 
						  			
						  				session.getTransaction().begin();
							
						  				Criteria criteria = session.createCriteria(MediSampleMasterPojo.class);
						  				criteria.add(Restrictions.eq("RECORD_STATUS",0)).add(Restrictions.eq("AGENCY_ID", lab_id));
						  				ProjectionList prj = Projections.projectionList();
						  				prj.add(Projections.property("SAMPLE_ID")).add(Projections.property("SAMPLE_NAME"));
						  				criteria.setProjection(prj).addOrder(Order.asc("SAMPLE_NAME"));		
						  				List<Object[]> MsnList=(List<Object[]>)criteria.list();
						  				for(Object[] obj:MsnList){
						  					getSampleList.put((Integer)obj[0], (String)obj[1]);
						  				}
						  				
						}catch(Exception e){
							logger.error("error in fetching the sample name agency wise .."+e);
							getSampleList=new HashMap<Integer,String>();
						}
						finally{
							try{			
								session.close();
								logger.info("session closed.. fetching the sample name agency wise .");
							}catch(Exception e){
								logger.error(e.getMessage());
							}
						}
						
						return getSampleList;
						
					}
	 		 		
	 		 		
	 		 		
	 		// this is the method written for fetching the report types for the specific user
					
					
					
	 				
						public static HashMap<Integer,String> fetchReportData(Integer report_type_id){
							HashMap<Integer,String> getSampleList=null;
							
							try{
								getSampleList=new HashMap<Integer,String>();
								
								 if (session == null || session.isOpen() == false) 
							  		{
							  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
							  			} 
							  			
							  				session.getTransaction().begin();
								
							  				Criteria criteria = session.createCriteria(MediUserReportsMasterPojo.class);
							  				criteria.add(Restrictions.eq("RECORD_STATUS",0)).add(Restrictions.eq("REPORT_TYPE", report_type_id));
							  				ProjectionList prj = Projections.projectionList();
							  				prj.add(Projections.property("REPORT_ID")).add(Projections.property("REPORT_NAME"));
							  				criteria.setProjection(prj).addOrder(Order.asc("REPORT_NAME"));		
							  				List<Object[]> MsnList=(List<Object[]>)criteria.list();
							  				for(Object[] obj:MsnList){
							  					getSampleList.put((Integer)obj[0], (String)obj[1]);
							  				}
							  				
							}catch(Exception e){
								logger.error("error in fetching the report name  .."+e);
								getSampleList=new HashMap<Integer,String>();
							}
							finally{
								try{			
									session.close();
									logger.info("session closed.. fetching the report name .");
								}catch(Exception e){
									logger.error(e.getMessage());
								}
							}
							
							return getSampleList;
							
						}
		 		 
// this is the method writen for the fetching the payment modes as on 10-11-2016
	
		 		
					
					
					
	 				
						public static HashMap<Integer,String> fetchPaymentModes(){
							HashMap<Integer,String> getPaymentModesList=null;
							
							try{
								getPaymentModesList=new HashMap<Integer,String>();
								
								 if (session == null || session.isOpen() == false) 
							  		{
							  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
							  			} 
							  			
							  				session.getTransaction().begin();
								
							  				Criteria criteria = session.createCriteria(AddPaymentModeMasterPojo.class);
							  				criteria.add(Restrictions.eq("RECORD_STATUS",0));
							  				ProjectionList prj = Projections.projectionList();
							  				prj.add(Projections.property("PAYMENT_MODE_ID")).add(Projections.property("PAYMENT_MODE_NAME"));
							  				criteria.setProjection(prj).addOrder(Order.asc("PAYMENT_MODE_NAME"));		
							  				List<Object[]> MsnList=(List<Object[]>)criteria.list();
							  				for(Object[] obj:MsnList){
							  					getSampleList.put((Integer)obj[0], (String)obj[1]);
							  				}
							  				
							}catch(Exception e){
								logger.error("error in fetching the report name  .."+e);
								getPaymentModesList=new HashMap<Integer,String>();
							}
							finally{
								try{			
									session.close();
									logger.info("session closed.. fetching the payment mode name .");
								}catch(Exception e){
									logger.error(e.getMessage());
								}
							}
							
							return getPaymentModesList;
							
						}
	
	// this is the method written for the sms and email utility
	// here 0 refers to the unsuccessfull mail sending 
	//1 means the successfull mail sending
	public static int sendEmailToUser(String message,String subject,String message_to,String message_from){
	 final String FROM = "admin@www.medisysonline.COM";   // Replace with your "From" address. This address must be verified.
         final String TO = "aims.ravi1609@gmail.com";  // Replace with a "To" address. If your account is still in the 
                                                       // sandbox, this address must be verified.
    
     final String BODY = "This email was sent through the Amazon SES SMTP interface by using Java.";
     final String SUBJECT = "Amazon SES test (SMTP interface accessed using Java)";
    
    // Supply your SMTP credentials below. Note that your SMTP credentials are different from your AWS credentials.
     final String SMTP_USERNAME = "AKIAIOXBOFNII4GIGNWA";  // Replace with your SMTP username.
     final String SMTP_PASSWORD = "AtuH4vzZb7frUwqD830q8ziLazkTEYknanr495uVLCVl";  // Replace with your SMTP password.
    
    // Amazon SES SMTP host name. This example uses the US West (Oregon) region.
     final String HOST = "email-smtp.us-east-1.amazonaws.com";    
    
    // The port you will connect to on the Amazon SES SMTP endpoint. We are choosing port 25 because we will use
    // STARTTLS to encrypt the connection.
     final int PORT = 25;
	int sending_flag=0;
	
		// starting mail sending 
		
		    // Create a Properties object to contain connection configuration information.
    	Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtps");
    	props.put("mail.smtp.port", PORT); 
    	
    	// Set properties indicating that we want to use STARTTLS to encrypt the connection.
    	// The SMTP session will begin on an unencrypted connection, and then the client
        // will issue a STARTTLS command to upgrade to an encrypted connection.
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.starttls.required", "true");

        // Create a Session object to represent a mail session with the specified properties. 
    	Session session = Session.getDefaultInstance(props);

        // Create a message with the specified information. 
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject(SUBJECT);
        msg.setContent(BODY,"text/plain");
            
        // Create a transport.        
        Transport transport = session.getTransport();
                    
        // Send the message.
        try
        {
            System.out.println("Attempting to send an email through the Amazon SES SMTP interface...");
            
            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
        	
            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
            sending_flag=1;
		System.out.println("Email sent!");
		
        }
        catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
		sending_flag=0;
        }
        finally
        {
            // Close and terminate the connection.
            transport.close();        	
        }		
	}
	
	return sending_flag;
	
	
	
}
