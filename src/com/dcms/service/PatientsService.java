package com.dcms.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.dcms.bean.ResponseBean;

@Service
public interface PatientsService {
	
	ResponseBean addPatient(ResponseBean responsebean) throws SQLException, Exception;
	
	ResponseBean searchPatient(ResponseBean responsebean) throws SQLException, Exception;
	
	ResponseBean selectPatient(ResponseBean responsebean) throws SQLException, Exception;

	ResponseBean getAllPatients() throws SQLException, Exception;

	ResponseBean updatePatient(ResponseBean responsebean) throws SQLException, Exception;

	ResponseBean deletePatient(ResponseBean responsebean) throws SQLException, Exception;

}
