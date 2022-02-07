package com.dcms.modal;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.dcms.utilities.StringPrefixedSequenceIdGenerator;

@Entity(name="treatment_plans")
public class TreatmentPlans implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id   // To define the primary key column of the table
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "treatmentPlan_seq")
	@GenericGenerator(name = "treatmentPlan_seq", strategy = "com.dcms.utilities.StringPrefixedSequenceIdGenerator",
		parameters = { 
				@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
				@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "TP"),
				@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
		})
	private String tp_id;
	private String case_no;
	private String total_amount;
	private String total_balance_amount;
	private String total_paid_amount;
	private String reg_date;
	private String status;
	
	@Transient
	private String treatment_start_date;
	
	@Transient
	private String treatment_end_date;
	
	public String getTreatment_start_date() {
		return treatment_start_date;
	}
	public void setTreatment_start_date(String treatment_start_date) {
		this.treatment_start_date = treatment_start_date;
	}
	public String getTreatment_end_date() {
		return treatment_end_date;
	}
	public void setTreatment_end_date(String treatment_end_date) {
		this.treatment_end_date = treatment_end_date;
	}
		
	public String getTp_id() {
		return tp_id;
	}
	public void setTp_id(String tp_id) {
		this.tp_id = tp_id;
	}
	public String getCase_no() {
		return case_no;
	}
	public void setCase_no(String case_no) {
		this.case_no = case_no;
	}
	public String getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getTotal_balance_amount() {
		return total_balance_amount;
	}
	public void setTotal_balance_amount(String total_balance_amount) {
		this.total_balance_amount = total_balance_amount;
	}
	public String getTotal_paid_amount() {
		return total_paid_amount;
	}
	public void setTotal_paid_amount(String total_paid_amount) {
		this.total_paid_amount = total_paid_amount;
	}
	
	
	@Transient //For any column needs not to be stored in DB
	private List<Plans> plans;

	public List<Plans> getPlans() {
		return plans;
	}
	public void setPlans(List<Plans> plans) {
		this.plans = plans;
	}
	
}
