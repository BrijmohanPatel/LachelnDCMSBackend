package com.dcms.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.dcms.bean.ResponseBean;


@Component
public interface TreatmentPlanDao {
	
	ResponseBean addTreatmentPlan(ResponseBean responsebean) throws SQLException, Exception;
	
	ResponseBean updateTreatmentPlan(ResponseBean responsebean) throws SQLException, Exception;
	
	ResponseBean deleteTreatmentPlan(String tpID) throws SQLException, Exception;

}
