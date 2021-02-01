package com.dcms.bean;

import java.util.List;

public class ResponseBean {

	String status;
	String message;
	ResponseData data = new ResponseData();
	private String userName;
	private String userCode;
	private long userId;
	private String statusCode;
	private String action;
	private String accessToken;
	private String email;
	private String mobile;
	private String fileName; 
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private List<Byte> fileData;

	public List<Byte> getFileData() {
		return fileData;
	}

	public void setFileData(List<Byte> fileData) {
		this.fileData = fileData;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseData getData() {
		return data;
	}

	public void setData(ResponseData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseBean [status=" + status + ", message=" + message + ", data=" + data + ", userName=" + userName
				+ ", userCode=" + userCode + ", userId=" + userId + ", statusCode=" + statusCode + ", action=" + action
				+ ", accessToken=" + accessToken + ", email=" + email + ", mobile=" + mobile + "]";
	}

	
}
