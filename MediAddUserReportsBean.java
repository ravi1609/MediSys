package com.medisys.ravi.beans;

import java.util.Date;

public class MediAddUserReportsBean {

	
	
	    //fields
	
		private Integer report_id;
		private String report_name;
		private String report_desc;
		private Integer	rcrd_status;
		private Integer uflag;
		private String status;
		private String created_by;
		private Date created_date;
		private String report_xml_name;
		private Integer report_type_id;

		
		
	    // getters and setters 
	
		public Integer getReport_id() {
			return report_id;
		}
		public void setReport_id(Integer report_id) {
			this.report_id = report_id;
		}
		public String getReport_name() {
			return report_name;
		}
		public void setReport_name(String report_name) {
			this.report_name = report_name;
		}
		public String getReport_desc() {
			return report_desc;
		}
		public void setReport_desc(String report_desc) {
			this.report_desc = report_desc;
		}
		public Integer getRcrd_status() {
			return rcrd_status;
		}
		public void setRcrd_status(Integer rcrd_status) {
			this.rcrd_status = rcrd_status;
		}
		public Integer getUflag() {
			return uflag;
		}
		public void setUflag(Integer uflag) {
			this.uflag = uflag;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getCreated_by() {
			return created_by;
		}
		public void setCreated_by(String created_by) {
			this.created_by = created_by;
		}
		public Date getCreated_date() {
			return created_date;
		}
		public void setCreated_date(Date created_date) {
			this.created_date = created_date;
		}
		public String getReport_xml_name() {
			return report_xml_name;
		}
		public void setReport_xml_name(String report_xml_name) {
			this.report_xml_name = report_xml_name;
		}
		public Integer getReport_type_id() {
			return report_type_id;
		}
		public void setReport_type_id(Integer report_type_id) {
			this.report_type_id = report_type_id;
		}
	
	
	
	
	
	
	
	
	
	
}
