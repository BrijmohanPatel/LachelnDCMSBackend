package com.dcms.bean;

import java.util.List;

import com.dcms.modal.*;

public class ResponseData {
	
	/*Patient data getter setter*/ 
	PatientData patientdata = new PatientData();
	
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
	TreatmentPlanData treatmentPlanData  = new TreatmentPlanData();

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
	
	
	PlansData plandata = new PlansData();
	
	public PlansData getPlandata() {
		return plandata;
	}
	public void setPlandata(PlansData plandata) {
		this.plandata = plandata;
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
	
	/*Treatment Data */
	
	TreatmentData treatmentData = new TreatmentData();

	public TreatmentData getTreatmentData() {
		return treatmentData;
	}
	public void setTreatmentData(TreatmentData treatmentData) {
		this.treatmentData = treatmentData;
	}
	
	List<Treatments> treatmentsList;

	public List<Treatments> getTreatmentsList() {
		return treatmentsList;
	}
	public void setTreatmentsList(List<Treatments> treatmentsList) {
		this.treatmentsList = treatmentsList;
	}
	
	/*Treatment Data ENDS*/
	
	/* Treatment Master Start */
	
	TreatmentMasterData treatmentMasterData = new TreatmentMasterData();

	public TreatmentMasterData getTreatmentMasterData() {
		return treatmentMasterData;
	}
	public void setTreatmentMasterData(TreatmentMasterData treatmentMasterData) {
		this.treatmentMasterData = treatmentMasterData;
	}

	List<TreatmentMaster> treatmentMasterList;

	public List<TreatmentMaster> getTreatmentMasterList() {
		return treatmentMasterList;
	}
	public void setTreatmentMasterList(List<TreatmentMaster> treatmentMasterList) {
		this.treatmentMasterList = treatmentMasterList;
	}
	/* Treatment Master Start END */
	
	/* Work Master start */
	
	WorkDoneMasterData workdoneMasterData = new WorkDoneMasterData();

	public WorkDoneMasterData getWorkdoneMasterData() {
		return workdoneMasterData;
	}
	public void setWorkdoneMasterData(WorkDoneMasterData workdoneMasterData) {
		this.workdoneMasterData = workdoneMasterData;
	}

	List<WorkdoneMaster> workdoneMasterList;

	public List<WorkdoneMaster> getWorkdoneMasterList() {
		return workdoneMasterList;
	}
	public void setWorkdoneMasterList(List<WorkdoneMaster> workdoneMasterList) {
		this.workdoneMasterList = workdoneMasterList;
	}
	
	/* work master end */
	
	
	
}
