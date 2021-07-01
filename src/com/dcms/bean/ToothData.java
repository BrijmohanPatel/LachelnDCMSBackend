package com.dcms.bean;

public class ToothData {
	private String upperLeftTooth;
	private String upperRightTooth;
	private String lowerLeftTooth;
	private String lowerRightTooth;
	
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
	@Override
	public String toString() {
		return "ToothData [upperLeftTooth=" + upperLeftTooth + ", upperRightTooth=" + upperRightTooth
				+ ", lowerLeftTooth=" + lowerLeftTooth + ", lowerRightTooth=" + lowerRightTooth + "]";
	}
	
	
}
