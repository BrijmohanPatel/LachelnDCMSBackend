package com.dcms.bean;

public class TreatmentMasterData {
	
	private String tm_id;
	private String treatment_name;
	
	
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
	@Override
	public String toString() {
		return "TreatmentMaster [tm_id=" + tm_id + ", treatment_name=" + treatment_name + "]";
	}

}
