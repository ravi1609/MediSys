package com.medisys.ravi.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.medisys.ravi.beans.MediAddUserReportsBean;
import com.medisys.ravi.lab.pojo.MediAgencyTaxMasterPojo;
import com.medisys.ravi.pojo.MediUserReportsMasterPojo;
import com.medisys.ravi.utility.HibernateSessionFactoryProvider;
import com.medisys.ravi.utility.IpAddress;

public class MediAddUserReportsDao {


	// logger initialization
	
	
		private static final Logger logger = Logger.getLogger(MediAddUserReportsDao.class);
		
		
		// fields
		
		private static Session session;
		private static Transaction tr;
		
		
		
		// methods 
		
		public static Integer getMaxTableId(){
			Integer a=0;
			try{
				
				if (session == null || session.isOpen() == false) 
		  		{
		  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
		  		}
		  		session.getTransaction().begin();
				
				Criteria criteria=session.createCriteria(MediUserReportsMasterPojo.class);
				ProjectionList prj=Projections.projectionList();
				prj.add(Projections.max("REPORT_ID"));
				criteria.setProjection(prj);			
		  		List max=criteria.list();
		  		
		  		if(max!=null && max.size()>0){
					a=(Integer)max.get(0);
					}
					if(a==null)
						a=0;
					
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
		
		
		// getting the state list for display
		
		
		public static List<MediAddUserReportsBean> getDataForDisplay(){
			List<MediAddUserReportsBean> displist=null;
			
			try{
				displist= new ArrayList<MediAddUserReportsBean>();
				if (session == null || session.isOpen() == false) 
		  		{
		  			session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
		  		}
		  		session.getTransaction().begin();
				
				Criteria criteria=session.createCriteria(MediUserReportsMasterPojo.class);
				String created_by=(String)ServletActionContext.getContext().getSession().get("loginname");
				
				criteria.addOrder(Order.asc("REPORT_ID"));
				List<MediUserReportsMasterPojo> add_user_reports_names_list=(List<MediUserReportsMasterPojo>)criteria.list();
			    Iterator<MediUserReportsMasterPojo>	iterator=add_user_reports_names_list.iterator();
				while(iterator.hasNext()){			
					MediUserReportsMasterPojo add_user_reports_pojo= new MediUserReportsMasterPojo();
					add_user_reports_pojo=(MediUserReportsMasterPojo)iterator.next();
					MediAddUserReportsBean dbean= new MediAddUserReportsBean();
					
					dbean.setCreated_by(add_user_reports_pojo.getCREATED_BY());
					dbean.setCreated_date(add_user_reports_pojo.getCREATED_DATE());
					dbean.setReport_desc(add_user_reports_pojo.getREPORT_DESCRIPTION());
					dbean.setReport_id(add_user_reports_pojo.getREPORT_ID());
					dbean.setReport_name(add_user_reports_pojo.getREPORT_NAME());
					dbean.setReport_type_id(add_user_reports_pojo.getREPORT_TYPE());
					dbean.setReport_xml_name(add_user_reports_pojo.getREPORT_XML_NAME());
					
					
					
					dbean.setRcrd_status(add_user_reports_pojo.getRECORD_STATUS());
					if(add_user_reports_pojo.getRECORD_STATUS().equals(0))
					    dbean.setStatus("ENABLE");
						else
						dbean.setStatus("DISABLE");
					
					displist.add(dbean);
				}
				
			}catch(Exception e){
				
				logger.error("error in fetching the display list"+e.getMessage());
				
			}
			finally{
				try{ 
				
					session.close();
					logger.info("session closed..");
				}catch(Exception e){
					logger.error(e.getMessage());
				}
			}
			
			
			return displist;
		}
		
		
		
		
		
		
		
		public static String AddOrUpdateReport(MediAddUserReportsBean add_user_report_bean){
			 String update_msg = null;
				
				try{
						
						
						if (session == null || session.isOpen() == false) 
						{
						
									session = HibernateSessionFactoryProvider.getSessionFactory().openSession();
							}
							
							String created_by=(String)ServletActionContext.getContext().getSession().get("loginname");
							if(created_by==null){
								
								created_by="ADMIN";
								
							}
							//session.getTransaction().begin();
							 
						Criteria	criteria = session.createCriteria(MediUserReportsMasterPojo.class);
						
							if(add_user_report_bean.getUflag().equals(0)){
								//save
								session.getTransaction().begin();
								logger.info("transaction started saving data..");
							
								MediUserReportsMasterPojo add_user_report_pojo= new MediUserReportsMasterPojo();
								
								
								add_user_report_pojo.setCREATED_BY(created_by);
								add_user_report_pojo.setCREATED_DATE(new Date());
								add_user_report_pojo.setCREATED_ON(IpAddress.getIp());
								add_user_report_pojo.setCREATED_TIME(new Date());
								add_user_report_pojo.setRECORD_STATUS(add_user_report_bean.getRcrd_status());
								add_user_report_pojo.setREPORT_DESCRIPTION(add_user_report_bean.getReport_desc());
								add_user_report_pojo.setREPORT_ID(add_user_report_bean.getReport_id());
								add_user_report_pojo.setREPORT_NAME(add_user_report_bean.getReport_name());
								add_user_report_pojo.setREPORT_TYPE(add_user_report_bean.getReport_type_id());
								add_user_report_pojo.setREPORT_XML_NAME(add_user_report_bean.getReport_xml_name());
								
								
						 
								session.save(add_user_report_pojo);
								session.getTransaction().commit();
								update_msg="saved";
								logger.info("transaction commited...data saved successfully..");
								
							}
							
							
							
							
							
							
							
							if (add_user_report_bean.getUflag().equals(1)){
								//update
			               	session.getTransaction().begin();
						    logger.info("transaction started updating data..");
			               	
			               	criteria.add(Restrictions.eq("REPORT_ID",add_user_report_bean.getReport_id()));
			               	
			               	MediUserReportsMasterPojo add_user_report_pojo= new MediUserReportsMasterPojo();
			               	
			               	add_user_report_pojo= (MediUserReportsMasterPojo)criteria.uniqueResult();
			               	
			               	add_user_report_pojo.setCREATED_BY(created_by);
							add_user_report_pojo.setCREATED_DATE(new Date());
							add_user_report_pojo.setCREATED_ON(IpAddress.getIp());
							add_user_report_pojo.setCREATED_TIME(new Date());
							add_user_report_pojo.setRECORD_STATUS(add_user_report_bean.getRcrd_status());
							add_user_report_pojo.setREPORT_DESCRIPTION(add_user_report_bean.getReport_desc());
						
							add_user_report_pojo.setREPORT_NAME(add_user_report_bean.getReport_name());
							add_user_report_pojo.setREPORT_TYPE(add_user_report_bean.getReport_type_id());
							add_user_report_pojo.setREPORT_XML_NAME(add_user_report_bean.getReport_xml_name());
						


			               	
							 
			   		    
			   		    session.update(add_user_report_pojo);
			   		    session.getTransaction().commit();
			   		    update_msg="updated";
							logger.info("transaction commited...data updation successfull..");
			               }
							
			           /*    if(state_bean.getOption_list().equals(2)){
								//delete
			               	  session.getTransaction().begin();
								logger.info("transaction started deleting data..");
								MediStateMasterPojo state_pojo=	(MediStateMasterPojo)session.get(MediStateMasterPojo.class, state_bean.getSrno());
								
								state_pojo.setDELETE_FLAG(1);
								
								 session.update(state_pojo);
								 session.getTransaction().commit();
								 update_msg="deleted";
								 logger.info("transaction commited...data deletion successfull..");
							}*/
							
							
					}catch(Exception e)
					{
						session.getTransaction().rollback();
						logger.error("error:-"+e.getMessage());
						update_msg="error";
						}
					finally{
						try{
						
							session.close();
							logger.info("session closed..");
						}catch(Exception e){
							update_msg="error";
							logger.error(e.getMessage());
						}
					}
					
					
				
				return update_msg;
				
				
				
		}
		
		
	
	
	
	
	
}
