package com.dcms.bean;

public class TreatmentData {
	private String t_id;
	private String patient_id;
	private String tp_id;
	private String work_done;
	private String treatment_date;
	private String paid_amount;
	private String balance_amount;
	private String payment_on_date;
	private ToothData toothData = new ToothData();;
	
	private Byte signature;
	
	public Byte getSignature() {
		return signature;
	}
	public void setSignature(Byte signature) {
		this.signature = signature;
	}
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
	public ToothData getToothData() {
		return toothData;
	}
	public void setToothData(ToothData toothData) {
		this.toothData = toothData;
	}
	@Override
	public String toString() {
		return "TreatmentData [t_id=" + t_id + ", patient_id=" + patient_id + ", tp_id=" + tp_id + ", work_done="
				+ work_done + ", treatment_date=" + treatment_date + ", paid_amount=" + paid_amount
				+ ", balance_amount=" + balance_amount + ", payment_on_date=" + payment_on_date + ", toothData="
				+ toothData + ", signature=" + signature + "]";
	}
		
}
