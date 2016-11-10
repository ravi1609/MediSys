package com.medisys.ravi.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;

@Entity
@Table(name="MEDI_REPORT_MASTER")
@Audited
@DynamicInsert
@DynamicUpdate
public class MediUserReportsMasterPojo implements Serializable {
	
	
	// fields
	
		@Id
		@Column(name="REPORT_ID",nullable=false,length=6)
		private Integer REPORT_ID;
		
		@Column(name="REPORT_NAME", nullable=false,length=80)
		private String REPORT_NAME;
		
		
		@Column(name="REPORT_XML_NAME", nullable=false,length=200)
		private String REPORT_XML_NAME;
		
		
		
		@Column(name="REPORT_DESCRIPTION", nullable=false,length=200)
		private String REPORT_DESCRIPTION;
		
		
		
		@Column(name="RECORD_STATUS", nullable=false,length=1)
		private Integer RECORD_STATUS;
		

		@Column(name="REPORT_TYPE", nullable=false,length=1)
		private Integer REPORT_TYPE;
		
		
		
		@Basic
		@Temporal(TemporalType.DATE)
		@Column(name="CREATED_DATE",nullable=false,length=10)
		private Date CREATED_DATE;
		
		@Basic
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name="CREATED_TIME",nullable=false)
		private Date CREATED_TIME;
		 
		@Column(name="CREATED_ON", nullable=false,length=40)
		private String CREATED_ON;
		
		@Column(name="CREATED_BY", nullable=false,length=20)
		private String CREATED_BY;

		
		// getters and setters 
		
		
		
		
		
		public Integer getREPORT_ID() {
			return REPORT_ID;
		}

		public String getREPORT_XML_NAME() {
			return REPORT_XML_NAME;
		}

		public void setREPORT_XML_NAME(String rEPORT_XML_NAME) {
			REPORT_XML_NAME = rEPORT_XML_NAME;
		}

		public void setREPORT_ID(Integer rEPORT_ID) {
			REPORT_ID = rEPORT_ID;
		}

		public String getREPORT_NAME() {
			return REPORT_NAME;
		}

		public void setREPORT_NAME(String rEPORT_NAME) {
			REPORT_NAME = rEPORT_NAME;
		}

		public String getREPORT_DESCRIPTION() {
			return REPORT_DESCRIPTION;
		}

		public void setREPORT_DESCRIPTION(String rEPORT_DESCRIPTION) {
			REPORT_DESCRIPTION = rEPORT_DESCRIPTION;
		}

		public Integer getRECORD_STATUS() {
			return RECORD_STATUS;
		}

		public void setRECORD_STATUS(Integer rECORD_STATUS) {
			RECORD_STATUS = rECORD_STATUS;
		}

		public Integer getREPORT_TYPE() {
			return REPORT_TYPE;
		}

		public void setREPORT_TYPE(Integer rEPORT_TYPE) {
			REPORT_TYPE = rEPORT_TYPE;
		}

		public Date getCREATED_DATE() {
			return CREATED_DATE;
		}

		public void setCREATED_DATE(Date cREATED_DATE) {
			CREATED_DATE = cREATED_DATE;
		}

		public Date getCREATED_TIME() {
			return CREATED_TIME;
		}

		public void setCREATED_TIME(Date cREATED_TIME) {
			CREATED_TIME = cREATED_TIME;
		}

		public String getCREATED_ON() {
			return CREATED_ON;
		}

		public void setCREATED_ON(String cREATED_ON) {
			CREATED_ON = cREATED_ON;
		}

		public String getCREATED_BY() {
			return CREATED_BY;
		}

		public void setCREATED_BY(String cREATED_BY) {
			CREATED_BY = cREATED_BY;
		}
		
		
		
		// mappings
		
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
