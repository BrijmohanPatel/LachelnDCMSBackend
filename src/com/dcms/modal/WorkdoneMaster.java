package com.dcms.modal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.dcms.utilities.StringPrefixedSequenceIdGenerator;

@Entity(name="workdone_master")
public class WorkdoneMaster {
	
	@Id   // To define the primary key column of the table
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workdone_master_seq")
	@GenericGenerator(name = "workdone_master_seq", strategy = "com.dcms.utilities.StringPrefixedSequenceIdGenerator",
		parameters = { 
				@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
				@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "WD"),
				@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
		})
	private String w_id;
	
	private String work_name;
	
	private String work_added_date;
	
	private String work_updated_date;
	

	public String getW_id() {
		return w_id;
	}

	public void setW_id(String w_id) {
		this.w_id = w_id;
	}

	public String getWork_name() {
		return work_name;
	}

	public void setWork_name(String work_name) {
		this.work_name = work_name;
	}

	public String getWork_added_date() {
		return work_added_date;
	}

	public void setWork_added_date(String work_added_date) {
		this.work_added_date = work_added_date;
	}

	public String getWork_updated_date() {
		return work_updated_date;
	}

	public void setWork_updated_date(String work_updated_date) {
		this.work_updated_date = work_updated_date;
	}

	@Override
	public String toString() {
		return "WorkdoneMaster [w_id=" + w_id + ", work_name=" + work_name + ", work_added_date=" + work_added_date
				+ ", work_updated_date=" + work_updated_date + "]";
	}
	
	
	
}
