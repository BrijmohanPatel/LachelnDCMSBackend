package com.dcms.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.dcms.bean.*;


@Service
public interface DentalService {
	
	ResponseBean addPatient(ResponseBean responsebean) throws SQLException, Exception;

	/*ResponseBean getTodaysWorkDone(ResponseBean responsebean) throws SQLException, Exception;
	
	ResponseBean getDoctorMaster(ResponseBean responsebean) throws SQLException, Exception;
	
	ResponseBean getWorkMaster(ResponseBean responsebean) throws SQLException, Exception;
	
	ResponseBean generateBill(ResponseBean responsebean) throws SQLException, Exception;
	
	ResponseBean generatePDF(ResponseBean responsebean) throws SQLException, Exception;

	ResponseBean removeWork(ResponseBean responsebean) throws SQLException, Exception;

	ResponseBean updateWork(ResponseBean responsebean) throws SQLException, Exception;
	
	ResponseBean userLogin(ResponseBean responsebean) throws SQLException, Exception;*/
}
