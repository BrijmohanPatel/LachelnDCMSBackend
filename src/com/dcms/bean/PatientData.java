package com.dcms.bean;

import java.util.List;

public class PatientData {
	
	private Long PATIENT_ID;
	private String PATIENT_NAME;
	private String GENDER;
	private Long MOBIL_NUMBER;
	private int AGE;
	private String OCCUPATION;
	private String ADDRESS;
	private String MEDICAL_HISTORY;
	private Long CHIEF_COMPLAINT;
	private String REG_DATE;
	
	public Long getPATIENT_ID() {
		return PATIENT_ID;
	}
	public void setPATIENT_ID(Long pATIENT_ID) {
		PATIENT_ID = pATIENT_ID;
	}
	public String getPATIENT_NAME() {
		return PATIENT_NAME;
	}
	public void setPATIENT_NAME(String pATIENT_NAME) {
		PATIENT_NAME = pATIENT_NAME;
	}
	public String getGENDER() {
		return GENDER;
	}
	public void setGENDER(String gENDER) {
		GENDER = gENDER;
	}
	public Long getMOBIL_NUMBER() {
		return MOBIL_NUMBER;
	}
	public void setMOBIL_NUMBER(Long mOBIL_NUMBER) {
		MOBIL_NUMBER = mOBIL_NUMBER;
	}
	public int getAGE() {
		return AGE;
	}
	public void setAGE(int aGE) {
		AGE = aGE;
	}
	public String getOCCUPATION() {
		return OCCUPATION;
	}
	public void setOCCUPATION(String oCCUPATION) {
		OCCUPATION = oCCUPATION;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getMEDICAL_HISTORY() {
		return MEDICAL_HISTORY;
	}
	public void setMEDICAL_HISTORY(String mEDICAL_HISTORY) {
		MEDICAL_HISTORY = mEDICAL_HISTORY;
	}
	public Long getCHIEF_COMPLAINT() {
		return CHIEF_COMPLAINT;
	}
	public void setCHIEF_COMPLAINT(Long cHIEF_COMPLAINT) {
		CHIEF_COMPLAINT = cHIEF_COMPLAINT;
	}
	public String getREG_DATE() {
		return REG_DATE;
	}
	public void setREG_DATE(String rEG_DATE) {
		REG_DATE = rEG_DATE;
	}
	@Override
	public String toString() {
		return "PatientData [PATIENT_ID=" + PATIENT_ID + ", PATIENT_NAME=" + PATIENT_NAME + ", GENDER=" + GENDER
				+ ", MOBIL_NUMBER=" + MOBIL_NUMBER + ", AGE=" + AGE + ", OCCUPATION=" + OCCUPATION + ", ADDRESS="
				+ ADDRESS + ", MEDICAL_HISTORY=" + MEDICAL_HISTORY + ", CHIEF_COMPLAINT=" + CHIEF_COMPLAINT
				+ ", REG_DATE=" + REG_DATE + "]";
	}
	
}
