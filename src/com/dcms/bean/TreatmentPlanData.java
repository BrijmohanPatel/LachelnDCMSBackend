package com.dcms.bean;

import java.util.List;

public class TreatmentPlanData {
	
	private String tp_id;
	private String caseNo;
	List<PlansData> plans;
	private String totalAmount;
	private String reg_date;
	private String status;
		
	public String getTp_id() {
		return tp_id;
	}
	public void setTp_id(String tp_id) {
		this.tp_id = tp_id;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<PlansData> getPlans() {
		return plans;
	}
	public void setPlans(List<PlansData> plans) {
		this.plans = plans;
	}
	@Override
	public String toString() {
		return "TreatmentPlanData [tp_id=" + tp_id + ", caseNo=" + caseNo + ", plans=" + plans + ", totalAmount="
				+ totalAmount + ", reg_date=" + reg_date + ", status=" + status + "]";
	}
}
