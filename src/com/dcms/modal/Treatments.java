package com.dcms.modal;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.dcms.utilities.StringPrefixedSequenceIdGenerator;

@Entity(name="treatments")
public class Treatments implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id   // To define the primary key column of the table
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "treatment_seq")
	@GenericGenerator(name = "treatment_seq", strategy = "com.dcms.utilities.StringPrefixedSequenceIdGenerator",
		parameters = { 
				@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
				@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "TRT"),
				@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
		})
	private String t_id;
	private String patient_id;
	private String tp_id;
	private String work_done;
	private String treatment_date;
	private String paid_amount;
	private String balance_amount;
	private String payment_on_date;
	private String upperLeftTooth;
	private String upperRightTooth;
	private String lowerLeftTooth;
	private String lowerRightTooth;
	
	public String getT_id() {
		return t_id;
	}
	public void setT_id(String t_id) {
		this.t_id = t_id;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getTp_id() {
		return tp_id;
	}
	public void setTp_id(String tp_id) {
		this.tp_id = tp_id;
	}
	public String getWork_done() {
		return work_done;
	}
	public void setWork_done(String work_done) {
		this.work_done = work_done;
	}
	public String getTreatment_date() {
		return treatment_date;
	}
	public void setTreatment_date(String treatment_date) {
		this.treatment_date = treatment_date;
	}
	public String getPaid_amount() {
		return paid_amount;
	}
	public void setPaid_amount(String paid_amount) {
		this.paid_amount = paid_amount;
	}
	public String getBalance_amount() {
		return balance_amount;
	}
	public void setBalance_amount(String balance_amount) {
		this.balance_amount = balance_amount;
	}
	public String getPayment_on_date() {
		return payment_on_date;
	}
	public void setPayment_on_date(String payment_on_date) {
		this.payment_on_date = payment_on_date;
	}
	public String getUpperLeftTooth() {
		return upperLeftTooth;
	}
	public void setUpperLeftTooth(String upperLeftTooth) {
		this.upperLeftTooth = upperLeftTooth;
	}
	public String getUpperRightTooth() {
		return upperRightTooth;
	}
	public void setUpperRightTooth(String upperRightTooth) {
		this.upperRightTooth = upperRightTooth;
	}
	public String getLowerLeftTooth() {
		return lowerLeftTooth;
	}
	public void setLowerLeftTooth(String lowerLeftTooth) {
		this.lowerLeftTooth = lowerLeftTooth;
	}
	public String getLowerRightTooth() {
		return lowerRightTooth;
	}
	public void setLowerRightTooth(String lowerRightTooth) {
		this.lowerRightTooth = lowerRightTooth;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
