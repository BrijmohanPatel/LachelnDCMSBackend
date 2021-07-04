package com.dcms.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.dcms.bean.ResponseBean;


@Service
public interface TreatmentPlanService {
	
	ResponseBean addTreatmentPlan(ResponseBean responsebean) throws SQLException, Exception;
	
	ResponseBean updateTreatmentPlan(ResponseBean responsebean) throws SQLException, Exception;
	

}
