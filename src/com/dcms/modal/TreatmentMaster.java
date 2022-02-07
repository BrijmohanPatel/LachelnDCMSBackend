package com.dcms.modal;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.dcms.utilities.StringPrefixedSequenceIdGenerator;

@Entity(name="treatment_master")
public class TreatmentMaster {
	
	
	private static final long serialVersionUID = 1L;
	
	@Id   // To define the primary key column of the table
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "treatment_master_seq")
	@GenericGenerator(name = "treatment_master_seq", strategy = "com.dcms.utilities.StringPrefixedSequenceIdGenerator",
		parameters = { 
				@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
				@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "TRTM"),
				@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
		})
	private String tm_id;
	
	private String treatment_name;
	
	private String  treatment_added_date;
	
	private String  treatment_updated_date;



	public String getTreatment_added_date() {
		return treatment_added_date;
	}

	public void setTreatment_added_date(String treatment_added_date) {
		this.treatment_added_date = treatment_added_date;
	}

	public String getTreatment_updated_date() {
		return treatment_updated_date;
	}

	public void setTreatment_updated_date(String treatment_updated_date) {
		this.treatment_updated_date = treatment_updated_date;
	}

	public String getTm_id() {
		return tm_id;
	}

	public void setTm_id(String tm_id) {
		this.tm_id = tm_id;
	}

	public String getTreatment_name() {
		return treatment_name;
	}

	public void setTreatment_name(String treatment_name) {
		this.treatment_name = treatment_name;
	}
	
	

}
