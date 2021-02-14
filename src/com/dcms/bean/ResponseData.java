package com.dcms.bean;

import java.util.List;

import com.dcms.modal.*;

public class ResponseData {
	
	/*Patient data getter setter*/ 
	PatientData patientdata;
	
	public PatientData getPatientdata() {
		return patientdata;
	}
	public void setPatientdata(PatientData patientdata) {
		this.patientdata = patientdata;
	}
	
	List<Patients> patientsList;
	
	public List<Patients> getPatientsList() {
		return patientsList;
	}
	public void setPatientsList(List<Patients> patientsList) {
		this.patientsList = patientsList;
	}
	/*Patient data getter setter ENDs*/
	
	
	/*TreatmentPlanData getter setter*/
	TreatmentPlanData treatmentPlanData;

	public TreatmentPlanData getTreatmentPlanData() {
		return treatmentPlanData;
	}
	public void setTreatmentPlanData(TreatmentPlanData treatmentPlanData) {
		this.treatmentPlanData = treatmentPlanData;
	}
	
	List<TreatmentPlans> treatmentPlanList;

	public List<TreatmentPlans> getTreatmentPlanList() {
		return treatmentPlanList;
	}
	public void setTreatmentPlanList(List<TreatmentPlans> treatmentPlanList) {
		this.treatmentPlanList = treatmentPlanList;
	}
	
	/*TreatmentPlanData getter setter ENDs*/
	
	/*Search Patients*/
	SearchPatients searchPatients  = new SearchPatients();

	public SearchPatients getSearchPatients() {
		return searchPatients;
	}
	public void setSearchPatients(SearchPatients searchPatients) {
		this.searchPatients = searchPatients;
	}
	
	/*Search Patients ENDs*/
	
	
}
