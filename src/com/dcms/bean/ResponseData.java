package com.dcms.bean;

import java.util.List;

public class ResponseData {
	
	PatientData patientdata = new PatientData();
	
	public PatientData getPatientdata() {
		return patientdata;
	}
	public void setPatientdata(PatientData patientdata) {
		this.patientdata = patientdata;
	}
	
	private String userName;
	private String password;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
		
}
