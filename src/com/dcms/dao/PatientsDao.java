package com.dcms.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.dcms.bean.ResponseBean;

@Component
public interface PatientsDao {

	
	ResponseBean addPatient(ResponseBean responsebean) throws SQLException, Exception;
	
	ResponseBean searchPatient(ResponseBean responsebean) throws SQLException, Exception;
	
	ResponseBean selectPatient(ResponseBean responsebean) throws SQLException, Exception;
}
