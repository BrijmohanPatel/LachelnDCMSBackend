package com.dcms.dao;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dcms.bean.*;

@Component
public interface DentalDao {

	ResponseBean addPatient(ResponseBean responsebean) throws SQLException, Exception;
/*
	ResponseBean getTodaysWorkDone(ResponseBean responsebean) throws SQLException, Exception;
	
	ResponseBean getDoctorMaster(ResponseBean responsebean) throws SQLException, Exception;
	
	ResponseBean getWorkMaster(ResponseBean responsebean) throws SQLException, Exception;

	ResponseBean generateBill(ResponseBean responsebean) throws SQLException, Exception;
	
	ResponseBean generatePDF(ResponseBean responsebean) throws SQLException, Exception;

	ResponseBean removeWork(ResponseBean responsebean) throws SQLException, Exception;

	ResponseBean updateWork(ResponseBean responsebean) throws SQLException, Exception;
	
	ResponseBean userLogin(ResponseBean responsebean) throws SQLException, Exception;*/
}
