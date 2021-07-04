package com.dcms.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.dcms.bean.ResponseBean;

@Service
public interface TreatmentService {
	
ResponseBean addTreatment(ResponseBean responsebean) throws SQLException, Exception;
	
	ResponseBean getTreatments(ResponseBean responsebean) throws SQLException, Exception;


}
