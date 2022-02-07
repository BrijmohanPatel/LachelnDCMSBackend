package com.dcms.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.dcms.bean.ResponseBean;

@Component
public interface TreatmentDao {
	
	ResponseBean addTreatment(ResponseBean responsebean) throws SQLException, Exception;
	
	ResponseBean getTreatments(ResponseBean responsebean) throws SQLException, Exception;

	ResponseBean deleteTreatment(ResponseBean responsebean) throws SQLException, Exception;

	ResponseBean updateTreatment(ResponseBean responsebean) throws SQLException, Exception;

}
