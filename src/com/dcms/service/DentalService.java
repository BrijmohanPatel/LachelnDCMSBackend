package com.dcms.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.dcms.bean.*;


@Service
public interface DentalService {
	
	ResponseBean addPatient(ResponseBean responsebean) throws SQLException, Exception;

	ResponseBean addTreatmentPlan(ResponseBean responsebean) throws SQLException, Exception;
	
	ResponseBean searchPatient(ResponseBean responsebean) throws SQLException, Exception;
	
	ResponseBean selectPatient(ResponseBean responsebean) throws SQLException, Exception;
	
	/*ResponseBean generateBill(ResponseBean responsebean) throws SQLException, Exception;
	
	ResponseBean generatePDF(ResponseBean responsebean) throws SQLException, Exception;

	ResponseBean removeWork(ResponseBean responsebean) throws SQLException, Exception;

	ResponseBean updateWork(ResponseBean responsebean) throws SQLException, Exception;
	
	ResponseBean userLogin(ResponseBean responsebean) throws SQLException, Exception;*/
}
