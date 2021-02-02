package com.dcms.modal;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name="patients")
//@Table(name="WORK_DATATABLE") // to change the name of the table
public class Patients implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id   // To define the primary key column of the table
	private String patient_id;
	private String patient_name;
	private String gender;
	private Long mobile_number;
	private int age;
	private String occupation;
	private String address;
	private String medical_history;
	private String chief_complaint;
	private String reg_date;
	
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(Long mobile_number) {
		this.mobile_number = mobile_number;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMedical_history() {
		return medical_history;
	}
	public void setMedical_history(String medical_history) {
		this.medical_history = medical_history;
	}
	public String getChief_complaint() {
		return chief_complaint;
	}
	public void setChief_complaint(String chief_complaint) {
		this.chief_complaint = chief_complaint;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "Patients [patient_id=" + patient_id + ", patient_name=" + patient_name + ", gender=" + gender
				+ ", mobile_number=" + mobile_number + ", age=" + age + ", occupation=" + occupation + ", address="
				+ address + ", medical_history=" + medical_history + ", chief_complaint=" + chief_complaint
				+ ", reg_date=" + reg_date + "]";
	}
		
}